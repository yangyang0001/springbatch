package com.deepblue.aws.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.aws.service.AmazonService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sns")
public class SnsSendSMSController {

    @Resource
    private AmazonService amazonService;

    @PostMapping("/send_message")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String sendMessage(String phone, String message) {
        Map<Object, Object> resultMap = amazonService.sendMessage(phone, message);
        return JSON.toJSONString(resultMap);
    }
}
