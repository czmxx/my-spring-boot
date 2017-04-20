package com.czm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//启动事物管理 配合@@Transactional使用
@EnableTransactionManagement
@MapperScan(basePackages = "com.czm.mapper")
public class MySpringBootApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MySpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
