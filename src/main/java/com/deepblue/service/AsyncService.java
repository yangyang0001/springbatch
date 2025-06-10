package com.deepblue.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> task();
}
