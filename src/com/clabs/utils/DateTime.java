package com.clabs.utils;

import java.util.Date;

public class DateTime {

    public static Date now() {
        Date r = new Date(System.currentTimeMillis());
        System.out.println("[Info] Date time now is " + r.toString());
        return r;
    }
}
