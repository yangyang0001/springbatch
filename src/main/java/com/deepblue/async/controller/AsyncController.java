package com.deepblue.async.controller;

import com.deepblue.async.service.AsyncService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * 测试异步调用
 */
@RestController
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @PostMapping("/asyncRequest")
    public String asyncRequest() throws InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = asyncService.task();
        CompletableFuture<String> task2 = asyncService.task();
        CompletableFuture<String> task3 = asyncService.task();

        CompletableFuture<Void> all = CompletableFuture.allOf(task1, task2, task3);
        all.join();

        System.out.println("cost time is :" + (System.currentTimeMillis() - start));
        return "";
    }
}
