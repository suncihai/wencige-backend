package dev.local.todo.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.time.Instant;
import java.util.Date;

public abstract class LocalDateTimeUtil {

    public static long toLongTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) return 0l;
        else return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static int getDaysByYearMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Long getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Long getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }

}