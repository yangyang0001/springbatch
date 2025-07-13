//package com.deepblue.springbatch.chapter_07;
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
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableBatchProcessing
//public class MineDemoJob {
//
//    @Resource
//    private MineJobListener mineJobListener;
//
//    @Resource
//    private MineChunkListener mineChunkListener;
//
//    @Resource
//    private MineStepListener mineStepListener;
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job listenerJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(listenerJob, params);
//        };
//    }
//
//    @Bean
//    public Job listenerJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("listenerJob", jobRepository)
//                .start(listenerStep(jobRepository, transactionManager))
//                .listener(mineJobListener)
//                .build();
//    }
//
//    @Bean
//    public Step listenerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("listenerStep", jobRepository)
//                .chunk(3, transactionManager)   // 每读取完 多少个数据, 进行 writer 操作!
//                .listener(mineChunkListener)
//                .reader(itemReader())
//                .writer(items -> items.forEach(item -> System.out.println("Hello " + item)))
//                .listener(mineStepListener)
//                .build();
//    }
//
//    @Bean
//    public ItemReader<String> itemReader() {
//        return new ListItemReader<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));
//    }
//
//}
