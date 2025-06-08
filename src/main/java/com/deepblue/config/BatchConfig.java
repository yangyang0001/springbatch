package com.deepblue.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Configuration
public class BatchConfig {


    /**
     * 创建 JOB
     * @param jobRepository
     * @param step1
     * @return
     */
    @Bean
    public Job importJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        return new ListItemReader<>(Arrays.asList("apple", "banana", "orange"));
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return item -> item.toUpperCase();
    }

    @Bean
    public ItemWriter<String> writer() {

        return items -> items.forEach(System.out::println);
    }

}
