package com.deepblue.limiter.controller;

import com.deepblue.limiter.entity.LimitRule;
import com.deepblue.limiter.aspect.RateLimit;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
public class RedisLimitController {

    @Resource
    private RedissonClient redissonClient;

    private AtomicLong counter = new AtomicLong(0);

    private SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置限流
     * @return
     */
    @RateLimit(key = "redis:limit", mode = RateType.OVERALL, rate = 5L, interval = 1, unit = RateIntervalUnit.MINUTES)
    @RequestMapping("/redisLimit")
    public String redisLimit() {
        try {
            counter.addAndGet(1);
            System.out.println("time is :" + defaultDateFormat.format(new Date()) + " --------- redis limit counter = " + counter.get());
            return counter.get() + "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 修改限流参数
     * @param limitRule
     * @return
     */
    @RequestMapping("/setRate")
    public String setRate(@RequestBody LimitRule limitRule) {
        try {
            String key = limitRule.getRateKey();
            RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
            rateLimiter.setRate(RateType.valueOf(limitRule.getRateMode()), limitRule.getRateRate(), limitRule.getRateInterval(), RateIntervalUnit.valueOf(limitRule.getRateUnit()));

            System.out.println("time is :" + defaultDateFormat.format(new Date()));
            return "success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
