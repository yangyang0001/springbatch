package com.deepblue.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    public static final String TOPIC_SINGLE_RECORD  = "topic_single_record";

    public static final String TOPIC_BATCH_RECORD = "topic_batch_record";

    @Bean
    public NewTopic topicSingleRecord() {
        return new NewTopic(TOPIC_SINGLE_RECORD, 1, (short) 1);
    }

    @Bean
    public NewTopic topicBatchRecord() {
        return new NewTopic(TOPIC_BATCH_RECORD, 10, (short) 1);
    }

}
