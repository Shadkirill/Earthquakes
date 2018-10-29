package com.kirill.earthquake.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
    private static final DateFormat dateFormatUtc0 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
    private static final DateFormat dateFormatToShow = new SimpleDateFormat("HH:mm");
    static {
        dateFormatUtc0.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormatToShow.setTimeZone(TimeZone.getDefault());
    }

    public static final String getCurrentTimeIso() {
        return dateFormatUtc0.format(new Date());
    }

    public static final String getTimeIso(Date date) {
        return dateFormatToShow.format(date);
    }

    public static final String getTodayMiddleNightTimeIso() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateFormatUtc0.format(calendar.getTime());
    }
}
