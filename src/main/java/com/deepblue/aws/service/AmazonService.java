package com.deepblue.aws.service;

import java.util.Map;

public interface AmazonService {

    Map<Object, Object> sendMessage(String phone, String message);
}
