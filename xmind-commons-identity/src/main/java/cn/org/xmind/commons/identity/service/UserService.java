package cn.org.xmind.commons.identity.service;


import cn.org.xmind.commons.email.MailService;
import cn.org.xmind.commons.email.MailType;
import cn.org.xmind.commons.identity.db.AccountActiveLogRepository;
import cn.org.xmind.commons.identity.db.UserRepository;
import cn.org.xmind.commons.identity.db.entity.AccountActiveLog;
import cn.org.xmind.commons.identity.db.entity.User;
import cn.org.xmind.commons.identity.exception.IllegalActiveCodeException;
import cn.org.xmind.commons.identity.exception.PasswordValidateFailed;
import cn.org.xmind.commons.identity.exception.UserNotFoundException;
import cn.org.xmind.commons.security.MultiPasswordEncoder;
import cn.org.xmind.commons.utils.MD5Utils;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
/**
 *
 * @author rodney
 */
@Service(value = "userService")
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private static final ThreadLocal<String> authenticatedUserId = new ThreadLocal<String>();
    //@Resource
    //private SecurityPasswordService securityPasswordService;
    @Resource
    private UserRepository userRepository;
    @Resource
    private AccountActiveLogRepository accountActiveLogRepository;
    @Resource
    private MailService mailService;
    @Resource
    private MultiPasswordEncoder multiPasswordEncoder;

    @Transactional
    public void add(User user) {
        //把登录名和用户名设置为一样，页面不传递用户名过来
        user.setUserName(user.getLoginName());
        if (user.getNickName() == null) {
            //如果没有填写昵称，把昵称设置为和用户名相同
            user.setNickName(user.getUserName());
        }
        //检查电子邮件、登录名等是否已经存在，如果已经存在则不允许注册
        boolean isExisted = this.checkLoginNameIsExisted(user.getLoginName());
        if (isExisted) {
            throw new IllegalArgumentException("登录名" + user.getLoginName() + "已经存在，不能重复注册");
        }
        isExisted = this.checkEmailIsExisted(user.getEmail());
        if (isExisted) {
            throw new IllegalArgumentException("电子邮件" + user.getEmail() + "已经被注册过，不能重复注册");
        }

        //String password = user.getPassword();
        String encodePassword = multiPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //数据校验通过，执行注册，把数据写入数据库中
        userRepository.saveAndFlush(user);
        //生成激活码
        String uuid = UUID.randomUUID().toString();
        String activeCode = this.buildActiveCode(uuid, user.getLoginName());
        //发送激活邮件
        boolean mailResult = mailService.send(MailType.ACCOUNT_ACTIVE_CODE, user.getEmail(), activeCode);
        if (!mailResult) {
            logger.warning("邮件发送失败，无法发送激活邮件，默认修改用户状态为已激活");
            //如果邮件发送失败，把账户改为已经激活，因为这种情况下可能是因为邮件服务出了问题
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            //激活码发送成功，记录激活码到数据库中，24小时内有效
            AccountActiveLog log = new AccountActiveLog(user, activeCode);
            accountActiveLogRepository.save(log);
        }
    }

    /**
     * 修改用户的密码，并且生成随机的salt值
     *
     * @return
     */
    public boolean changePassword(Long userId, String oldPassword, String newPassword, String confirmPassword)
            throws UserNotFoundException, PasswordValidateFailed {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            //比较新密码和确认密码是否相等
            if (newPassword.equals(confirmPassword)) {
                //比较旧密码是否正确
                if (multiPasswordEncoder.matches(oldPassword, user.getPassword())) {
                    //旧密码验证通过
                    String encodePassword = multiPasswordEncoder.encode(newPassword);
                    user.setPassword(encodePassword);
                    //保存修改后的用户信息，主要是更新了密码和salt值
                    userRepository.save(user);
                } else {
                    //旧密码验证失败
                    throw new PasswordValidateFailed(PasswordValidateFailed.Key.OLD_PASSWORD_ERROR, "旧密码校验失败");
                }
            } else {
                //新密码和确认密码不相等
                throw new PasswordValidateFailed(PasswordValidateFailed.Key.PASSOWRD_NOT_THE_SAME, "旧密码校验失败");
            }
        }
        return true;
    }

    public User findByLoginName(String loginName) {
        User user = userRepository.findByLoginName(loginName);
        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    /**
     *
     * @param loginName
     * @return
     */
    public boolean checkLoginNameIsExisted(String loginName) {
        if (StringUtils.isEmpty(loginName)) {
            //如果登录名是空的，不检查登录名
            return false;
        }
        User old = findByLoginName(loginName);
        //如果old为空，表示loginName不存在，返回false
        //否则返回true
        return !(old == null);
    }

    /**
     *
     * @param loginName
     * @return
     */
    public boolean checkEmailIsExisted(String email) {
        if (StringUtils.isEmpty(email)) {
            //如果email是空的，不检查email
            return false;
        }
        User old = userRepository.findByEmail(email);
        //如果old为空，表示email不存在，返回false
        //否则返回true
        return !(old == null);
    }

    public static void setAuthenticatedUserId(String userId) {
        authenticatedUserId.set(userId);
    }

    public static String getAuthenticatedUserId() {
        return authenticatedUserId.get();
    }

    @Transactional
    public void active(String activeCode) {
        //1.根据激活码查询未完成的激活日志
        AccountActiveLog log = accountActiveLogRepository.findByActiveCode(activeCode);
        //2.如果log是空的，表示激活码不存在
        if (log == null) {
            throw new IllegalActiveCodeException(IllegalActiveCodeException.Type.NOT_EXISTS);
        }
        //3.判断激活时间是否为空，不为空表示不能激活，因为之前已经激活过了
        if (log.getActiveTime() != null) {
            throw new IllegalActiveCodeException(IllegalActiveCodeException.Type.ALREADY_IN_USED, log);
        }
        //4.如果还未激活，判断激活码是否已经过期
        if (System.currentTimeMillis() > log.getValidTime().getTime()) {
            //如果当前已经比有效时间更大，表示已经过期
            throw new IllegalActiveCodeException(IllegalActiveCodeException.Type.OUT_OF_DATE, log);
        }
        //5.如果未被使用，并且还有效，修改激活日志的激活时间
        log.setActiveTime(new Date());
        //6.同时修改用户中的enabled属性为true
        User user = log.getUser();
        user.setEnabled(true);
        //保存更改到数据库中
        this.accountActiveLogRepository.save(log);
        this.userRepository.save(user);
    }

    private String buildActiveCode(String uuid, String loginName) {
        StringBuilder builder = new StringBuilder();
        builder.append("uuid=");
        builder.append(uuid);
        builder.append("\n");
        builder.append("userId=");
        builder.append(loginName);

        String activeCode = MD5Utils.encrypt(builder.toString());

        return activeCode;
    }
}
