package cn.org.xmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LuoWenqiang
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/list")
    public String list() {
        return "list";
    }
}
