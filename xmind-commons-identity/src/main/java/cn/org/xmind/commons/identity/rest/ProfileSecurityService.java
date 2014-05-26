package cn.org.xmind.commons.identity.rest;

import javax.ws.rs.Path;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rodney
 */
@Service("profileSecurityService")
@Path("/profile/security")
@Description(value = "账户激活服务")
public class ProfileSecurityService {

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST})
    public ModelAndView changePassword(String oldPassword, String newPassword, String confirmPassword) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/jsp/commons/showSucceed.jsp");
        mav.addObject("msg", "密码修改成功");
        mav.addObject("redirect", "/identity/profile");
        //查询当前的用户信息
        return mav;
    }
}
