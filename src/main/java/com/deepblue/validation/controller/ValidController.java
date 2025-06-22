package com.deepblue.validation.controller;

import com.alibaba.fastjson.JSON;
import com.deepblue.validation.entity.MineValid;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试校验 Valid
 */
@Slf4j
@RestController
public class ValidController {

    @PostMapping("/valid")
    public String valid(@Valid @RequestBody MineValid valid) {
        log.info("valid : {}", JSON.toJSONString(valid));

        return JSON.toJSONString(valid);
    }
}
