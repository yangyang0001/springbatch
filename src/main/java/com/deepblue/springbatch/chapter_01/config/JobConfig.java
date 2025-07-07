package com.deepblue.springbatch.chapter_01.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Bean
    public CommandLineRunner runJob(JobLauncher jobLauncher, Job helloWorldJob) {
        return args -> {
            JobParameters params = new JobParametersBuilder()
                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
                    .toJobParameters();
            jobLauncher.run(helloWorldJob, params);
        };
    }

    @Bean
    public Job helloWorldJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("helloWorldJob", jobRepository)
                .start(step1(jobRepository, transactionManager))
                .build();
    }

    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .chunk(5, transactionManager)
                .reader(simpleReader())
                .writer(items -> items.forEach(item -> System.out.println("Hello World: " + item)))
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("Step completed");
                        return ExitStatus.COMPLETED;
                    }

                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("Step started");
                    }
                })
                .build();
    }


    @Bean
    public ItemReader<String> simpleReader() {
        return new ItemReader<String>() {
            private final List<String> data = Arrays.asList("A", "B", "C");
            private int index = 0;

            @Override
            public String read() {
                return index < data.size() ? data.get(index++) : null;
            }
        };
    }

}
