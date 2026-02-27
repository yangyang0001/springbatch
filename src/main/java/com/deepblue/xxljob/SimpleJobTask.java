package com.deepblue.xxljob;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SimpleJobTask {

    @XxlJob("SimpleJobTask")
    public void doTask() {
        System.out.println("------------------------------------------ time is :" + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));
    }

}
