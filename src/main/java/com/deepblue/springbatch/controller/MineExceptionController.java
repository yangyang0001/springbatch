package com.deepblue.springbatch.controller;

import com.deepblue.springbatch.common.ErrorCodeEnum;
import com.deepblue.springbatch.common.MineResponse;
import com.deepblue.springbatch.exception.MineException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MineExceptionController {

    @PostMapping("/testRuntimeException")
    public MineResponse<String> testRuntimeException() {
        throw new RuntimeException(ErrorCodeEnum.E_0001.getMessage());
    }

    @PostMapping("/testMineException")
    public MineResponse<String> testMineException() {
        throw new MineException(ErrorCodeEnum.E_0002.getMessage());
    }

}
