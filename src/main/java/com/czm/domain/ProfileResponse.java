package com.czm.domain;

import lombok.Data;

/**
 * Created by CHENZHANMEI on 2017/6/1.
 */
public class ProfileResponse {
    private String name;

    private String emailAddress;

    private String mobilePhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
