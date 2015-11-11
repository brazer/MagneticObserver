package by.org.cgm.magneticobserver.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.jetbrains.annotations.NotNull;

import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.model.MagMessage;
import by.org.cgm.magneticobserver.service.RegistrationIntentService;
import by.org.cgm.magneticobserver.ui.fragment.ChartsFragment;
import by.org.cgm.magneticobserver.ui.fragment.MessageFragment;
import by.org.cgm.magneticobserver.util.FragmentTags;
import by.org.cgm.magneticobserver.util.FragmentUtils;

public class MainActivity extends AppCompatActivity implements MessageFragment.OnShowChartsListener {

    private final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean gotToken = sharedPreferences
                        .getBoolean(RegistrationIntentService.GOT_TOKEN, false);
                if (!gotToken) {
                    Toast.makeText(context, R.string.no_token_retrieved_message, Toast.LENGTH_LONG).show();
                }
            }
        };

        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        MagMessage magMessage = (MagMessage) getIntent().getSerializableExtra(MagMessage.TAG);
        if (magMessage != null) showMessageFragment(magMessage);
        else showChartsFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(RegistrationIntentService.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private void showChartsFragment() {
        Fragment fragment = new ChartsFragment();
        FragmentUtils.replaceContent(this, R.id.container, fragment, FragmentTags.CHARTS);
    }

    private void showMessageFragment(@NotNull MagMessage message) {
        Fragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putSerializable(MagMessage.TAG, message);
        fragment.setArguments(args);
        FragmentUtils.replaceContent(this, R.id.container, fragment, FragmentTags.MESSAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowChartsFragment() {
        showChartsFragment();
    }
}
