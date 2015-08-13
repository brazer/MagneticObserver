package by.org.cgm.magneticobserver;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class AppCache {

    private static AppCache sInstance;

    private AppCache() {}

    public static void initialize() {
        sInstance = new AppCache();
    }

}
