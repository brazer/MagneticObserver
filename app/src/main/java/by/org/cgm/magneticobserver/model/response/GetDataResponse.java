package by.org.cgm.magneticobserver.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

import by.org.cgm.magneticobserver.model.Data;

/**
 * Author: Anatol Salanevich
 * Date: 01.10.2015
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataResponse extends ArrayList<Data> {
}
