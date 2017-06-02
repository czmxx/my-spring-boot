package com.czm.entity;

import javax.persistence.*;

public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_info")
    private String logInfo;

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
     * @return log_info
     */
    public String getLogInfo() {
        return logInfo;
    }

    /**
     * @param logInfo
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
}