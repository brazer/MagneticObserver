package by.org.cgm.magneticobserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import by.org.cgm.magneticobserver.model.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public final class StringUtils {

    public static final String EMPTY = "";

    public static MagMessage parse(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json.replace('\'', '"'), MagMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toDoubleDigits(int val) {
        return (val<10) ? "0" + val : String.valueOf(val);
    }

    public static String formatDecimals(String template, float value) {
        return String.format(template, (double) Math.round(value * 10) / 10);
    }

}
