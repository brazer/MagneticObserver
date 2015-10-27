package by.org.cgm.magneticobserver.utils;

import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Author: Anatol Salanevich
 * Date: 07.10.2015
 */
public class DateTimeUtils {

    public static String getYesterday() {
        return getPastDay(-1);
    }

    public static String getPastDay(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, count);
        return (String) DateFormat.format("yyyy-MM-dd", calendar.getTime());
    }

    public static String getDate(String datetime) {
        return datetime.substring(0, 10);
    }

    public static String getTime(String datetime) {
        return datetime.substring(11);
    }

}
