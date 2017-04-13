package com.example.hnTea.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.hnTea.MyApplication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 太能 on 2017/3/3.
 */

public class SystemUtil {
    //获取虚拟功能键高度
    public static int getVirtualBarHeight() {
        int vh = 0;
        WindowManager windowManager = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }


    /**
     * 根据map获取map包含的key,返回set
     *
     * @param map
     * @return
     */
    public static Set<String> getKeySetByMap(Map<String, String> map) {

        Set<String> keySet = new HashSet<String>();
        keySet.addAll(map.keySet());

        return keySet;
    }

    /**
     * 根据key的set返回key的list
     *
     * @param set
     * @return
     */
    public static List<String> getKeyListBySet(Set<String> set) {
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(set);
        return keyList;
    }

    /**
     * 根据map返回key和value的list
     *
     * @param map
     * @param isKey
     *            true为key,false为value
     * @return
     */
    public static List<String> getListByMap(Map<String, String> map,
                                            boolean isKey) {
        List<String> list = new ArrayList<String>();

        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            if (isKey) {
                list.add(key);
            } else {
                list.add(map.get(key));
            }
        }

        return list;
    }

}
