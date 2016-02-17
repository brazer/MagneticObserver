package by.org.cgm.magneticobserver.util;

import android.text.format.DateFormat;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import by.org.cgm.magneticobserver.model.MagMessage;

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

    public static void convertToLocalTime(MagMessage mes) {
        mes.setBegin(convert(mes.getBegin()));
        mes.setEnd(convert(mes.getEnd()));
    }

    private static String convert(String time) {
        DateTimeFormatter inputFormatter = DateTimeFormat.forPattern("HH:mm").withZoneUTC();
        DateTime parsed = inputFormatter.parseDateTime(time);
        DateTimeFormatter outputFormatter = DateTimeFormat.forPattern("HH:mm")
                .withZone(DateTimeZone.forID("Europe/Minsk"));
        return outputFormatter.print(parsed);
    }

}
