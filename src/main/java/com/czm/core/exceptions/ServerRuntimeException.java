package com.czm.core.exceptions;

import java.io.Serializable;

/**
 * Created by chen zhan mei  on 2017/2/17.
 */
public class ServerRuntimeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1168987160778410810L;

    public ServerRuntimeException(String message) {
        super(message);
    }

}
