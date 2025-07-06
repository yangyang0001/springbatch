package com.deepblue.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.deepblue.kafka.config.TopicConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaSingleListener {

    @KafkaListener(groupId = "group_topic_single_record", topics = TopicConfig.TOPIC_SINGLE_RECORD, containerFactory = "singleFactory", concurrency = "1")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("KafkaSingleListener listen method invoked");
            buffer.append(", topic = ").append(record.topic());
            buffer.append(", partition = ").append(record.partition());
            buffer.append(", offset = ").append(record.offset());
            buffer.append(", key = ").append(record.key());
            buffer.append(", value = ").append(record.value());
            buffer.append(", timestamp = ").append(record.timestamp());
            buffer.append(", headers = ").append(JSON.toJSONString(record.headers()));

            System.out.println(buffer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ack.acknowledge();
        }

    }
}
