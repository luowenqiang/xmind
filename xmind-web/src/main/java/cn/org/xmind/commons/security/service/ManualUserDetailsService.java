package cn.org.xmind.commons.security.service;

import cn.org.xmind.commons.utils.EmailUtils;
import cn.org.xmind.commons.security.SecurityUserDetails;
import cn.org.xmind.identity.db.entity.User;
import cn.org.xmind.identity.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rodney
 */
@Service("manualUserDetailsService")
public class ManualUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user;
        if (EmailUtils.isEmail(loginName)) {
            //如果是电子邮件的形式，那么根据电子邮件来登录
            user = userService.findByEmail(loginName);
        } else {
            //否则以登录名来进行登录
            user = userService.findByLoginName(loginName);
        }
        if (user == null) {
            throw new UsernameNotFoundException("用户[" + loginName + "]不存在");
        }
        //角色列表
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("user");
        authorities.add(authority);
        //构建Spring需要的用户信息
        //String password = "md5:99eb5eb6e26676eda9dbd54b1e146b24";
        SecurityUserDetails userDetails = new SecurityUserDetails(user, authorities);
        return userDetails;
    }
}
