package com.deepblue.handler.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class FiltePointcut {

    @Pointcut("@annotation(com.deepblue.handler.annotation.FilteBlack)")
    public void FilteBlack(){}

    @Pointcut("@annotation(com.deepblue.handler.annotation.FilteWhite)")
    public void FilteWhite(){}

    @Pointcut("@annotation(com.deepblue.handler.annotation.FilteBoth)")
    public void FilteBoth(){}
}
