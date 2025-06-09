package com.deepblue.springbatch.exception;

public class MineException extends Exception{
    private String message;
    public MineException(String message) {
        this.message = message;
    }
}
