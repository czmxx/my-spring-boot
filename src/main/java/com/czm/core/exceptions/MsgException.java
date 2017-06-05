package com.czm.core.exceptions;


/**
 * Created by CHENZHANMEI on 2017/2/21.
 * 用于底层直接报异常抛到页面
 */
public class MsgException extends RuntimeException {

    public MsgException(String message) {
        super(message);
    }
}
