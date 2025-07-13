package com.deepblue.test;

import com.alibaba.fastjson.JSON;
import com.deepblue.dynamicds.entity.Student;
import com.deepblue.kafka.entity.MineRecord;
import com.deepblue.springbatch.chapter_other.entity.MineJobParam;
import com.deepblue.test.entity.Mine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class ObjectTest {

    public static void main(String[] args) throws JsonProcessingException {

//        List<Mine> list = new ArrayList<>();
//        list.add(new Mine("1", "zhangsan"));
//        list.add(new Mine("2", "lisi"));
//
//        Object[] array0 = list.toArray();
//        Mine[]   array1 = list.toArray(new Mine[0]);
//        Object[] array2 = list.stream().toArray();
//
//
//        Arrays.stream(array0).forEach(item -> {
//            System.out.println(JSON.toJSONString(item));
//        });
//        System.out.println("-------------------------------------------------------------------------");
//
//
//        Arrays.stream(array1).forEach(item -> {
//            System.out.println(JSON.toJSONString(item));
//        });
//        System.out.println("-------------------------------------------------------------------------");
//
//
//        Arrays.stream(array2).forEach(item -> {
//            System.out.println(JSON.toJSONString(item));
//        });
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        List<List<Integer>> partition = Lists.partition(numbers, 10);
//
//        numbers.forEach(item -> System.out.println(item));
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println("partition size = " + partition.size());
//
//        for (List<Integer> part : partition) {
//            System.out.println("part = " + JSON.toJSONString(part));
//        }
//        System.out.println("-------------------------------------------------------------------------");
//
//        System.out.println(ForkJoinPool.commonPool().getParallelism());
//
//        System.out.println("-------------------------------------------------------------------------");
//
//        A a = new A();
//        a.setCustomerId("zhangsan");
//
//        B b = new B();
//
//        BeanUtils.copyProperties(a, b);
//        System.out.println("b json is :" + JSON.toJSONString(b));
//
//        System.out.println("-------------------------------------------------------------------------");
//
//        MineJobParam<String> param = new MineJobParam<>();
//        List<String> dataList = Arrays.asList("zhangsan", "lisi", "wangwu");
//        param.setDataList(dataList);
//        System.out.println("param is :" + JSON.toJSONString(param));
//
//        System.out.println("-------------------------------------------------------------------------");
//        Student student = new Student();
//        student.setAge(10);
//        student.setName("zhangsan_new_database");
//        student.setGender(1);
//        student.setDelFlag(0);
//
//        System.out.println("student is :" + JSON.toJSONString(student));
//
//        System.out.println("-------------------------------------------------------------------------");

        MineRecord record1 = new MineRecord();
        MineRecord record2 = new MineRecord();
        MineRecord record3 = new MineRecord();
        MineRecord record4 = new MineRecord();
        MineRecord record5 = new MineRecord();
        MineRecord record6 = new MineRecord();

        record1.setId(1L).setName("zhangsan1").setPass("1111111111").setAge(11);
        record2.setId(2L).setName("zhangsan2").setPass("2222222222").setAge(21);
        record3.setId(3L).setName("zhangsan3").setPass("3333333333").setAge(31);
        record4.setId(4L).setName("zhangsan4").setPass("4444444444").setAge(41);
        record5.setId(5L).setName("zhangsan5").setPass("5555555555").setAge(51);
        record6.setId(6L).setName("zhangsan6").setPass("6666666666").setAge(61);

        System.out.println("record1 is :" + JSON.toJSONString(record1));

        List<MineRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        records.add(record6);
        System.out.println("records is :" + JSON.toJSONString(records));
//
//        System.out.println("-------------------------------------------------------------------------");
//
//        Iterator<String> iterator = Collections.emptyIterator();
//
//        System.out.println(iterator.hasNext());
//
//        System.out.println("-------------------------------------------------------------------------");

        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");

        Iterator<String> iterator1 = list1.iterator();
        if (iterator1.hasNext()) {
            System.out.println("next:" + iterator1.next());
        }

        iterator1 = list1.iterator();

        if (iterator1.hasNext()) {
            System.out.println("next:" + iterator1.next());
        }


    }




    @Data
    @Accessors(chain = true)
    public static class A {
        private String customerId;
    }

    @Data
    @Accessors(chain = true)
    public static class B {
        private Long customerId;
    }

}
