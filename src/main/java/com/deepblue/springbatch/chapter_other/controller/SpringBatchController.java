package com.deepblue.springbatch.chapter_other.controller;


import com.deepblue.exception.common.ErrorCodeEnum;
import com.deepblue.exception.common.MineResponse;
import com.deepblue.springbatch.chapter_other.entity.MineJobParam;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试 HTTP POST 调用 SpringBatch
 */
@RestController
@RequestMapping("/springbatch")
public class SpringBatchController {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    @Qualifier("helloJob")
    private Job helloJob;

    @PostMapping("/helloJob")
    public MineResponse<String> helloJob(@RequestBody MineJobParam param) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // 防止重复执行
                .addJobParameter("dataList", param.getDataList() , List.class)
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
