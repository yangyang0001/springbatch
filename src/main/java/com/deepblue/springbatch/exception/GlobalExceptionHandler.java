package com.deepblue.springbatch.exception;

import com.deepblue.springbatch.common.ErrorCodeEnum;
import com.deepblue.springbatch.common.MineResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有自定义或未捕获的异常
     */
    @ExceptionHandler(RuntimeException.class)
    public MineResponse<Object> handleRuntimeException(RuntimeException ex) {
        // 打印日志
        ex.printStackTrace();

        // 返回响应（可封装为统一格式）
        MineResponse<Object> response = new MineResponse<>();
        response.setCode(ErrorCodeEnum.E_0001.getCode())
                .setMessage(ErrorCodeEnum.E_0001.getMessage())
                .setResult(null);
        return response;
    }

    /**
     * 处理所有自定义或未捕获的异常
     */
    @ExceptionHandler(MineException.class)
    public MineResponse<Object> handleMineException(MineException ex) {
        // 打印日志
        ex.printStackTrace();

        // 返回响应（可封装为统一格式）
        MineResponse<Object> response = new MineResponse<>();
        response.setCode(ex.getCode())
                .setMessage(ex.getMessage())
                .setResult(null);
        return response;
    }

}
