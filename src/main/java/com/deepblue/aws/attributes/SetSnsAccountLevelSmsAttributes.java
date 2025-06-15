package com.deepblue.aws.attributes;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;
        import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

import java.util.HashMap;
import java.util.Map;

public class SetSnsAccountLevelSmsAttributes {

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
    }
}

