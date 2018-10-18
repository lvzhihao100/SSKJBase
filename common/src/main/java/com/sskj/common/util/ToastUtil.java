package com.sskj.common.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


import com.sskj.common.base.App;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class ToastUtil {
    private static Toast toast;

    private static void show(CharSequence message, boolean isShort) {
        if (toast != null) {
            toast.setText(message);
            toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast = Toast.makeText(App.INSTANCE, message, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private static void show(int message, boolean isShort) {
        if (toast != null) {
            toast.setText(message);
            toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast = Toast.makeText(App.INSTANCE, message, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> show(message, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShort(CharSequence message) {
        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> show(message, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShort(int message) {
        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> show(message, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLong(int message) {
        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> show(message, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
