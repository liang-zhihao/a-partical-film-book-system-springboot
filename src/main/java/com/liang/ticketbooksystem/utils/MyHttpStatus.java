package com.liang.ticketbooksystem.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author 34773
 */

public enum  MyHttpStatus  {
 INFO_DUPLICATION ( 10001),
    SUCCEED_TO_DELETE(10002),
    ;


    private int value;

    MyHttpStatus(int i) {
        this.value=i;

    }

    public int value() {
        return this.value;
    }
}
