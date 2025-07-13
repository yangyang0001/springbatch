package com.deepblue.springbatch.chapter_10.job_excel;

import com.alibaba.excel.EasyExcel;
import com.deepblue.springbatch.chapter_10.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@StepScope
public class ExcelItemReader implements ItemReader<User>, InitializingBean {

    @Value("#{jobParameters['filePath']}")
    private String filePath;

    private final BlockingQueue<User> queue = new LinkedBlockingQueue<>();

    private volatile boolean finished = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 启动新线程异步加载数据
        new Thread(() -> {
            try {
                EasyExcel.read(filePath, User.class, new BatchQueueListener<User>(queue, 10))
                        .sheet()
                        .doRead();
            } finally {
                finished = true;
            }
        }, "EasyExcel-Reader-Thread").start();
    }

    @Override
    public User read() throws Exception {
        if (finished && queue.isEmpty()) {
            return null;
        }
        // 如果队列为空，阻塞一会儿等待生产者
        return queue.poll(3, TimeUnit.SECONDS);
    }
}
