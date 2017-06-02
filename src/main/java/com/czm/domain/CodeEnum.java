package com.czm.domain;

/**
 * Created by CHENZHANMEI
 * on 2017/3/17.
 */
public enum CodeEnum {

    Success("Success", 200),

    Fail("Fail", 400);

    private Integer code;
    private String name;

    CodeEnum(String name, Integer code) {
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
