//package com.deepblue.springbatch.chapter_09;
//
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MineWriter implements ItemWriter<String> {
//    @Override
//    public void write(Chunk<? extends String> chunk) throws Exception {
//        chunk.forEach(item -> System.out.println("Hello " + item));
//    }
//}
