package com.deepblue.xxljob;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XxlJobConfig {

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses("http://192.168.188.1:8888/xxl-job-admin");
        executor.setAppname("springbatch");
        executor.setIp("192.168.188.1");
        executor.setPort(9999);
        executor.setLogPath("/Users/yangjianwei/IdeaProjects/springbatch/logs/xxl-job");
        executor.setLogRetentionDays(30);
        return executor;
    }
}
