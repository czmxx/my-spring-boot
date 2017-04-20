package com.czm.core.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by CHENZHANMEI on 2017/3/12.
 */
public class RedistUitl {

    private final static RedisTemplate<String, Object> redisTemplate;

    private final static int timeout = 30;

    private final static TimeUnit minutes = TimeUnit.MINUTES;

    static {
        redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate");
    }

    public static void set(String key, Object o) {
        redisTemplate.opsForValue().set(key, o);
        redisTemplate.expire(key, timeout, minutes);
    }


    public static Object get(String key) {

        return redisTemplate.opsForValue().get(key);
    }

    public static void remove(String key) {
        redisTemplate.delete(key);
    }


}
