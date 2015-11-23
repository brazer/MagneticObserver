package by.org.cgm.magneticobserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("date")
    private String date;
    @JsonProperty("hour")
    private int hour;
    @JsonProperty("minute")
    private int minute;
    @JsonProperty("x")
    private double x;
    @JsonProperty("y")
    private double y;
    @JsonProperty("z")
    private double z;

    public String getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
