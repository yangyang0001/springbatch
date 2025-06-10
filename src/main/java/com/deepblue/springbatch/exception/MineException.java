package com.deepblue.springbatch.exception;

import lombok.Data;

@Data
public class MineException  extends RuntimeException{

    private String code;

    public MineException() {
        super();
    }

    public MineException(String message) {
        super(message);
    }

    public MineException(String message, Throwable cause) {
        super(message, cause);
    }

    public MineException(Throwable cause) {
        super(cause);
    }

    protected MineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MineException(String message, String code) {
        super(message);
        this.code = code;
    }

}
