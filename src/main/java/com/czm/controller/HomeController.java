package com.czm.controller;

import com.czm.entity.Login;
import com.czm.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chen zhan mei  on 2017/2/12.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CityService cityService;

    @PostMapping("/getAll")
    public List<Login> getLoginAll() {
        return this.cityService.getLoginAll();
    }

    @PostMapping("getOne/{id}")
    public Login getLoginById(@PathVariable Long id) {

        return this.cityService.getLoginById(id);
    }

    @PostMapping("/addLogin")
    public void addLogin() {
        Login login = new Login();
        login.setName("张三");
        login.setAge((byte) 20);
        login.setPassworod(System.currentTimeMillis() + "");
        this.cityService.addLogin(login);
    }

}
