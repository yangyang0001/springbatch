package com.deepblue.springbatch.controller;


import com.deepblue.springbatch.common.ErrorCodeEnum;
import com.deepblue.springbatch.common.MineResponse;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SpringBatchController {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    @Qualifier("helloJob")
    private Job helloJob;

    @PostMapping("/run")
    public MineResponse<String> run(@RequestBody List<String> dataList) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // 防止重复执行
                .addJobParameter("dataList", dataList , List.class)
                .toJobParameters();

        try {
            jobLauncher.run(helloJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        MineResponse<String> response = new MineResponse<>();
        response.setCode(ErrorCodeEnum.E_0000.getCode())
                .setMessage(ErrorCodeEnum.E_0000.getMessage())
                .setResult("触发任务成功!");
        return response;
    }
}
