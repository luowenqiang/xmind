package cn.org.xmind.commons.identity.rest;

import cn.org.xmind.commons.identity.exception.IllegalActiveCodeException;
import cn.org.xmind.commons.identity.rest.vo.ActiveResult;
import cn.org.xmind.commons.identity.service.UserService;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodney
 */
@Service("accountActiveService")
@Path("/account/active")
@Description(value = "账户激活服务")
public class AccountActiveService {

    @Resource
    private UserService userService;

    @GET
    @Path("/{activeCode}")
    @Produces(value = {"application/json;charset=UTF-8"})
    @Description(value = "根据激活码激活账户，激活完成后返回一个ActiveResult对象。")
    public ActiveResult active(@PathParam("activeCode") String activeCode) {
        ActiveResult result = new ActiveResult();
        if (activeCode == null) {
            //请求参数没有携带激活码过来，返回错误提示
            result.setErrorCode(ActiveResult.ERROR_CODE_INVALID_REQUEST);
            result.setTitle("激活失败");
            result.setMessage("无效请求，请从正确的路径访问网站，谢谢。");
        } else {
            try {
                userService.active(activeCode);
                //激活成功，显示成功界面，并且5秒后重定向到登陆界面
                result.setMessage("激活成功");
                result.setTitle("激活成功");
                result.setRedirect("/login");
            } catch (IllegalActiveCodeException ex) {
                result.setErrorCode(ActiveResult.ERROR_CODE_ACTIVE_FAILED);
                result.setTitle("激活失败");
                result.setMessage("激活失败：" + ex.getLocalizedMessage());
                if (ex.getEmail() != null) {
                    result.setRedirect("/identity/active/resend/" + ex.getEmail());
                } else {
                    result.setMessage("激活失败：无效激活码，请从邮件中复制完整的地址进行激活。");
                }
            }
        }
        return result;
    }

    /**
     * 重新发送验证码
     *
     * @param email
     * @return
     */
    @Path("/resend/{email}")
    @Produces(value = {"application/json;charset=UTF-8"})
    @Description(value = "重新发送激活码到电子邮件中。")
    public ActiveResult resend(@PathParam("email") String email) {
        ActiveResult result = new ActiveResult();
        result.setMessage("激活码已经重新发送到指定邮件，请按照邮件的指引进行下一步操作。");
        result.setTitle("激活码已经重新发送");
        return result;
    }
}
