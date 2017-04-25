package com.czm.entity;

import javax.persistence.*;

@Table(name = "profile_company")
public class ProfileCompany {
    /**
     * 客史Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 城市
     */
    private String city;

    /**
     * 街道
     */
    private String street;

    /**
     * 国家
     */
    private String country;

    @Column(name = "State")
    private String state;

    /**
     * 邮编
     */
    @Column(name = "postal_code")
    private String postalCode;

    private String vip;

    /**
     * 联系方式
     */
    private String communications;

    private String number;

    /**
     * 获取客史Id
     *
     * @return id - 客史Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客史Id
     *
     * @param id 客史Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取街道
     *
     * @return street - 街道
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道
     *
     * @param street 街道
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return State
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取邮编
     *
     * @return postal_code - 邮编
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置邮编
     *
     * @param postalCode 邮编
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return vip
     */
    public String getVip() {
        return vip;
    }

    /**
     * @param vip
     */
    public void setVip(String vip) {
        this.vip = vip;
    }

    /**
     * 获取联系方式
     *
     * @return communications - 联系方式
     */
    public String getCommunications() {
        return communications;
    }

    /**
     * 设置联系方式
     *
     * @param communications 联系方式
     */
    public void setCommunications(String communications) {
        this.communications = communications;
    }

    /**
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}