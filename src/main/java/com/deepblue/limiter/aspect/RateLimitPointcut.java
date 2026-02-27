package com.deepblue.limiter.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class RateLimitPointcut {

    @Pointcut("@annotation(RateLimit)")
    public void RateLimit(){}

}
