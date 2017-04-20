package com.czm.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by chen zhan mei  on 2017/2/16.
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DruidProperties {
    private String url;
    private String username;
    private String password;
    private int maxActive;
    private int minIdle;
    private int initialSize;
    private boolean testOnBorrow;
}
