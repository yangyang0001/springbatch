package com.deepblue;

import com.alibaba.fastjson.JSON;
import com.deepblue.entity.Mine;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ObjectTest {

    public static void main(String[] args) {

        List<Mine> list = new ArrayList<>();
        list.add(new Mine("1", "zhangsan"));
        list.add(new Mine("2", "lisi"));

        Object[] array0 = list.toArray();
        Mine[]   array1 = list.toArray(new Mine[0]);
        Object[] array2 = list.stream().toArray();


        Arrays.stream(array0).forEach(item -> {
            System.out.println(JSON.toJSONString(item));
        });
        System.out.println("-------------------------------------------------------------------------");


        Arrays.stream(array1).forEach(item -> {
            System.out.println(JSON.toJSONString(item));
        });
        System.out.println("-------------------------------------------------------------------------");


        Arrays.stream(array2).forEach(item -> {
            System.out.println(JSON.toJSONString(item));
        });

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<List<Integer>> partition = Lists.partition(numbers, 10);

        numbers.forEach(item -> System.out.println(item));
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("partition size = " + partition.size());

        for (List<Integer> part : partition) {
            System.out.println("part = " + JSON.toJSONString(part));
        }
        System.out.println("-------------------------------------------------------------------------");

        System.out.println(ForkJoinPool.commonPool().getParallelism());

    }

}
