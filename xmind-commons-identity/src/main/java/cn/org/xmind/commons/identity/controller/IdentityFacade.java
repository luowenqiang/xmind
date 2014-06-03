package cn.org.xmind.commons.identity.controller;

import cn.org.xmind.commons.identity.db.entity.User;
import cn.org.xmind.commons.identity.rest.RegisterService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author LuoWenqiang
 */
@Controller()
@RequestMapping(value = "/identity")
public class IdentityFacade {

    @Resource
    private RegisterService registerService;

    @RequestMapping(value = "/register")
    public ModelAndView register(@RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/identity/login.jsp");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        this.registerService.post(user);
        return mav;
    }
}
