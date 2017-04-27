package com.gofit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * Created by anarasim on 4/25/2017.
 */

public class MyPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            Toast.makeText(context, "Location Settings Changed!! Please switch on Location for better functionality",
                    Toast.LENGTH_LONG).show();
//            Intent pushIntent = new Intent(context, LocalService.class);
//            context.startService(pushIntent);

            // Vibrate the mobile phone
//            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//            vibrator.vibrate(2000);
        }


}}