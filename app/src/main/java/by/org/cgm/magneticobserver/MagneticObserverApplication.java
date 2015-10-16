package by.org.cgm.magneticobserver;

import android.app.Application;
import android.content.Context;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class MagneticObserverApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        AppCache.initialize();
        AppCache.getInstance().setLevels(getResources().getStringArray(R.array.storm_level));
        AppCache.getInstance().setShortLevels(getResources().getStringArray(R.array.storm_short_level));
    }

    public static Context getContext() {
        return sContext;
    }

}
