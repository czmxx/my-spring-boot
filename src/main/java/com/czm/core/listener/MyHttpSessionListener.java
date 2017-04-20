package com.czm.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session的创建与销毁
 * Created by Administrator on 2017/2/18.
 */
@Configuration
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger("myHttpSessionListener");

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("myHttpSessionListener +_________sessionCreated");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("myHttpSessionListener +sessionDestroyed");

    }
}
