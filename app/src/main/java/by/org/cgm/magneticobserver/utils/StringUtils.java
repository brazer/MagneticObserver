package by.org.cgm.magneticobserver.utils;

import com.google.gson.Gson;

import by.org.cgm.magneticobserver.models.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class StringUtils {

    public static MagMessage parse(String json) {
        return new Gson().fromJson(json, MagMessage.class);
    }

}
