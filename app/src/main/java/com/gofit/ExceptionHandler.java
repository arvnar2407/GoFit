package com.gofit;

import android.app.Activity;

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

    }
}
