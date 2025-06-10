package com.deepblue.controller;

import com.deepblue.common.ErrorCodeEnum;
import com.deepblue.common.MineResponse;
import com.deepblue.exception.MineException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试全局异常处理器
 */
@RestController
public class MineExceptionController {

    @PostMapping("/testRuntimeException")
    public MineResponse<String> testRuntimeException() {
        throw new RuntimeException(ErrorCodeEnum.E_0001.getMessage());
    }

    @PostMapping("/testMineException")
    public MineResponse<String> testMineException() {
        throw new MineException(ErrorCodeEnum.E_0002.getMessage(), ErrorCodeEnum.E_0002.getCode());
    }

}
