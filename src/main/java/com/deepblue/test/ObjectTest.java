package com.deepblue.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.deepblue.dynamicds.entity.Student;
import com.deepblue.kafka.entity.MineRecord;
import com.deepblue.redis_lua.RedisKeyConstants;
import com.deepblue.springbatch.chapter_other.entity.MineJobParam;
import com.deepblue.test.entity.Mine;
import com.deepblue.test.entity.SmsSendRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Slf4j
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

//        MineRecord record1 = new MineRecord();
//        MineRecord record2 = new MineRecord();
//        MineRecord record3 = new MineRecord();
//        MineRecord record4 = new MineRecord();
//        MineRecord record5 = new MineRecord();
//        MineRecord record6 = new MineRecord();
//
//        record1.setId(1L).setName("zhangsan1").setPass("1111111111").setAge(11);
//        record2.setId(2L).setName("zhangsan2").setPass("2222222222").setAge(21);
//        record3.setId(3L).setName("zhangsan3").setPass("3333333333").setAge(31);
//        record4.setId(4L).setName("zhangsan4").setPass("4444444444").setAge(41);
//        record5.setId(5L).setName("zhangsan5").setPass("5555555555").setAge(51);
//        record6.setId(6L).setName("zhangsan6").setPass("6666666666").setAge(61);
//
//        System.out.println("record1 is :" + JSON.toJSONString(record1));
//
//        List<MineRecord> records = new ArrayList<>();
//        records.add(record1);
//        records.add(record2);
//        records.add(record3);
//        records.add(record4);
//        records.add(record5);
//        records.add(record6);
//        System.out.println("records is :" + JSON.toJSONString(records));
//
//        System.out.println("-------------------------------------------------------------------------");
//
//        Iterator<String> iterator = Collections.emptyIterator();
//
//        System.out.println(iterator.hasNext());
//
//        System.out.println("-------------------------------------------------------------------------");

//        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");
//
//        Iterator<String> iterator1 = list1.iterator();
//        if (iterator1.hasNext()) {
//            System.out.println("next:" + iterator1.next());
//        }
//
//        iterator1 = list1.iterator();
//
//        if (iterator1.hasNext()) {
//            System.out.println("next:" + iterator1.next());
//        }

//        SmsSendRecord record1 = new SmsSendRecord();
//        record1.setId("1111");
//        record1.setState(1);
//        record1.setLoginName("1111");
//
//        SmsSendRecord record2 = new SmsSendRecord();
//        record2.setId("2222");
//        record2.setState(2);
//        record2.setLoginName("1111");
//
//
//        SmsSendRecord record3 = new SmsSendRecord();
//        record3.setId("3333");
//        record3.setState(3);
//        record3.setLoginName("2222");
//
//        SmsSendRecord record4 = new SmsSendRecord();
//        record4.setId("4444");
//        record4.setState(3);
//        record4.setLoginName("2222");
//
//        SmsSendRecord record5 = new SmsSendRecord();
//        record5.setId("5555");
//        record5.setState(7);
//        record5.setLoginName("2222");
//
//        SmsSendRecord record6 = new SmsSendRecord();
//        record6.setId("6666");
//        record6.setState(2);
//        record6.setLoginName("3333");
//
//
//        List<SmsSendRecord> records = new ArrayList<>();
//        records.add(record6);
//        records.add(record3);
//        records.add(record5);
//        records.add(record1);
//        records.add(record2);
//        records.add(record4);
//
//        String stringNowDate = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
//        Map<String, List<SmsSendRecord>> listMap = records.stream().collect(
//                Collectors.groupingBy(
//                        item -> RedisKeyConstants.SMS_BP_SEND_DOUBLE + stringNowDate + ":" + item.getLoginName(),
//                        LinkedHashMap::new,
//                        Collectors.toList()
//                ));
//        List<String> keyList = new ArrayList<>(listMap.keySet());
//        Map<String, String> keyValMap = keyList.stream().collect(
//                Collectors.toMap(item -> item, item -> item, (exist, replace) -> exist, LinkedHashMap::new)
//        );
//
//        System.out.println("listMap   :" + JSON.toJSONString(listMap));
//        System.out.println("keyList   :" + JSON.toJSONString(keyList));
//        System.out.println("keyValMap :" + JSON.toJSONString(keyValMap));
//
//
//        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
//        threadLocal.set(new Object());

        List<String> list = Arrays.asList("1", "101", "102", "103", "104", "105", "106");
        System.out.println(list.contains(1));

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


