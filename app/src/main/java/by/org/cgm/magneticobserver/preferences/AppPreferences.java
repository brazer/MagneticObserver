package by.org.cgm.magneticobserver.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import by.org.cgm.magneticobserver.MagneticObserverApplication;
import by.org.cgm.magneticobserver.util.StringUtils;

/**
 * Author: Anatol Salanevich
 * Date: 16.10.2015
 */
public class AppPreferences {

    private static AppPreferences sInstance;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private AppPreferences() {
        mPreferences =
                PreferenceManager.getDefaultSharedPreferences(MagneticObserverApplication.getContext());
        mEditor = mPreferences.edit();
    }

    public static AppPreferences getInstance() {
        if (sInstance == null) {
            sInstance = new AppPreferences();
        }
        return sInstance;
    }

    public String getString(final String key) {
        return mPreferences.getString(key, StringUtils.EMPTY);
    }

    public void putString(final String key, final String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

}
