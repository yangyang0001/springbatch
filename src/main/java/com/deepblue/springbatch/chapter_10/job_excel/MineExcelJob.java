package com.deepblue.springbatch.chapter_10.job_excel;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@EnableBatchProcessing
public class MineExcelJob {

    @Resource
    private ExcelItemReader excelItemReader;

    @Bean
    public CommandLineRunner runJob(JobLauncher jobLauncher, Job excelJob) {
        return args -> {
            JobParameters params = new JobParametersBuilder()
                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
                    .addString("filePath", "/Users/yangjianwei/IdeaProjects/springbatch/src/main/java/com/deepblue/springbatch/chapter_10/job_excel/用户.xlsx") // 用时间戳保证唯一
                    .toJobParameters();
            jobLauncher.run(excelJob, params);
        };
    }

    @Bean
    public Job excelJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("excelJob", jobRepository)
                .start(excelStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step excelStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("excelStep", jobRepository)
                .chunk(10, transactionManager)
                .reader(excelItemReader)
                .writer(items -> {
                    log.info("本批次处理数量 --------------------------------------------------------------------------: {}", items.size());
                    items.forEach(item -> log.info("--------------------------------------------------------------------------数据: {}", JSON.toJSONString(item)));
                })
                .build();
    }
}
