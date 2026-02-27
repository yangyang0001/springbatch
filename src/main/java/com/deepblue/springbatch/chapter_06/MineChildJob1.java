//package com.deepblue.springbatch.chapter_06;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class MineChildJob1 {
//
//    @Bean
//    public Job childJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("childJob1", jobRepository)
//                .start(childStep1(jobRepository, transactionManager))
//                .build();
//    };
//
//    @Bean
//    public Step childStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("childStep1", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(new ItemReader<String>() {
//                    private int count = 0;
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <= 0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("childStep1")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        StepExecutionListener.super.beforeStep(stepExecution);
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        return StepExecutionListener.super.afterStep(stepExecution);
//                    }
//                })
//                .build();
//    }
//
//}
