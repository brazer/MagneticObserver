package by.org.cgm.magneticobserver.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.model.MagMessage;
import by.org.cgm.magneticobserver.preferences.AppPreferences;
import by.org.cgm.magneticobserver.preferences.PreferencesKeys;
import by.org.cgm.magneticobserver.ui.activity.MainActivity;
import by.org.cgm.magneticobserver.ui.dialog.RangeBarPreference;
import by.org.cgm.magneticobserver.util.StringUtils;

/**
 * Author: Anatol Salanevich
 * Date: 14.08.2015
 */
public class CustomGcmListenerService extends GcmListenerService {

    private static final String TAG = CustomGcmListenerService.class.getSimpleName();
    private MagMessage mMagMessage;

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */
        mMagMessage = StringUtils.parse(message);
        mMagMessage.convertToLocalTime();
        processMessage();
    }
    // [END receive_message]

    private void processMessage() {
        String strVal = AppPreferences.getInstance()
                .getString(PreferencesKeys.NOTIFICATIONS, RangeBarPreference.DEFAULT_VALUE);
        RangeBarPreference.SettingsValue value = new RangeBarPreference.SettingsValue();
        value.setValue(strVal);
        int normalizedVal = mMagMessage.getValue()/2;
        if (value.getLeft() <= normalizedVal && value.getRight() >= normalizedVal) {
            /**
             * In some cases it may be useful to show a notification indicating to the user
             * that a message was received.
             */
            sendNotification(mMagMessage.toMessage(getString(R.string.message)));
        }
    }

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MagMessage.TAG, mMagMessage);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.title_notification))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
