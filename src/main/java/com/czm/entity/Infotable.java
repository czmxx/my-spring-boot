package com.czm.entity;

import javax.persistence.*;

public class Infotable {
    /**
     * id主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 描述
     */
    private String desc1;

    /**
     * 备注
     */
    private String remack;

    /**
     * 获取id主键
     *
     * @return id - id主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id主键
     *
     * @param id id主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return desc1 - 描述
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * 设置描述
     *
     * @param desc1 描述
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    /**
     * 获取备注
     *
     * @return remack - 备注
     */
    public String getRemack() {
        return remack;
    }

    /**
     * 设置备注
     *
     * @param remack 备注
     */
    public void setRemack(String remack) {
        this.remack = remack;
    }
}