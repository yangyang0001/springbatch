package com.deepblue.springbatch.common;

public enum ErrorCodeEnum {

    E_0000("0000", "Successful"),
    E_0001("0001", "runtime exception message"),
    E_0002("0002", "mine exception message");

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
