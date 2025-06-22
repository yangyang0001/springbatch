package com.deepblue.exception.handler;

import com.deepblue.exception.common.MineException;
import com.deepblue.exception.common.ErrorCodeEnum;
import com.deepblue.exception.common.MineResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


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
                .setMessage(ex.getMessage())
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

    /**
     * 处理所有自定义或未捕获的异常
     */
    @ExceptionHandler(ValidationException.class)
    public MineResponse<Object> handleMineException(ValidationException ex) {
        // 打印日志
        ex.printStackTrace();

        // 返回响应（可封装为统一格式）
        MineResponse<Object> response = new MineResponse<>();
        response.setCode(ErrorCodeEnum.E_0003.getCode())
                .setMessage(ex.getMessage())
                .setResult(null);
        return response;
    }

    // 处理 @RequestBody 校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MineResponse<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        // 返回响应（可封装为统一格式）
        MineResponse<Object> response = new MineResponse<>();
        response.setCode(ErrorCodeEnum.E_0004.getCode())
                .setMessage(message)
                .setResult(null);
        return response;
    }

    // 处理 query param / path param 校验失败
    @ExceptionHandler(ConstraintViolationException.class)
    public MineResponse<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        MineResponse<Object> response = new MineResponse<>();
        response.setCode(ErrorCodeEnum.E_0005.getCode())
                .setMessage(ex.getMessage())
                .setResult(null);
        return response;
    }



}
