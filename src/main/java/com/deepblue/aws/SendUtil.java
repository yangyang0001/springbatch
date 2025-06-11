package com.deepblue.aws;

import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

public class SendUtil {

    @Autowired
    private SnsClient snsClient;

    public static void main(String[] args) {
        PublishRequest request = PublishRequest.builder()
                .message("Your message")
                .phoneNumber("+819012345678")
                .build();

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .build();
        pubTextSMS(snsClient, message, phoneNumber);
        snsClient.close();
        snsClient.publish(request);
    }


    SnsClient snsClient = SnsClient.builder()
            .region(Region.US_EAST_1)
            .build();
    pubTextSMS(snsClient, message, phoneNumber);
        snsClient.close();
}
