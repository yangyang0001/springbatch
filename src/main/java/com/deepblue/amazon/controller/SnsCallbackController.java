//package com.deepblue.amazon.controller;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Instant;
//
///**
// * {
// * "Type" : "Notification",
// * "MessageId" : "abcd1234-5678-90ab-cdef-EXAMPLE",
// * "TopicArn" : "arn:aws:sns:ap-northeast-1:123456789012:MyDeliveryStatusTopic",
// * "Message" : "{\"notification\":{\"messageId\":\"df234a12-3456-789a-bcde-0987example\",\"timestamp\":\"2025-06-15T02:15:03.432Z\"},\"delivery\":{\"destination\":\"+819012345678\",\"smsType\":\"Promotional\",\"providerResponse\":\"Message has been accepted by phone carrier.\",\"dwellTimeMsUntilDeviceAck\":534,\"deliveryStatus\":\"SUCCESS\"}}",
// * "Timestamp" : "2025-06-15T02:15:03.511Z",
// * "SignatureVersion" : "1",
// * "Signature" : "base64-encoded-signature",
// * "SigningCertURL" : "https://sns.ap-northeast-1.amazonaws.com/SimpleNotificationService.pem",
// * "UnsubscribeURL" : "https://sns.ap-northeast-1.amazonaws.com/?Action=Unsubscribe..."
// * }
// * <p>
// * 上面的 Message 字段解析后的 JSON:
// * {
// * "notification": {
// * "messageId": "df234a12-3456-789a-bcde-0987example",
// * "timestamp": "2025-06-15T02:15:03.432Z"
// * },
// * "delivery": {
// * "destination": "+819012345678",
// * "smsType": "Promotional",
// * "providerResponse": "Message has been accepted by phone carrier.",
// * "dwellTimeMsUntilDeviceAck": 534,
// * "deliveryStatus": "SUCCESS"
// * }
// * }
// * *************************************************************************************************************
// * {
// * "Type": "Notification",
// * "MessageId": "1234-5678",
// * "TopicArn": "arn:aws:sns:us-east-1:123456789012:sms-status",
// * "Message": "{\"notification\":{\"messageId\":\"msg-id\"},\"delivery\":{\"destination\":\"+12345678901\",\"providerResponse\":\"Message delivered\",\"dwellTimeMsUntilDeviceAck\":223,\"success\":true}}",
// * "Timestamp": "2025-06-15T12:34:56Z"
// * }
// * <p>
// * 上面的 Message 字段解析出来的 JSON
// * {
// * "notification": {
// * "messageId": "msg-id"
// * },
// * "delivery": {
// * "destination": "+12345678901",
// * "providerResponse": "Message delivered",
// * "dwellTimeMsUntilDeviceAck": 223,
// * "success": true
// * }
// * }
// * <p>
// * *************************************************************************************************************
// * {
// * "Type": "Notification",
// * "MessageId": "d3f6f2e3-1d39-5d10-8f7a-1234567890ab",
// * "TopicArn": "arn:aws:sns:us-east-1:123456789012:sms-status-topic",
// * "Subject": null,
// * "Message": "{\"notification\":{\"messageId\":\"df234a12-3456-789a-bcde-0987example\",\"timestamp\":\"2025-06-15T02:15:03.432Z\"},\"delivery\":{\"destination\":\"+1234567890\",\"smsType\":\"Transactional\",\"providerResponse\":\"Message has been accepted by phone carrier.\",\"dwellTimeMsUntilDeviceAck\":534,\"deliveryStatus\":\"SUCCESS\"}}",
// * "Timestamp": "2025-06-15T02:15:03.511Z",
// * "SignatureVersion": "1",
// * "Signature": "base64-encoded-signature",
// * "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService.pem",
// * "UnsubscribeURL": "https://sns.us-east-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-east-1:123456789012:sms-status-topic:xxxxxx",
// * "MessageAttributes": {}
// * }
// * <p>
// * 上面的 Message 字段解析出来的 JSON
// * {
// * "notification": {
// * "messageId": "df234a12-3456-789a-bcde-0987example",
// * "timestamp": "2025-06-15T02:15:03.432Z"
// * },
// * "delivery": {
// * "destination": "+1234567890",
// * "smsType": "Transactional",
// * "providerResponse": "Message has been accepted by phone carrier.",
// * "dwellTimeMsUntilDeviceAck": 534,
// * "deliveryStatus": "SUCCESS"
// * }
// * }
// * *************************************************************************************************************
// * <p>
// * Message ChatGPT 给出的 JSON 串结构!
// * {
// * "notification": {
// * "messageId": "abcd-1234...",
// * "timestamp": "2025-06-15T12:34:56Z"
// * },
// * "delivery": {
// * "destination": "+819012345678",
// * "smsType": "Promotional",
// * "providerResponse": "Message has been accepted by phone carrier.",
// * "success": true,
// * "deliveryStatus": "SUCCESS",
// * "dwellTimeMsUntilDeviceAck": 742
// * }
// * }
// */
//@RestController
//@RequestMapping("/sns")
//public class SnsCallbackController {
//
//    private static final Logger log = LoggerFactory.getLogger(SnsCallbackController.class);
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @PostMapping("/callback")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void receiveSnsNotification(@RequestBody String body, HttpServletRequest request) throws Exception {
//        String messageType = request.getHeader("x-amz-sns-message-type");
//        JsonNode root = objectMapper.readTree(body);
//
//        if ("SubscriptionConfirmation".equalsIgnoreCase(messageType)) {
//            String subscribeUrl = root.get("SubscribeURL").asText();
//            log.info("🔔 SNS SubscriptionConfirmation received. Please visit: {}", subscribeUrl);
//            // 自动注册 回调 topic
//
//            // SNS 发送短信自动注册回调 springboot 完整案例推荐!
//            HttpClient.newHttpClient().send(
//                    HttpRequest.newBuilder().uri(URI.create(subscribeUrl)).GET().build(),
//                    HttpResponse.BodyHandlers.discarding()
//            );
//
//            // SNS 发送短信自动注册回调 springboot 完整案例也可以!
//            HttpClient.newHttpClient().send(
//                    HttpRequest.newBuilder(URI.create(subscribeUrl)).GET().build(),
//                    HttpResponse.BodyHandlers.ofString()
//            );
//            System.out.println("SNS subscription confirmed.");
//
//
//        } else if ("Notification".equalsIgnoreCase(messageType)) {
//            String messageJson = root.get("Message").asText();
//            JsonNode delivery = objectMapper.readTree(messageJson);
//
//            String phone = delivery.get("delivery").get("destination").asText();
//            boolean success = delivery.get("delivery").get("success").asBoolean();
//            String providerResponse = delivery.get("delivery").get("providerResponse").asText();
//            String deliveryStatus = delivery.get("delivery").get("deliveryStatus").asText();
//
//            log.info("📬 SMS delivery report - Phone: {}, Success: {}, Provider: {}",
//                    phone, success, providerResponse);
//        } else {
//            log.warn("❓ Unknown SNS message type: {}", messageType);
//        }
//    }
//
//
//    @PostMapping("/callback")
//    public ResponseEntity<String> callback(@RequestBody String snsPayload) {
//        try {
//            JsonNode root = objectMapper.readTree(snsPayload);
//            String type = root.path("Type").asText();
//
//            if ("SubscriptionConfirmation".equals(type)) {
//                String subscribeUrl = root.path("SubscribeURL").asText();
//                // 自动确认订阅
//                confirmSubscription(subscribeUrl);
//                System.out.println("SNS Subscription confirmed.");
//                return ResponseEntity.ok("Subscription confirmed");
//
//            } else if ("Notification".equals(type)) {
//                String messageStr = root.path("Message").asText();
//                JsonNode messageJson = objectMapper.readTree(messageStr);
//
//                String messageId = messageJson.path("notification").path("messageId").asText();
//                String phone = messageJson.path("delivery").path("destination").asText();
//                String status = messageJson.path("delivery").path("deliveryStatus").asText();
//                String providerResponse = messageJson.path("delivery").path("providerResponse").asText();
//                Instant timestamp = Instant.parse(root.path("Timestamp").asText());
//
//                System.out.printf("短信回调 messageId=%s, phone=%s, status=%s, providerResponse=%s, timestamp=%s%n",
//                        messageId, phone, status, providerResponse, timestamp);
//
//                // TODO: 存库或其他处理逻辑
//
//                return ResponseEntity.ok("Notification processed");
//            }
//
//            return ResponseEntity.badRequest().body("Unsupported SNS message type");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Error processing SNS message");
//        }
//    }
//
//    private void confirmSubscription(String subscribeUrl) throws IOException, InterruptedException {
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(subscribeUrl))
//                    .GET()
//                    .build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            if (response.statusCode() != 200) {
//                throw new RuntimeException("Subscription confirmation failed: " + response.statusCode());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
