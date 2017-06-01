package com.czm.entity;

import javax.persistence.*;

@Table(name = "profile_company")
public class ProfileCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String street;

    private String country;

    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    private String vip;

    private String stays;

    private String communications;

    private String number;

    @Column(name = "other_communications")
    private String otherCommunications;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "business_phone")
    private String businessPhone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return state
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
     * @return postal_code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
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
     * @return stays
     */
    public String getStays() {
        return stays;
    }

    /**
     * @param stays
     */
    public void setStays(String stays) {
        this.stays = stays;
    }

    /**
     * @return communications
     */
    public String getCommunications() {
        return communications;
    }

    /**
     * @param communications
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

    /**
     * @return other_communications
     */
    public String getOtherCommunications() {
        return otherCommunications;
    }

    /**
     * @param otherCommunications
     */
    public void setOtherCommunications(String otherCommunications) {
        this.otherCommunications = otherCommunications;
    }

    /**
     * @return email_address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return fax_number
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * @param faxNumber
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * @return business_phone
     */
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * @param businessPhone
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    /**
     * @return mobile_phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}