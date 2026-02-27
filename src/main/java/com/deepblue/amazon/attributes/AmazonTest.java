package com.deepblue.amazon.attributes;

import com.alibaba.fastjson.JSON;
import com.deepblue.amazon.entity.SnsDeliveryMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;
        import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

import java.util.HashMap;
import java.util.Map;

public class AmazonTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {

        // ✅ 初始化 SNS 客户端
        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1) // 根据你的 SNS 区域选择
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        // ✅ 设置属性键值对
        Map<String, String> attributes = new HashMap<>();

        // 设置默认短信类型
        attributes.put("DefaultSMSType", "Transactional");

        // 设置默认 Sender ID（仅部分国家支持）
        attributes.put("DefaultSenderID", "MyApp");

        // 设置成功投递的采样比率（百分比）
        attributes.put("DeliveryStatusSuccessSamplingRate", "100");

        // 设置投递失败的采样比率（百分比）
        attributes.put("DeliveryStatusFailureSamplingRate", "100");

        // 设置 IAM Role 以便 SNS 将短信状态写入 CloudWatch 或发 SNS 通知
        attributes.put("DeliveryStatusIAMRole", "arn:aws:iam::123456789012:role/SnsSmsDeliveryRole");

        // 设置短信投递状态 SNS Topic
        attributes.put("DeliveryStatusTopicArn", "arn:aws:sns:us-east-1:123456789012:sms-status-topic");

        // 设置短信使用报告 SNS Topic
        attributes.put("SMSUsageReportTopicArn", "arn:aws:sns:us-east-1:123456789012:sms-usage-report-topic");

        // ✅ 发送设置请求
        SetSmsAttributesRequest request = SetSmsAttributesRequest.builder()
                .attributes(attributes)
                .build();

        snsClient.setSMSAttributes(request);

        System.out.println("SNS 账户级别 SMS 参数设置成功！");

        snsClient.close();


        System.out.println("---------------------------------------------------------------------");


        System.out.println("-------------------------------------------------------------------------");

        String message = "{\n" +
                "\"Type\": \"Notification\",\n" +
                "\"MessageId\": \"d3f6f2e3-1d39-5d10-8f7a-1234567890ab\",\n" +
                "\"TopicArn\": \"arn:aws:sns:us-east-1:123456789012:sms-status-topic\",\n" +
                "\"Subject\": null,\n" +
                "\"Message\": \"{\\\"notification\\\":{\\\"messageId\\\":\\\"df234a12-3456-789a-bcde-0987example\\\",\\\"timestamp\\\":\\\"2025-06-15T02:15:03.432Z\\\"},\\\"delivery\\\":{\\\"destination\\\":\\\"+1234567890\\\",\\\"smsType\\\":\\\"Transactional\\\",\\\"providerResponse\\\":\\\"Message has been accepted by phone carrier.\\\",\\\"dwellTimeMsUntilDeviceAck\\\":534,\\\"deliveryStatus\\\":\\\"SUCCESS\\\"}}\",\n" +
                "\"Timestamp\": \"2025-06-15T02:15:03.511Z\",\n" +
                "\"SignatureVersion\": \"1\",\n" +
                "\"Signature\": \"base64-encoded-signature\",\n" +
                "\"SigningCertURL\": \"https://sns.us-east-1.amazonaws.com/SimpleNotificationService.pem\",\n" +
                "\"UnsubscribeURL\": \"https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-east-1:123456789012:sms-status-topic:xxxxxx\",\n" +
                "\"MessageAttributes\": {}\n" +
                "}";

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(message);
            String jsonMessage = rootNode.get("Message").asText();

            JsonNode messageNode = objectMapper.readTree(jsonMessage);

            String messageId = messageNode.get("notification").get("messageId").asText();
            String phone = messageNode.get("delivery").get("destination").asText();
            String providerResponse = messageNode.get("delivery").get("providerResponse").asText();

            boolean success = messageNode.path("delivery").path("success").asBoolean();
            success = success ? true : messageNode.path("delivery").path("deliveryStatus").asText().equalsIgnoreCase("SUCCESS");

            System.out.println("messageId =" + messageId);
            System.out.println("phone =" + phone);
            System.out.println("providerResponse =" + providerResponse);

            System.out.println("success =" + success);

            SnsDeliveryMessage snsDeliveryMessage = objectMapper.readValue(jsonMessage, SnsDeliveryMessage.class);
            System.out.println("message = " + JSON.toJSONString(snsDeliveryMessage));



        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }



    }
}

