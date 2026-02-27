//package com.deepblue.springbatch.chapter_06;
//
//import jakarta.annotation.Resource;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//public class MineParentJob {
//
//
//    @Resource
//    private Job childJob1;
//
//    @Resource
//    private Job childJob2;
//
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job parentJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(parentJob, params);
//        };
//    }
//
//    @Bean
//    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("parentJob", jobRepository)
//                .start(childJobStep1(jobRepository, transactionManager))
//                .next(childJobStep2(jobRepository, transactionManager))
//                .build();
//
//    }
//
//    @Bean
//    public Step childJobStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJobStep1", jobRepository))
//                .job(childJob1)
//                .build();
//    }
//
//    @Bean
//    public Step childJobStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJobStep2", jobRepository))
//                .job(childJob2)
//                .build();
//    }
//}
