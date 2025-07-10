package com.deepblue.springbatch.chapter_05;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class MineDecider implements JobExecutionDecider {

    private int count = 0;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if (count++ % 2 == 0) {
            // 偶数
            return new FlowExecutionStatus("even");
        } else {
            // 奇数
            return new FlowExecutionStatus("odd");
        }
    }
}
