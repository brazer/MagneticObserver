package by.org.cgm.magneticobserver;

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

    private AppCache() {}

    public static void initialize() {
        sInstance = new AppCache();
    }

    public static AppCache getInstance() {
        if (sInstance == null) initialize();
        return sInstance;
    }

}
