package com.czm.core.util;

import com.czm.domain.CodeEnum;
import com.czm.domain.ResponseDomain;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CHENZHANMEI on 2017/6/5.
 * 用于对消息进行封装,返回前段
 */
public interface ResponseMessage {


    void sendMessage(HttpServletResponse response, String message);


    default void sendResponseMessage(HttpServletResponse response, String message) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseDomain responseDomain = new ResponseDomain(CodeEnum.Fail.getCode(), message);
            //objectMapper 把对象转换为json
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseDomain));
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println();
        }
    }

}
