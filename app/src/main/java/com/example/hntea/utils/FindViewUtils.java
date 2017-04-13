package com.example.hnTea.utils;

import android.util.Log;
import android.view.View;

/**
 * Created by 太能 on 2016/11/10.
 */
public class FindViewUtils {
    private final String mTag =getClass().getName();
    private View mView;

    public FindViewUtils(View view){
        mView=view;
    }

    public <T extends View> T findViewById(int viewId){
        if (mView==null){
            Log.e(mTag,"");
            return null;
        }
        View view =mView.findViewById(viewId);
        return (T) view;
    }
}
