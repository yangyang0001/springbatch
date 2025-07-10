//package com.deepblue.springbatch.chapter_04;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableAutoConfiguration
//public class MineDemoSplit {
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
//    public Job splitDemoJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("splitDemoJob", jobRepository)
//                .start(flow1(jobRepository, transactionManager))
//                // 下面两行是 并发执行的代码, 异步执行 flow2
//                .split(new SimpleAsyncTaskExecutor())
//                .add(flow2(jobRepository, transactionManager))
//                .end()
//                .build();
//    }
//
//
//    @Bean
//    public Flow flow1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new FlowBuilder<Flow>("flow1")
//                .start(step1(jobRepository, transactionManager))
//                .end();
//
//    }
//
//    @Bean
//    public Flow flow2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new FlowBuilder<Flow>("flow2")
//                .start(step2(jobRepository, transactionManager))
//                .next(step3(jobRepository, transactionManager))
//                .end();
//
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
//                .writer(items -> items.forEach(item -> System.out.println("Hello World Step1")))
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
//                }).build();
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
//                .writer(items -> items.forEach(item -> System.out.println("Hello World Step2")))
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
//                }).build();
//    }
//
//    @Bean
//    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("step3", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(new ItemReader<String>() {
//                    private int count = 0;
//
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return count <= 0 ? String.valueOf(count++) : null;
//                    }
//                })
//                .writer(items -> items.forEach(item -> System.out.println("Hello World Step3")))
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        System.out.println("step3 started");
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        System.out.println("step3 completed");
//                        return ExitStatus.COMPLETED;
//                    }
//                }).build();
//    }
//
//
//}
