package com.alpaca.library_base.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;

/**
 * @文件名: ClipboardUtils
 * @功能描述:
 * @Date : 2020/12/28
 * @email:
 * @修改记录:
 */
public class ClipboardUtils {
    public static void getClipboardText(Activity activity, getTextListen listen) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = "";
                if (Looper.getMainLooper() == Looper.myLooper()) {//主线程
                    ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = clipboardManager.getPrimaryClip();
                    if (clipData != null && clipData.getItemCount() > 0) {
                        ClipData.Item item = clipData.getItemAt(0);
                        if (item != null && !TextUtils.isEmpty(item.getText())) {
                            text = item.getText().toString();
                        }
                    }
                    if (listen != null) {
                        listen.getText(text);
                    }
                } else {
                    Looper.prepare();
                    ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = clipboardManager.getPrimaryClip();
                    if (clipData != null && clipData.getItemCount() > 0) {
                        ClipData.Item item = clipData.getItemAt(0);
                        if (item != null && !TextUtils.isEmpty(item.getText())) {
                            text = item.getText().toString();
                        }
                    }
                    if (listen != null) {
                        listen.getText(text);
                    }
                    Looper.loop();
                }
            }
        }).start();
    }

    public interface getTextListen {
        void getText(String text);
    }
}
