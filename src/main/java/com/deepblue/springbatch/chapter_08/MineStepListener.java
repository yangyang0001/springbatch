//package com.deepblue.springbatch.chapter_08;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MineStepListener implements StepExecutionListener {
//
//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        JobParameters jobParameters = stepExecution.getJobParameters();
//        System.out.println("MineStepListener beforeStep invoke, JobParameters: " + JSON.toJSONString(jobParameters));
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        JobParameters jobParameters = stepExecution.getJobParameters();
//        System.out.println("MineStepListener afterStep invoke, JobParameters: " + JSON.toJSONString(jobParameters));
//        return StepExecutionListener.super.afterStep(stepExecution);
//    }
//
//}
