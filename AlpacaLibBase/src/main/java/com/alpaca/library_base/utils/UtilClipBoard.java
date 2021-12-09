package com.alpaca.library_base.utils;

import android.content.ClipboardManager;
import android.content.Context;

public class UtilClipBoard {

    public static void copy(Context context, String text) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(text.trim());
    }

    /**
     * 实现粘贴功能
     *
     */
    public static String paste(Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
}