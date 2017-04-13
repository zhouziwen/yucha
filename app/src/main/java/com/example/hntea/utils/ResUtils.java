package com.example.hnTea.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.hnTea.MyApplication;

/**
 * Created by 太能 on 2016/11/10.
 */
public class ResUtils {
    private static Resources getResource() {
        return MyApplication.getInstance().getResources();
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);
    }

    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    public static String getString(int id) {
        if (id < 0) {
            return "";
        }
        return getResource().getString(id);
    }

    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }
}
