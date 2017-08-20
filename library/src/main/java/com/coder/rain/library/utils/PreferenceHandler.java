package com.coder.rain.library.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Describe : Key-Value 属性存储
 * Email:baossrain99@163.com
 * Created by Rain on 17-5-2.
 */
public class PreferenceHandler {

    private static final String TAG = "PreferenceHandler";

    public static final String PREFS_NAME = "config";

    private static SharedPreferences settings;

    public PreferenceHandler(Application context) {
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return settings.getBoolean(key, defValue);
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return this.settings.getInt(key, defValue);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return this.getString(key, "");
    }

    public String getString(String key, String defValue) {
        return this.settings.getString(key, defValue);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

}

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */
