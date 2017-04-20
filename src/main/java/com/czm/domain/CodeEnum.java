package com.czm.domain;

/**
 * Created by CHENZHANMEI
 * on 2017/3/17.
 */
public enum CodeEnum {

    Success(200, "Success"),

    Fail(400, "Fail");

    private Integer code;
    private String name;

    CodeEnum(Integer code, String name) {
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

    //
//    private static final Map<Integer, String> MAP = new HashMap<>();
//
//    static {
//        for (CodeEnum codeEnum : CodeEnum.values())
//            MAP.put(codeEnum.getCode(), codeEnum.getName());
//    }

}
