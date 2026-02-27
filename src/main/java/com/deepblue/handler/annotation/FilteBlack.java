package com.deepblue.handler.annotation;

import com.deepblue.handler.common.SiteIdEnum;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilteBlack {

    boolean openFlag() default true;

    SiteIdEnum siteId() default SiteIdEnum.BP;
}
