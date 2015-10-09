package by.org.cgm.magneticobserver.utils;

import com.google.gson.Gson;

import by.org.cgm.magneticobserver.models.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static MagMessage parse(String json) {
        return new Gson().fromJson(json, MagMessage.class);
    }

    public static String toDoubleDigits(int val) {
        return (val<10) ? "0" + val : String.valueOf(val);
    }

    public static String formatDecimals(String template, float value) {
        return String.format(template, (double) Math.round(value * 10) / 10);
    }

}
