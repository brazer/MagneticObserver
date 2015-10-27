package by.org.cgm.magneticobserver.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import by.org.cgm.magneticobserver.R;

/**
 * Author: Anatol Salanevich
 * Date: 14.10.2015
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}
