package cn.org.xmind.identity.controller;

import cn.org.xmind.identity.exception.IllegalActiveCodeException;
import cn.org.xmind.identity.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rodney
 */
@Controller
@RequestMapping(value = "/identity/active")
public class AccountActiveController {

    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView active(@RequestParam(value = "activeCode", required = false) String activeCode) {
        ModelAndView mav = new ModelAndView();
        if (activeCode == null) {
            //请求参数没有携带激活码过来，返回到错误提示页面
            mav.addObject("msg", "无效请求，请从正确的路径访问网站，谢谢。");
            mav.setViewName("result");
        } else {
            try {
                userService.active(activeCode);
                //激活成功，显示成功界面，并且5秒后重定向到登陆界面
                mav.addObject("title", "激活成功");
                mav.addObject("msg", "激活成功");
                mav.addObject("redirect", "/login");
            } catch (IllegalActiveCodeException ex) {
                mav.addObject("title", "激活失败");
                mav.addObject("msg", ex.getLocalizedMessage());
                if (ex.getEmail() != null) {
                    mav.addObject("redirect", "/identity/active/resend?email=" + ex.getEmail());
                    mav.addObject("ex", ex);
                } else {
                    mav.addObject("msg", "无效激活码，请从邮件中复制完整的地址进行激活。");
                }
            }
        }
        mav.setViewName("result");
        return mav;
    }

    /**
     * 重新发送验证码
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "resend", method = RequestMethod.GET)
    public ModelAndView resend(@RequestParam(value = "email") String email) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
