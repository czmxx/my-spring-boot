package com.czm.service;

import com.czm.domain.ResponseDomain;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CHENZHANMEI on
 * 2017/6/2.
 */
public interface LoginService {

    /**
     * @param keyword     邮件昵称或者手机号
     * @param password 密码
     * @param request
     * @return
     */
    ResponseDomain login(String keyword, String password, HttpServletRequest request);


}
