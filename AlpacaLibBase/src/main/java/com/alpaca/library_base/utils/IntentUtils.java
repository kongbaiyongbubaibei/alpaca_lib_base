package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * class desc :
 *
 * @author : zzh
 * date : 2020/3/5
 */
public class IntentUtils {
    
    public static void openWeb(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
