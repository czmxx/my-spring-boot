package com.czm;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by CHENZHANMEI on 2017/3/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedistConfTest {


    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void set() {
        redisTemplate.opsForValue().set("key1", new Date() + "" + System.currentTimeMillis());

        Object key1 = redisTemplate.opsForValue().get("key1");
        Assert.assertNotNull(key1);
    }

}
