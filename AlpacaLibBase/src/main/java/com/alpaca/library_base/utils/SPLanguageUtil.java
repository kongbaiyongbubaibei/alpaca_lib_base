package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alpaca.library_base.BuildConfig;


public class SPLanguageUtil {

    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private static volatile SPLanguageUtil instance;

    private final SharedPreferences mSharedPreferences;

    public SPLanguageUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }


    public void saveLanguage(int select) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(TAG_LANGUAGE, select);
        edit.commit();
    }

    public int getSelectLanguage() {
        int defaultLanguage = BuildConfig.INTERNATIONAL ? 2 : 0;
        return mSharedPreferences.getInt(TAG_LANGUAGE, defaultLanguage);
    }

    public static SPLanguageUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SPLanguageUtil.class) {
                if (instance == null) {
                    instance = new SPLanguageUtil(context);
                }
            }
        }
        return instance;
    }
}