package com.deepblue.springbatch.chapter_10.job_excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class BatchQueueListener<T> extends AnalysisEventListener<T> {

    private final BlockingQueue<T> queue;

    private final int batchSize;

    private List<T> buffer;

    public BatchQueueListener(BlockingQueue<T> queue, int batchSize) {
        this.queue = queue;
        this.batchSize = batchSize;
        this.buffer = new ArrayList<>(batchSize);
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        buffer.add(data);
        if (buffer.size() >= batchSize) {
            flush();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        flush();
    }

    private void flush() {
        for (T item : buffer) {
            try {
                queue.put(item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Failed to put data into queue", e);
            }
        }
        buffer.clear();
    }
}

