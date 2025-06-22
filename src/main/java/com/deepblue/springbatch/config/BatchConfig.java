package com.deepblue.springbatch.config;


import com.deepblue.springbatch.reader.MineItemReader;
import jakarta.annotation.Resource;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Resource
    private MineItemReader mineItemReader;

    @Bean
    public CommandLineRunner run(JobLauncher jobLauncher, Job helloJob) {
        return args -> {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // 防止重复执行
                    .addJobParameter("dataList", List.of("zhangsan", "lisi", "wangwu") , List.class)
                    .toJobParameters();
            jobLauncher.run(helloJob, jobParameters);
        };
    }

    /**
     * 创建 JOB
     * @param jobRepository
     * @param step1
     * @return
     */
    @Bean
    public Job helloJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("helloJob", jobRepository)
                .start(step1)
                .next(step1)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        JobExecutionListener.super.beforeJob(jobExecution);
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        JobExecutionListener.super.afterJob(jobExecution);
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("helloStep", jobRepository)
                .<String, String>chunk(5, transactionManager)
//                .reader(reader())
                .reader(mineItemReader)
                .processor(processor())
                .writer(writer())
                .listener(new StepExecutionListener() {
                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return StepExecutionListener.super.afterStep(stepExecution);
                    }

                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        StepExecutionListener.super.beforeStep(stepExecution);
                    }
                })
                .build();
    }

    @Bean
    public ItemReader<String> reader() {
        // 设置固定的 ItemReader
        return new ListItemReader<>(List.of("zhangsan", "lisi", "wangwu"));
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
