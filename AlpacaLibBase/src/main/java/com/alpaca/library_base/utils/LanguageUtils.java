package com.alpaca.library_base.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.alpaca.library_base.BuildConfig;

import java.util.Locale;

public class LanguageUtils {

    public static Locale getSetLocale(Context context) {

        if (BuildConfig.INTERNATIONAL) {
            return Locale.ENGLISH;
        }

        int currentLanguage = SPLanguageUtil.getInstance(context).getSelectLanguage();

        switch (currentLanguage) {
            case ChangeLanguageHelper.LANGUAGE_DEFAULT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return Resources.getSystem().getConfiguration().getLocales().get(0);//解决了获取系统默认错误的问题
                } else {
                    return Locale.getDefault();
                }
            case ChangeLanguageHelper.LANGUAGE_CHINA:
                return Locale.CHINA;
            case ChangeLanguageHelper.LANGUAGE_ENGLISH:
                return Locale.ENGLISH;
            default:
                return Locale.ENGLISH;
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context wrapContext(Context context) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtils.getSetLocale(context);

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }

    public static void applyChange(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        Locale locale = getSetLocale(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            conf.setLocales(localeList);
        } else {
            conf.locale = locale;
            try {
                conf.setLayoutDirection(locale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.updateConfiguration(conf, dm);

    }
}
