package com.deepblue.async.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    CompletableFuture<String> task();
}
