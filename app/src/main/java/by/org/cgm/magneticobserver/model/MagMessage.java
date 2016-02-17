package by.org.cgm.magneticobserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Anatol Salanevich
 * Date: 23.09.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MagMessage implements Serializable {

    public static final String TAG = "MagMessage";

    @Getter @Setter
    @JsonProperty("begin")
    private String begin;
    @Getter @Setter
    @JsonProperty("end")
    private String end;
    @Getter
    @JsonProperty("date")
    private String date;
    @Getter
    @JsonProperty("value")
    private int value;

}
