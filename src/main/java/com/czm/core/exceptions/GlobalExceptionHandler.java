package com.czm.core.exceptions;

import com.czm.core.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by CHENZHANMEI
 * on 2017/6/5.
 * 用于把自定义的异常返回到前段页面 ;
 * 用于进行异常处理!
 */
@ControllerAdvice
public class GlobalExceptionHandler implements ResponseMessage {
    @Override
    public void sendMessage(HttpServletResponse response, String message) {
        sendResponseMessage(response, message);
    }


    /**
     * 自定义的异常返回到页面!
     *
     * @param response
     * @param e
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void myExceptionHandler(HttpServletResponse response, Exception e) {

        sendResponseMessage(response, e.getMessage());
    }

    /***
     *系统异常,返回到页面!
     * @param response
     * @param e
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void exceptionHandler(HttpServletResponse response, Exception e) {

        sendResponseMessage(response, "系统开小差了!稍后再试!" + e.getMessage());
    }

}
