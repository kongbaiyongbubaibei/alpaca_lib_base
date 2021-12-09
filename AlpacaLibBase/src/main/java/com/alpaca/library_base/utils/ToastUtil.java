package com.alpaca.library_base.utils;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    private ToastUtil() {

    }

    private static void showToast(Context context, String text, int length) {
        if (context == null) return;

        if (Looper.getMainLooper() == Looper.myLooper()) {//主线程
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(context.getApplicationContext(), text, length);
            mToast.setText(text);
            mToast.show();
        } else {//子线程
            Looper.prepare();
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(context.getApplicationContext(), text, length);
            mToast.setText(text);
            mToast.show();
            Looper.loop();
        }
    }

    public static void showToastShort(Context context, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(Context context, String text) {
        if (TextUtils.isEmpty(text))
            return;
        showToast(context, text, Toast.LENGTH_LONG);
    }

    public static void showToastShort(Context context, int textId) {
        showToastShort(context, context.getResources().getString(textId));
    }

    public static void showToastLong(Context context, int textId) {
        showToastLong(context, context.getResources().getString(textId));
    }

}
