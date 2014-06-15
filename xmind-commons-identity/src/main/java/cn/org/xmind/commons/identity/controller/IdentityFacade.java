package cn.org.xmind.commons.identity.controller;

import cn.org.xmind.commons.identity.db.entity.User;
import cn.org.xmind.commons.identity.rest.RegisterService;
import cn.org.xmind.commons.identity.rest.vo.RegisterResult;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("identity/register");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ModelAndView register(@RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/identity/login.jsp");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (!user.getPassword().equals(confirmPassword)) {
            //两次输入的密码不一致，不允许注册
            //throw new IllegalArgumentException("两次输入的密码不一样");
            mav.addObject("confirmPassword", "两次输入的密码不一样");
            mav.setViewName("identity/register");
            return mav;
        }
        RegisterResult result = this.registerService.post(user);
        mav.addObject("result", result);
        mav.setViewName("message");
        return mav;
    }
}
