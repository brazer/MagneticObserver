package by.org.cgm.magneticobserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @Getter
    @JsonProperty("date")
    private String date;
    @Getter
    @JsonProperty("hour")
    private int hour;
    @Getter
    @JsonProperty("minute")
    private int minute;
    @Getter
    @JsonProperty("x")
    private double x;
    @Getter
    @JsonProperty("y")
    private double y;
    @Getter
    @JsonProperty("z")
    private double z;

}
