package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @文件名: ScreenShotUtils
 * @功能描述:
 * @Date : 2019/3/18
 * @email:
 * @修改记录:
 */
public class ScreenShotUtils {


    private static String TAG = "ScreenShotUtils";

    public static Bitmap creatBitmap(View view) {
        Bitmap bmp = null;
        try {
            int w = view.getMeasuredWidth();
            int h = view.getMeasuredHeight();
            bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bmp);
            c.drawColor(Color.WHITE);

            view.layout(0, 0, w, h);
            view.draw(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static Bitmap getViewBp(View v) {
        if (null == v) {
            return null;
        }

        Bitmap b = null;
        try {
            v.setDrawingCacheEnabled(true);
            v.buildDrawingCache();
            if (Build.VERSION.SDK_INT >= 11) {
                v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
                        View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                        v.getHeight(), View.MeasureSpec.EXACTLY));
                v.layout((int) v.getX(), (int) v.getY(),
                        (int) v.getX() + v.getMeasuredWidth(),
                        (int) v.getY() + v.getMeasuredHeight());
            } else {
                v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            }
            b = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

            v.setDrawingCacheEnabled(false);
            v.destroyDrawingCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static Bitmap createBitmap3(View v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

    public static Bitmap createBitmap4(View v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        v.draw(c);
        return bmp;
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "SavaShare");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        String path = file.getAbsolutePath();
//        Log.i(TAG, "saveImageToGallery: "+path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }
}