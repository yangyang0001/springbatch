package com.deepblue.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.deepblue.kafka.config.TopicConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaBatchListener {

    @KafkaListener(groupId = "group_topic_batch_record", topics = TopicConfig.TOPIC_BATCH_RECORD, containerFactory = "batchFactory", concurrency = "10")
    public void listen(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord<String, String> record : records) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("KafkaBatchListener listen method invoked");
                buffer.append(", topic = ").append(record.topic());
                buffer.append(", partition = ").append(record.partition());
                buffer.append(", offset = ").append(record.offset());
                buffer.append(", key = ").append(record.key());
                buffer.append(", value = ").append(record.value());
                buffer.append(", timestamp = ").append(record.timestamp());
                buffer.append(", headers = ").append(JSON.toJSONString(record.headers()));

                System.out.println(buffer.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ack.acknowledge();
        }

    }
}
