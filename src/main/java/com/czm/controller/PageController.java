package com.czm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2017/3/5.
 */
@Controller("/page")
public class PageController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "/web/login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "/web/register";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/load")
    public String load() {
        return "/web/loading";
    }

}
