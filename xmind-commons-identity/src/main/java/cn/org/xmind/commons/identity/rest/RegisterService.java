package cn.org.xmind.commons.identity.rest;

import cn.org.xmind.commons.identity.db.entity.User;
import cn.org.xmind.commons.identity.rest.vo.RegisterResult;
import cn.org.xmind.commons.identity.service.UserService;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodney
 */
@Service("registerService")
@Path("/register")
@Description(value = "用户注册服务")
public class RegisterService {

    @Resource
    private UserService userService;

    /**
     * 完成注册功能
     *
     * @param user
     * @return
     */
    @PUT
    @Consumes(value = {"application/json"})
    @Produces(value = {"application/json"})
    @Transactional
    public RegisterResult post(
            User user) {
        RegisterResult result = new RegisterResult();
        result.setTitle("注册失败");
        try {
            userService.add(user);
            result.setTitle("注册成功");
            result.setMessage("注册成功，但是账户未激活，请到接收发送到" + user.getEmail() + "的激活邮件，按提示进行激活。");
        } catch (IllegalArgumentException ex) {
            result.setMessage("注册时出现错误：" + ex.getLocalizedMessage());
            result.setException(ex);
        }
        return result;
    }
}
