package cn.org.xmind.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rodney
 */
@Controller
@RequestMapping(value = "/identity/profile/security")
public class ProfileSecurityController {

    /**
     * 显示修改密码的界面
     *
     * @return
     */
    @RequestMapping(value = "/changePassword", method = {RequestMethod.GET})
    public ModelAndView changePassword() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("identity/security/changePassword");
        //查询当前的用户信息
        return mav;
    }

    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST})
    public ModelAndView changePassword(String oldPassword, String newPassword, String confirmPassword) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/WEB-INF/jsp/commons/showSucceed.jsp");
        mav.addObject("msg", "密码修改成功");
        mav.addObject("redirect", "/identity/profile");
        //查询当前的用户信息
        return mav;
    }

    @RequestMapping(value = "/log", method = {RequestMethod.GET})
    public ModelAndView log() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("identity/security/log");
        //查询当前的用户信息
        return mav;
    }
}
