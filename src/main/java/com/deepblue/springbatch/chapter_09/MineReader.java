//package com.deepblue.springbatch.chapter_09;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//
//@Data
//@Component
//public class MineReader implements ItemReader<String> {
//
//    private List<String> readList;
//
//    private Iterator<String> iterator;
//
//    public MineReader(List<String> readList) {
//        this.readList = readList;
//        this.iterator = readList.iterator();
//    }
//
//
//    @Override
//    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        // 一个数据 一个数据的读
//        while (iterator.hasNext()) {
//            return iterator.next();
//        }
//
//        return null;
//    }
//
//}
