package com.czm.entity;

import javax.persistence.*;

public class Infotable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String desc1;

    private String remack;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
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
     * @return desc1
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * @param desc1
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    /**
     * @return remack
     */
    public String getRemack() {
        return remack;
    }

    /**
     * @param remack
     */
    public void setRemack(String remack) {
        this.remack = remack;
    }
}