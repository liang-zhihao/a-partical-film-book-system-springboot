package com.liang.ticketbooksystem.utils;

public enum MyMsg {

    SUCCEED_TO_DELETE("succeed to delete"),
    SUCCEED_TO_CREATE("succeed to create"),
    SUCCEED_TO_UPDATE("succeed to update"),
    FAILED_TO_DELETE("failed to delete"),
    FAILED_TO_UPDATE("failed to update"),
    FAILED_TO_CREATE("failed to create"),
    SUCCEED_TO_GET("succeed to get"),
    FAILED_TO_GET("failed to get"),
    SUCCEED_TO_LOGIN("succeed to login"),
    ;

    private String value;

    MyMsg(String s) {
    this.value=s;
    }
    public String v() {
        return this.value;
    }
}
