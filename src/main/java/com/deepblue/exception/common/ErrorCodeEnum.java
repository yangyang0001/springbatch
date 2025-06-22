package com.deepblue.exception.common;

public enum ErrorCodeEnum {

    E_0000("0000", "Successful"),
    E_0001("0001", "RuntimeException message"),
    E_0002("0002", "MineException message"),
    E_0003("0003", "ValidationException message"),
    E_0004("0004", "MethodArgumentNotValidException message"),
    E_0005("0005", "ConstraintViolationException message");

    private String code;

    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
