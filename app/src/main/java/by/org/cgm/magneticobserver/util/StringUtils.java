package by.org.cgm.magneticobserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import by.org.cgm.magneticobserver.AppCache;
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

    public static String getStormLevel(int value) {
        String levels[] = AppCache.getInstance().getLevels();
        switch (value) {
            case 0: return levels[0];
            case 1: return levels[0];
            case 2: return levels[1];
            case 3: return levels[1];
            case 4: return levels[2];
            case 5: return levels[2];
            case 6: return levels[3];
            case 7: return levels[3];
            case 8: return levels[4];
            case 9: return levels[4];
        }
        return "";
    }

    public static String toMessage(String template, int value) {
        return String.format(template, StringUtils.getStormLevel(value));
    }

}
