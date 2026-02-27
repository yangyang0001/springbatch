//package com.deepblue.springbatch.chapter_07;
//
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MineJobListener implements JobExecutionListener {
//
//    @Override
//    public void beforeJob(JobExecution jobExecution) {
//        System.out.println("MineJobListener ------------------------:" + jobExecution.getJobInstance().getJobName() + ", beforeJob invoked!");
//    }
//
//    @Override
//    public void afterJob(JobExecution jobExecution) {
//        System.out.println("MineJobListener ------------------------:" + jobExecution.getJobInstance().getJobName() + ", afterJob invoked!");
//    }
//}
