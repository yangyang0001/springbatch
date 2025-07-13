//package com.deepblue.springbatch.chapter_09;
//
//import jakarta.annotation.Resource;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableBatchProcessing
//public class MineDemoJob {
//
//    @Resource
//    private MineWriter mineWriter;
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
//    public Job readerJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("readerJob", jobRepository)
//                .start(readerStep(jobRepository, transactionManager))
//                .build();
//    }
//
//    @Bean
//    public Step readerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("readerStep", jobRepository)
//                .<String, String>chunk(5, transactionManager)
//                .reader(getMineReader())
//                .writer(mineWriter)
//                .build();
//    }
//
//    @Bean
//    public ItemReader<String> getMineReader() {
//        List<String> list = Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
//        return new MineReader(list);
//    }
//}
