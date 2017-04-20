package com.czm.domain;

import java.io.Serializable;

/**
 * Created by CHENZHANMEI on 2017/3/17.
 */
public class PageResponse extends ResponseDomain implements Serializable {

    private int total;

    public PageResponse(Integer code, String message, Object object, int total) {
        super(code, message, object);
        this.total = total;
    }

    public PageResponse(Integer code, String message, int total) {
        super(code, message);
        this.total = total;
    }

    public PageResponse(Integer code, Object object, int total) {
        super(code, object);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
