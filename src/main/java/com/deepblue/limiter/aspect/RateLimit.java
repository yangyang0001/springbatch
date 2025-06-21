package com.deepblue.limiter.aspect;

import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    RateType mode() default RateType.OVERALL;

    long rate() default 5L;

    long interval() default 1L;

    RateIntervalUnit unit() default org.redisson.api.RateIntervalUnit.SECONDS;

    String key() default "";

}
