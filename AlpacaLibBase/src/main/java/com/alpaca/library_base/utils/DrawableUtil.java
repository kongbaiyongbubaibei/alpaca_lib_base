package com.alpaca.library_base.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

import com.alpaca.library_base.base.BaseApplication;

import androidx.annotation.NonNull;

/**
 * @文件名: DrawableUtil
 * @功能描述:
 * @Date : 2021/3/3
 * @email:
 * @修改记录:
 */
public class DrawableUtil {

    public static Drawable tintDrawable(@NonNull GradientDrawable drawable, int color) {
        drawable.setColor(color);
        return drawable;
    }

    public static Drawable tintDrawable(@NonNull GradientDrawable drawable, int color, float[] radii) {
        drawable.setColor(color);
        drawable.setCornerRadii(radii);
        return drawable;
    }

    public static void setTextLeftDrawable(int resId, TextView textView) {
        if (resId == 0) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = BaseApplication.getInstance().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setTextRightDrawable(int resId, TextView textView) {
        if (resId == 0) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = BaseApplication.getInstance().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setTextTopDrawable(int resId, TextView textView) {
        if (resId == 0) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = BaseApplication.getInstance().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

}
