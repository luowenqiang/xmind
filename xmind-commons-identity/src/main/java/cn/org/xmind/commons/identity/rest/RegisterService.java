package cn.org.xmind.commons.identity.rest;

import cn.org.xmind.commons.identity.db.entity.User;
import cn.org.xmind.commons.identity.rest.vo.RegisterResult;
import cn.org.xmind.commons.identity.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param validateResult
     * @param confirmPassword
     * @return
     */
    @POST
    @Path("/")
    @Produces(value = {"application/json;charset=UTF-8"})
    @Description(value = "用户注册的实现功能")
    public RegisterResult post(
            @Valid User user,
            BindingResult validateResult,
            @RequestParam("confirmPassword") String confirmPassword) {

        RegisterResult result = new RegisterResult();
        result.setTitle("注册失败");
        if (validateResult.hasErrors()) {
            //如果数据校验有异常，回到注册界面，并且显示错误信息
            List<FieldError> errors = validateResult.getFieldErrors();
            for (FieldError error : errors) {
                //mav.addObject("error_field_" + error.getField(), error.getDefaultMessage());
                result.getValidateResultMap().put(error.getField(), error.getDefaultMessage());
            }
            result.setMessage("数据校验失败");
        }
        if (!user.getPassword().equals(confirmPassword)) {
            //两次输入的密码不一致，不允许注册
            result.setMessage("两次输入的密码不一样");
        }
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
