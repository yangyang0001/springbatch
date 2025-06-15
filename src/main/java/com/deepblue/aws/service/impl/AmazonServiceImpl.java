package com.deepblue.aws.service.impl;

import com.deepblue.aws.config.AmazonConfig;
import com.deepblue.aws.service.AmazonService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.HashMap;
import java.util.Map;

public class AmazonServiceImpl implements AmazonService {

    @Value("${aws.senderId}")
    private String senderId;

    @Value("${aws.smsType}")
    private String smsType;

    @Resource
    private AmazonConfig config;

    @Override
    public Map<Object, Object> sendMessage(String phone, String message) {
        SnsClient snsClient = config.snsClient();

        Map<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put("AWS.SNS.SMS.SenderID", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(senderId)
                .build());

        attributes.put("AWS.SNS.SMS.SMSType", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue(smsType)
                .build());


        // 以下是账号级别设置, 不需要在短信级别设置!
        attributes.put("AWS.SNS.SMS.StatusSuccessSamplingRate", MessageAttributeValue.builder()
                .dataType("String")
                .stringValue("100")
                .build());

        String deliveryStatusTopic = "arn:aws:sns:us-east-1:123456789012:sms-status-topic";
        attributes.put("AWS.SNS.SMS.Status", MessageAttributeValue.builder()
                .stringValue(deliveryStatusTopic)
                .dataType("String")
                .build());

        attributes.put("AWS.SNS.SMS.SMSUsageReportTopicArn", MessageAttributeValue.builder()
                .stringValue("arn:aws:sns:us-east-1:123456789012:YourSMSTopic")
                .dataType("String")
                .build());



        PublishRequest request = PublishRequest.builder()
                .phoneNumber(phone)
                .message(message)
                .messageAttributes(attributes)
                .build();

        PublishResponse response = snsClient.publish(request);
        String messageId = response.messageId();

        Map<Object, Object> result = new HashMap<>();
        result.put("messageId", messageId);
        result.put("phone", phone);
        result.put("isSuccessful", response.sdkHttpResponse().isSuccessful());

        return result;
    }
}
