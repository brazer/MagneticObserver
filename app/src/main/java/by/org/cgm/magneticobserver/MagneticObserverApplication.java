package by.org.cgm.magneticobserver;

import android.app.Application;

import by.org.cgm.magneticobserver.network.API;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public class MagneticObserverApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        API.initialize();
        AppCache.initialize();
        AppCache.getInstance().setLevels(getResources().getStringArray(R.array.storm_level));
    }

}
