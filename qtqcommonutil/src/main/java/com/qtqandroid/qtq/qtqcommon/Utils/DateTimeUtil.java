package com.qtqandroid.qtq.qtqcommon.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class DateTimeUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date ParseDate(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static Date SafeParseDate(String dateStr) {
        try {
            if (StringUtil.IsNullOrEmpty(dateStr)) {
                return new Date();
            }
            return ParseDate(dateStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Calendar SafeParseCalendar(String dateStr) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(SafeParseDate(dateStr));
        return calendar;
    }

    public static String FormatDate(Calendar calendar) {
        return dateFormat.format(calendar.getTime());
    }

    public static String FormatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    public static Date RemoveTime(Date time) {
        return SafeParseDate(FormatDate(time));
    }

}
