package com.yupi.springbootinit.manager;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import org.apache.lucene.store.RateLimiter;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 提供RedissonLimiter 限流基础服务
 */
@Service
public class RedisLimiterManager {
    @Resource
    private RedissonClient redissonClient;

    /**
     * 限流操作
     * @param key 区分不同的限流器比如不同的用户id应该分别统计
     */
    public void daRateLimit(String key){

        //创建一个名称为user_limiter的连六七，每次最多访问2次
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL,2, 1, RateIntervalUnit.SECONDS);
        //当一个操作来了后，获取一个令牌
        boolean canDo=rateLimiter.tryAcquire(1);
        if(!canDo){
            //拒绝访问
            throw new BusinessException(ErrorCode.TOO_MANY_REQUESTS);
        }
    }
}
