//package com.deepblue.springbatch.chapter_07;
//
//import org.springframework.batch.core.ChunkListener;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MineChunkListener implements ChunkListener {
//
//    @Override
//    public void beforeChunk(ChunkContext context) {
//        System.out.println("MineChunkListener ------------------------:" + context.getStepContext().getStepName() + ", beforeChunk invoked!");
//    }
//
//    @Override
//    public void afterChunk(ChunkContext context) {
//        System.out.println("MineChunkListener ------------------------:" + context.getStepContext().getStepName() + ", afterChunk invoked!");
//    }
//
//    @Override
//    public void afterChunkError(ChunkContext context) {
//        ChunkListener.super.afterChunkError(context);
//    }
//}
