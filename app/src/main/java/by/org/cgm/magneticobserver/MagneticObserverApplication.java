package by.org.cgm.magneticobserver;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

import by.org.cgm.magneticobserver.network.API;

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
        JodaTimeAndroid.init(this);
        API.initialize();
        AppCache.initialize();
        AppCache.getInstance().setLevels(getResources().getStringArray(R.array.storm_level));
        AppCache.getInstance().setShortLevels(getResources().getStringArray(R.array.storm_short_level));
    }

    public static Context getContext() {
        return sContext;
    }

}
