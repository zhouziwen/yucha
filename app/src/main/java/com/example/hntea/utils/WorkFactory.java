package com.example.hnTea.utils;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.hnTea.MyApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 太能 on 2016/11/10.
 */
public enum WorkFactory {
    instance;
    private ExecutorService mService;
    private RequestQueue mRequestQueue;

    WorkFactory() {
        mService = Executors.newCachedThreadPool();
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());
    }

    public ExecutorService service() {
        return mService;
    }

    public RequestQueue queue() {
        return mRequestQueue;
    }

}
