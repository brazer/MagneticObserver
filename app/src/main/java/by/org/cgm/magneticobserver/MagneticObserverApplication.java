package by.org.cgm.magneticobserver;

import android.app.Application;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class MagneticObserverApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCache.initialize();
        AppCache.getInstance().setLevels(getResources().getStringArray(R.array.storm_level));
    }

}
