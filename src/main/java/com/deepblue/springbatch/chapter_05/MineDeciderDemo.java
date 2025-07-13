//package com.deepblue.springbatch.chapter_05;
//
//import jakarta.annotation.Resource;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//public class MineDeciderDemo {
//
//    @Resource
//    private MineDecider mineDecider;
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job deciderDemoJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(deciderDemoJob, params);
//        };
//    }
//
//    @Bean
//    public Job deciderDemoJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("deciderDemoJob", jobRepository)
//                .start(mineDecider)
//                .from(mineDecider).on("even").to(step1(jobRepository, transactionManager))
//                .from(mineDecider).on("odd").to(step2(jobRepository, transactionManager))
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("step1", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(new ItemReader<String>() {
//                    private int count = 0;
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <=0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("even")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        System.out.println("step1 started");
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        System.out.println("step1 completed");
//                        return ExitStatus.COMPLETED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("step2", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(new ItemReader<String>() {
//                    private int count = 0;
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <=0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("odd")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        System.out.println("step2 started");
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        System.out.println("step2 completed");
//                        return ExitStatus.COMPLETED;
//                    }
//                })
//                .build();
//    }
//
//
//
//
//
//
//
//}
