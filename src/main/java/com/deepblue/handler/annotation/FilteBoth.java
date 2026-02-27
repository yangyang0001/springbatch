package com.deepblue.handler.annotation;

import com.deepblue.handler.common.SiteIdEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@FilteBlack        // 组合注解包含黑名单
@FilteWhite        // 组合注解包含白名单
public @interface FilteBoth {

    @AliasFor(annotation = FilteBlack.class, attribute = "openFlag")
    boolean blackOpen() default true;

    @AliasFor(annotation = FilteWhite.class, attribute = "openFlag")
    boolean whiteOpen() default true;

    // 共享 siteId，可分别传递下去
    @AliasFor(annotation = FilteBlack.class, attribute = "siteId")
    SiteIdEnum siteId() default SiteIdEnum.BP;
}


