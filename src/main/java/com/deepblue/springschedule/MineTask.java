package com.deepblue.springschedule;

import com.deepblue.async.service.CommonService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 测试定时任务
 */
@Component
public class MineTask {

    @Resource
    private List<CommonService> services;

    @Scheduled(cron = "0/20 * * * * ?")
    public void sayHello() {
        System.out.println("MineTask start ----------------------------------------------------------------------");
        System.out.println("Hello World");
        services.stream().forEach(item ->{
            System.out.println(item.mine());
        });
        System.out.println("MineTask end   ----------------------------------------------------------------------");
    }
}
