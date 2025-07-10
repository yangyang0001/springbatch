//package com.deepblue.springbatch.chapter_03;
//
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.FlowJobBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.job.flow.Flow;
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
//public class MineDemoFlow {
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job flowDemoJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(flowDemoJob, params);
//        };
//    }
//
//    @Bean
//    public Job flowDemoJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("flowDemoJob", jobRepository)
//                .start(flow1(jobRepository, transactionManager))
//                .end()      //此处必须有 end()
//                .build();
//    }
//
//
//    /**
//     * Flow 是 Step 的集合, 可以复用
//     *
//     * @param jobRepository
//     * @param transactionManager
//     * @return
//     */
//    @Bean
//    public Flow flow1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new FlowBuilder<Flow>("flow1")
//                .start(step1(jobRepository, transactionManager))
//                .next(step2(jobRepository, transactionManager))
//                .end();
//    }
//
//    @Bean
//    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("step1", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(new ItemReader<String>() {
//                    private int count = 0;
//
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <= 0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("Hello World")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        System.out.println("step1 completed");
//                        return ExitStatus.COMPLETED;
//                    }
//
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        System.out.println("step1 started");
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
//
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <= 0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("Welcome")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        System.out.println("step2 completed");
//                        return ExitStatus.COMPLETED;
//                    }
//
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        System.out.println("step2 started");
//                    }
//                })
//                .build();
//    }
//}
