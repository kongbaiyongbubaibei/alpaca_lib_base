package com.alpaca.library_base.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SharePicUtils {

    public static void savePic(String path, Bitmap bitmap) throws IOException {
        //save qrcode
        File file = new File(path);
        if (file.exists())
            file.delete();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        //if we use CompressFormat.JPEG,bitmap will have black background
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        bos.flush();
        bos.close();
        bitmap.recycle();
    }

    //get screenShoot
    public static Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    public static void shareData(Activity activity, String val) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,val);
        shareIntent.setType("text/plain");
        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

}
