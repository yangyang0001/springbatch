package com.deepblue.springbatch.chapter_02;

import ch.qos.logback.classic.spi.Configurator;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class MineDemoJob {

    @Bean
    public CommandLineRunner runJob(JobLauncher jobLauncher, Job demoJob) {
        return args -> {
            JobParameters params = new JobParametersBuilder()
                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
                    .toJobParameters();
            jobLauncher.run(demoJob, params);
        };
    }

    @Bean
    public Job demoJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("demoJob", jobRepository)
                .start(step1(jobRepository, transactionManager))
                .next(step2(jobRepository, transactionManager))
                .next(step3(jobRepository, transactionManager))
                .build();


    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .chunk(5, transactionManager)
                .reader(simpleReader())
                .writer(items -> items.forEach(item -> System.out.println("Step1 execute, Hello World")))
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("Step1 completed");
                        return ExitStatus.COMPLETED;
                    }

                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("Step1 started");
                    }
                })
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .chunk(5, transactionManager)
                .reader(simpleReader())
                .writer(items -> items.forEach(item -> System.out.println("Step2 execute, Hello World")))
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("Step2 completed");
                        return ExitStatus.COMPLETED;
                    }

                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("Step2 started");
                    }
                })
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step3", jobRepository)
                .chunk(5, transactionManager)
                .reader(simpleReader())
                .writer(items -> items.forEach(item -> System.out.println("Step3 execute, Hello World")))
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("Step3 completed");
                        return ExitStatus.COMPLETED;
                    }

                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("Step3 started");
                    }
                })
                .build();
    }

    public ItemReader<String> simpleReader() {
        return new ItemReader<String>() {
            private int index = 0;

            @Override
            public String read() {
                return index <= 0 ? String.valueOf(index++) : null;
            }
        };
    }


}
