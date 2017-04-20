package com.czm.entity;

import javax.persistence.*;

@Table(name = "city_mobile")
public class CityMobile {
    @Id
    @Column(name = "mobile_code")
    private String mobileCode;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    /**
     * @return mobile_code
     */
    public String getMobileCode() {
        return mobileCode;
    }

    /**
     * @param mobileCode
     */
    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    /**
     * @return city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return city_name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}