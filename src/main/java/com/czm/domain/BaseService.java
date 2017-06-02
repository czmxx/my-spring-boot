package com.czm.domain;

import com.czm.core.exceptions.MyException;

/**
 * Created by CHENZHANMEI on 2017/6/2.
 */
public class BaseService {


    protected ResponseDomain fail() {
        return new ResponseDomain(CodeEnum.Fail.getCode());
    }

    protected ResponseDomain success() {
        return new ResponseDomain(CodeEnum.Success.getCode());
    }

    protected ResponseDomain success(Object obj) {
        return new ResponseDomain(obj);
    }

    protected ResponseDomain fail(String message) {
        return new ResponseDomain(CodeEnum.Fail.getCode(), message);
    }

    protected ResponseDomain fail(CodeEnum code, String message) {
        return new ResponseDomain(code.getCode(), message);
    }

    protected ResponseDomain result(Boolean value) {
        return new ResponseDomain(value ? CodeEnum.Success.getCode() : CodeEnum.Fail.getCode());
    }

    protected ResponseDomain validResult(ResponseDomain result) {
        if (!result.getCode().equals(CodeEnum.Success.getCode()))
            throw new MyException(result.getMessage());
        return result;
    }

    protected ResponseDomain validResult(Boolean value) {
        if (!value)
            throw new MyException(CodeEnum.Fail.getName());

        return new ResponseDomain(CodeEnum.Success.getCode());
    }

    protected ResponseDomain validResult(Boolean value, String successMsg, String fileMsg) {
        if (value)
            return new ResponseDomain(CodeEnum.Success.getCode(), successMsg);

        return new ResponseDomain(CodeEnum.Fail.getCode(), fileMsg);
    }


}
