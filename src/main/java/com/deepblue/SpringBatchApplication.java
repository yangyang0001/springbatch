package com.deepblue;

import com.xxl.job.core.log.XxlJobFileAppender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Field;


@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBatchApplication {

    public static void main(String[] args) {
        // 启动前修改 logBasePath
        Field field = null;
        try {
            field = XxlJobFileAppender.class.getDeclaredField("logBasePath");
            field.setAccessible(true);
            field.set(null, "/Users/yangjianwei/IdeaProjects/springbatch/logs/xxl-job");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(SpringBatchApplication.class, args);
    }

}

