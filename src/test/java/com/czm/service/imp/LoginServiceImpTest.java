package com.czm.service.imp;

import com.czm.BaseTest;
import com.czm.domain.ResponseDomain;
import com.czm.service.LoginService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by CHENZHANMEI on 2017/6/2.
 */
public class LoginServiceImpTest extends BaseTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void login() throws Exception {

        ResponseDomain 张珊 = loginService.login("张珊", "123456", null);
    }

}