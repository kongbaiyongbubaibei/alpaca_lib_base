package com.alpaca.library_base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class CopyStringUtils {
    public static void CopyString(Context activity, String str) {
        ClipboardManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", str);
            cm.setPrimaryClip(mClipData);
        }
    }
}
