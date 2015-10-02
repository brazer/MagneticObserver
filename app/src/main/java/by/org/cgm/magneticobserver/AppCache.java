package by.org.cgm.magneticobserver;

import java.util.ArrayList;

import by.org.cgm.magneticobserver.models.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class AppCache {

    private static AppCache sInstance;

    @Setter @Getter
    private String[] levels;
    @Setter @Getter
    private ArrayList<Data> data;

    private AppCache() {}

    public static void initialize() {
        sInstance = new AppCache();
    }

    public static AppCache getInstance() {
        return sInstance;
    }

}
