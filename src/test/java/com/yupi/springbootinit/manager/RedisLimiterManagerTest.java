package com.yupi.springbootinit.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RedisLimiterManagerTest {

    @Resource
    private RedisLimiterManager redisLimiterManager;
    @Test
    void daRateLimit() {
        String userId= "1";
        for (int i = 0; i < 5; i++) {
            redisLimiterManager.daRateLimit(userId);
            System.out.println("当前第"+i+"次请求");
        }
    }
}