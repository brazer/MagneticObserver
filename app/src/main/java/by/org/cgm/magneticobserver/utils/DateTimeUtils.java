package by.org.cgm.magneticobserver.utils;

import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Author: Anatol Salanevich
 * Date: 07.10.2015
 */
public class DateTimeUtils {

    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return (String) DateFormat.format("yyyy-MM-dd", calendar.getTime());
    }

}
