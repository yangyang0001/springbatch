package com.deepblue.async.service.impl;

import com.deepblue.async.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Async("mineExecutor")
    @Override
    public CompletableFuture<String> task() {
        try {
            // 模拟耗时任务
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.supplyAsync(() -> UUID.randomUUID().toString());
    }



}
