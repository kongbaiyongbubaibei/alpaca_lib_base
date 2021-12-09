package com.alpaca.library_base.utils;

import android.view.View;

import com.alpaca.library_base.BuildConfig;


/**
 * class desc :
 * <p>
 * 马甲包相关工具类
 *
 * @author : zzh
 * @date : 2019/12/4
 */
public class MajiaUtils {

    public static boolean isMajiabao() {
        return BuildConfig.IS_MAJIABAO;
    }

    public static void shareBtnEnable(View view) {
        view.setVisibility(BuildConfig.ENABLE_SHARE && view.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
    }
}
