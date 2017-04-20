package com.czm.domain;

/**
 * Created by CHENZHANMEI on 2017/3/17.
 */
public abstract class BaseResponse {


    protected ResponseDomain success() {
        return new ResponseDomain(CodeEnum.Success.getCode());
    }

    protected ResponseDomain success(String message) {
        return new ResponseDomain(CodeEnum.Success.getCode(), message);
    }


    protected ResponseDomain success(String message, Object o) {
        return new ResponseDomain(CodeEnum.Success.getCode(), o);
    }

    protected ResponseDomain fail() {
        return new ResponseDomain(CodeEnum.Fail.getCode());
    }


}
