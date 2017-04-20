package com.czm.core.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CHENZHANMEI on 2017/3/11.
 */
@Target(ElementType.METHOD) //作用域
@Retention(RetentionPolicy.RUNTIME) //作用时间
public @interface EnableRedisCache {

    String key();

    String value();

    int expireTime()  default 3600;
}
