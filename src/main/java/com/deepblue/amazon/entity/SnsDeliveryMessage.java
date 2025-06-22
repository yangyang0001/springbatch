package com.deepblue.amazon.entity;

import lombok.Data;

@Data
public class SnsDeliveryMessage {

    public Notification notification;
    public Delivery delivery;

    @Data
    public static class Notification {
        public String messageId;
        public String timestamp;
    }

    @Data
    public static class Delivery {
        public String destination;
        public String smsType;
        public String providerResponse;
        public int dwellTimeMsUntilDeviceAck;
        public String deliveryStatus;
    }
}
