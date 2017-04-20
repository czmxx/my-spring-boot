package com.czm.core.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 * Created by Administrator on 2017/2/18.
 */
@Configuration
@WebListener
public class MyServletContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger("MyServletContextListener");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        log.info("...........contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        log.info(".................contextDestroyed");
    }
}
