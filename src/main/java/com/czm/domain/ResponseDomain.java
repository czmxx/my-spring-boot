package com.czm.domain;

import java.io.Serializable;

/**
 * Created by CHENZHANMEI on 2017/3/16.
 */
public class ResponseDomain implements Serializable {

    private Integer code;

    private String message;

    private Object object;


    public ResponseDomain(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public ResponseDomain(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDomain(Integer code, Object object) {
        this.code = code;
        this.object = object;
    }

    public ResponseDomain(Integer code) {
        this.code = code;
    }

    public ResponseDomain(Object object) {
        this.object = object;
    }


    public boolean isSuccess() {
        return this.code != null && this.code.equals(CodeEnum.Success.getCode());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
