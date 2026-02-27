//package com.deepblue.springbatch.chapter_07;
//
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MineStepListener implements StepExecutionListener {
//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        System.out.println("MineStepListener ------------------------:" + stepExecution.getStepName() + ", beforeStep invoked!");
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        System.out.println("MineStepListener ------------------------:" + stepExecution.getStepName() + ", afterStep invoked!");
//        return StepExecutionListener.super.afterStep(stepExecution);
//    }
//}
