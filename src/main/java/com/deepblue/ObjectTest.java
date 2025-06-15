package com.deepblue;

import com.alibaba.fastjson.JSON;
import com.deepblue.aws.entity.SnsDeliveryMessage;
import com.deepblue.entity.Mine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ObjectTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

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

        JsonNode rootNode = objectMapper.readTree(message);
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


    }

}
