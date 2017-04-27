package com.gofit;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by anarasim on 4/25/2017.
 */

public class ExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
    Activity activity;
    public ExceptionHandler(Activity mainActivity) {
    activity = mainActivity;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        Toast.makeText(activity, "Location Settings Changed!! Please switch on Location for better functionality",
//                Toast.LENGTH_LONG).show();
        Log.d("Unhandled Exception","Unhandled"+e.getStackTrace());
    }
}
