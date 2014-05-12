package cn.org.xmind.commons.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rodney
 */
@Controller
@RequestMapping(value = "/identity/profile")
public class ProfileController {

    /**
     * 显示用户的个人信息
     *
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = {RequestMethod.GET})
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("identity/profile");
        //查询当前的用户信息
        return mav;
    }
}
