package by.org.cgm.magneticobserver.model;

import lombok.Getter;

/**
 * Author: Anatol Salanevich
 * Date: 06.10.2015
 */
public class Mark {

    private static int count = 0;
    @Getter private int id;
    public String begin;
    public String end;
    public int level;

    public Mark() {
        id = ++count;
    }

    public static void reset() {
        count = 0;
    }

}
