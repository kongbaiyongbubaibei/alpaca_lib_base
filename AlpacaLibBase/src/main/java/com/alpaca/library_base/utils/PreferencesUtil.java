package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

/**
 * @文件名: PreferencesUtil
 * @功能描述:
 * @Date : 2018/3/1
 * @email:
 * @修改记录:
 */
public class PreferencesUtil {

    public static String PREFERENCE_NAME = "YtEdu";

    private PreferencesUtil() {
        throw new AssertionError();
    }

    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, (String)null);
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getString(key, defaultValue);
    }

    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getInt(key, defaultValue);
    }

    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static long getLong(Context context, String key) {
        return getLong(context, key, -1L);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getLong(key, defaultValue);
    }

    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1.0F);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getFloat(key, defaultValue);
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getBoolean(key, defaultValue);
    }

    public static Set<String> getStringSet(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getStringSet(key, (Set)null);
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return settings.getStringSet(key, defaultValue);
    }

    public static void clear(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        settings.edit().clear().commit();
    }

    public static void remove(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, 0);
        settings.edit().remove(key);
    }

    public static <T> void saveBean2Sp(Context context, T t, String fileName, String keyName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            byte[] bytes = bos.toByteArray();
            String ObjStr = Base64.encodeToString(bytes, Base64.DEFAULT);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(keyName, ObjStr);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T extends Object> T getBeanFromSp(Context context, String fileName, String keyNme) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        byte[] bytes = Base64.decode(preferences.getString(keyNme, ""), Base64.DEFAULT);
        ByteArrayInputStream bis;
        ObjectInputStream ois = null;
        T obj = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }


}
