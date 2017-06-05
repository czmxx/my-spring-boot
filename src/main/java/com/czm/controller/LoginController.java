package com.czm.controller;

import com.czm.domain.ResponseDomain;
import com.czm.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CHENZHANMEI on 2017/6/2.
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseDomain login(@RequestParam String keyword,
                                @RequestParam String password, HttpServletRequest request) {

        return loginService.login(keyword, password, request);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseDomain register(@RequestParam(required = false) String mobile,
                                   @RequestParam(required = false) String email,
                                   @RequestParam String nickName,
                                   @RequestParam(required = false) String headImageUrl,
                                   @RequestParam String password) {

        return loginService.register(mobile, email, nickName, headImageUrl, password);
    }


}
