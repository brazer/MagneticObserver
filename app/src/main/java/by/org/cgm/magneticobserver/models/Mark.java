package by.org.cgm.magneticobserver.models;

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
    public int mark;

    public Mark() {
        id = ++count;
    }

}
