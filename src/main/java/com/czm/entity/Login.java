package com.czm.entity;

import javax.persistence.*;

public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String passworod;

    private Byte age;

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
     * @return passworod
     */
    public String getPassworod() {
        return passworod;
    }

    /**
     * @param passworod
     */
    public void setPassworod(String passworod) {
        this.passworod = passworod;
    }

    /**
     * @return age
     */
    public Byte getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Byte age) {
        this.age = age;
    }
}