//package com.deepblue.springbatch.chapter_10.job_mysql;
//
//import com.alibaba.fastjson.JSON;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class MySQLDBJob {
//
//    @Resource
//    private MySQLSingleReader mysqlSingleReader;
//
//    @Resource
//    private MySQLBatchReader mysqlBatchReader;
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job mysqlJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(mysqlJob, params);
//        };
//    }
//
//    @Bean
//    public Job mysqlJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("mysqlJob", jobRepository)
//                .start(mysqlStep(jobRepository, transactionManager))
//                .build();
//    }
//
//    @Bean
//    public Step mysqlStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("mysqlStep", jobRepository)
//                .chunk(1, transactionManager)
//                // .reader(mysqlSingleReader)
//                .reader(mysqlBatchReader)
//                .writer(items -> items.forEach(item -> {
//                    log.info("------------------------------------------------------------------------------ item is :{}" , JSON.toJSONString(item));
//                }))
//                .build();
//    }
//}
