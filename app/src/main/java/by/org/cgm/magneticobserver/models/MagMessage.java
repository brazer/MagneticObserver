package by.org.cgm.magneticobserver.models;

import java.io.Serializable;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
public class MagMessage implements Serializable {

    public static final String TAG = "MagMessage";

    private String begin;
    private String end;
    private String date;
    private int value;

    public String toMessage(String template) {
        return String.format(template, begin, end, date, value);
    }

    public String toShortMessage(String template) {
        return String.format(template, value);
    }
}
