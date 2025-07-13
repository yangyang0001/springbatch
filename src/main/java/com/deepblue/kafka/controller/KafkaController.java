package com.deepblue.kafka.controller;


import com.alibaba.fastjson.JSON;
import com.deepblue.kafka.config.TopicConfig;
import com.deepblue.kafka.entity.MineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/sendSingle")
    public String single(@RequestBody MineRecord mineRecord) {
        try {
            kafkaTemplate.send(TopicConfig.TOPIC_SINGLE_RECORD, JSON.toJSONString(mineRecord));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            kafkaTemplate.flush();
        }
        return "success_single";
    }

    @RequestMapping("/sendBatch")
    public String batch(@RequestBody List<MineRecord> mineRecords) {
        try {
            for (MineRecord mineRecord : mineRecords) {
                // 不设置 key, 轮询插入分区
//                kafkaTemplate.send(TopicConfig.TOPIC_BATCH_RECORD, JSON.toJSONString(mineRecord));
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String time = String.valueOf(System.currentTimeMillis());
                // 设置 key, 根据 key 某种 hash 算法, 插入不同的分区
                kafkaTemplate.send(TopicConfig.TOPIC_BATCH_RECORD,uuid+ "_" + time , JSON.toJSONString(mineRecord));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            kafkaTemplate.flush();
        }
        return "success_batch";
    }

    public static void main(String[] args) {

    }
}
