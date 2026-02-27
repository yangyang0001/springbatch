package com.deepblue.handler.common;

public enum SiteIdEnum {

    AP("2", "AP"),
    BP("1", "BP");

    String code;
    String name;

    SiteIdEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
