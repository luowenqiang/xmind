package cn.org.xmind.identity.controller;

import cn.org.xmind.identity.db.entity.User;
import cn.org.xmind.identity.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rodney
 */
@Controller
@RequestMapping(value = "/identity/register")
public class RegisterController {

    @Resource
    private UserService userService;

    /**
     * 获取注册页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "identity/register";
    }

    /**
     * 完成注册功能
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(
            @Valid User user,
            BindingResult validateResult,
            @RequestParam("confirmPassword") String confirmPassword) {
        ModelAndView mav = new ModelAndView();
        if (validateResult.hasErrors()) {
            //如果数据校验有异常，回到注册界面，并且显示错误信息
            List<FieldError> errors = validateResult.getFieldErrors();
            for (FieldError error : errors) {
                mav.addObject("error_field_" + error.getField(), error.getDefaultMessage());
            }
            mav.addObject("validateResult", validateResult);
            mav.setViewName("identity/register");
            return mav;
        }
        if (!user.getPassword().equals(confirmPassword)) {
            //两次输入的密码不一致，不允许注册
            //throw new IllegalArgumentException("两次输入的密码不一样");
            mav.addObject("confirmPassword", "两次输入的密码不一样");
            mav.setViewName("identity/register");
            return mav;
        }
        try {
            userService.add(user);
            mav.addObject("email", user.getEmail());
        } catch (IllegalArgumentException ex) {
            mav.addObject("illegalArgumentException", ex);
            mav.setViewName("identity/register");
            return mav;
        }
        //mav.setViewName("redirect:/login");
        mav.setViewName("identity/unactivated");
        return mav;
    }
}