//    private List<SmsSendRecord> updateRemoveDoubleBP(List<SmsSendRecord> t, List<SmsSendRecord> sendDouble) {
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        List<SmsSendRecord> sendRecords = new ArrayList<>(t);
//        log.info("updateRemoveDoubleBP start uuid: {}, sendRecords.size: {}", uuid, sendRecords.size());
//        if (!sendRecords.isEmpty()) {
//            // 获取当前日期, 组装数据
//            String stringNowDate = DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
//            Map<String, List<SmsSendRecord>> listMap = sendRecords.stream().collect(Collectors.groupingBy(item -> RedisKeyConstants.SMS_BP_SEND_DOUBLE + stringNowDate + ":" + item.getLoginName()));
//            List<String> keyList = new ArrayList<>(listMap.keySet());
//            Map<String, String> keyValMap = keyList.stream().collect(Collectors.toMap(item -> item, item -> item, (exist, replace) -> exist, LinkedHashMap::new));
//
//            long seconds = Duration.between(LocalDateTime.now(), LocalDate.now().plusDays(1).atStartOfDay()).getSeconds();
//            List<Object> resultList = redisUtil.batchSetOrExpirePippline(keyValMap, seconds);
//            log.info("updateRemoveDoubleBP pipelineSet, uuid: {}, stringNowDate: {}, keyList: {}, keyValMap: {}, seconds: {}, resultList: {}", uuid, stringNowDate, JSON.toJSONString(keyList), JSON.toJSONString(keyValMap), seconds, JSON.toJSONString(resultList));
//
//            for (int i = 0; i < resultList.size(); i++) {
//                String key = keyList.get(i);
//                if (Boolean.FALSE.equals(resultList.get(i))) {
//                    // 设置失败 整批都已经重复!
//                    log.info("updateRemoveDoubleBP result is false, uuid: {}, i: {}, key: {}, records: {}", uuid, i, key, JSON.toJSONString(listMap.get(key)));
//                    listMap.get(key).stream().forEach(sendRecord -> {
//                        sendRecord.setState(SendStateEnum.REPEAT.getCodes());
//                        sendRecord.setRemark(SystemUrlConstant.SMS_DUPLICATE_DOUBLE);
//                        sendDouble.add(sendRecord);
//                    });
//                } else {
//                    // 设置成功; 只有第一个元素 不重复; 其他元素 都设置为 重复!
//                    log.info("updateRemoveDoubleBP result is true, uuid: {}, i: {}, key: {}, first record: {}, other records: {}",
//                            uuid, i, key, JSON.toJSONString(listMap.get(key).get(0)), JSON.toJSONString(listMap.get(key).stream().skip(1).toList()));
//                    listMap.get(key).stream().skip(1).forEach(sendRecord -> {
//                        sendRecord.setState(SendStateEnum.REPEAT.getCodes());
//                        sendRecord.setRemark(SystemUrlConstant.SMS_DUPLICATE_DOUBLE);
//                        sendDouble.add(sendRecord);
//                    });
//                }
//            }
//
//            if (!sendDouble.isEmpty()) {
//                //  遍历过程中 状态异常的数据剔除 掉
//                long start = System.currentTimeMillis();
//                log.info("insertBatchAndSendSecondKafka start, uuid: {}, sendDouble.size: {}", uuid, sendDouble.size());
//                insertBatchAndSendSecondKafka(sendDouble, stringNowDate, uuid);
//                log.info("insertBatchAndSendSecondKafka end, uuid: {}, sendDouble.size: {}, cost time: {}", uuid, sendDouble.size(), System.currentTimeMillis() - start);
//                sendRecords.removeAll(sendDouble);
//            }
//        }
//
//        return sendRecords;
//    }
//
//    //批量添加 并且设置失效时间
//    public List<Object> batchSetOrExpirePippline(Map<String, String> map, Long seconds) {
//        //使用管道
//        return redisTemplate.executePipelined(new SessionCallback<Object>() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                map.forEach((key, value) -> {
//                    operations.opsForValue().setIfAbsent(key, value, seconds, TimeUnit.SECONDS);
//                });
//                return null;
//            }
//        });
//    }

}
