package com.deepblue.limiter.aspect;


import com.alibaba.fastjson.JSON;
import com.deepblue.limiter.config.RateLimiterCacheConfig;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

@Aspect
@Component
public class RateLimitAspect {

    @Resource
    private RateLimiterCacheConfig cacheConfig;

    @Around("RateLimitPointcut.RateLimit()")
    public Object around(ProceedingJoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimit annotation = method.getAnnotation(RateLimit.class);
        String key = annotation.key();
        RateType mode = annotation.mode();
        Long rate = annotation.rate();
        long interval = annotation.interval();
        RateIntervalUnit unit = annotation.unit();

        Object[] args = point.getArgs();
        Object result = null;

        try {
            RRateLimiter rateLimiter = cacheConfig.getRateLimiter(key);
            if(StringUtils.isNotBlank(key) && mode != null && rate != 0 && interval != 0 && unit != null) {
                rateLimiter.trySetRate(mode, rate,interval, unit);
            }

            rateLimiter.acquire();
            result = point.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result;
    }
}
