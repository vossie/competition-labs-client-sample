package com.clabs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {

    public static Date now() {
        Date r = new Date(System.currentTimeMillis());
        return r;
    }

    public static String dateMinusMillisAsString(int millis) {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        df.setTimeZone(tz);
        return df.format(new Date(System.currentTimeMillis() - millis));

    }
}
