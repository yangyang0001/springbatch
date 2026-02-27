package com.deepblue.handler.aspect;

import com.alibaba.fastjson.JSON;
import com.deepblue.handler.annotation.FilteBlack;
import com.deepblue.handler.common.SiteIdEnum;
import com.deepblue.handler.filter.FilteBlackHandler;
import com.deepblue.test.entity.Mine;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(5)
public class FilteBlackAspect {

    @Resource
    private FilteBlackHandler filteBlackHandler;

    @Around("com.deepblue.handler.pointcut.FiltePointcut.FilteBlack() && @annotation(filteBlack)")
    public Object around(ProceedingJoinPoint point, FilteBlack filteBlack) throws Throwable {

        boolean openFlag = filteBlack.openFlag();
        SiteIdEnum siteId = filteBlack.siteId();

        if (openFlag && SiteIdEnum.BP.equals(siteId)) {
            Object[] args = point.getArgs();
            Object result = null;

            try {
                System.out.println("FilteBlackHandler invoke before, args[0] = " + JSON.toJSONString(args[0]) + ", result: " + JSON.toJSONString(result));
                args[0] = filteBlackHandler.doFilter((List<Mine>) args[0]);
                System.out.println("FilteBlackHandler invoke  after, args[0] = " + JSON.toJSONString(args[0]) + ", result: " + JSON.toJSONString(result));
                result = point.proceed(args);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            return result;
        } else {

            Object result = null;
            try {
                result = point.proceed(point.getArgs());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return result;

        }



    }
}
