package com.example.hnTea.mvppresenter.home;

import com.android.volley.VolleyError;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by 太能 on 2016/11/16.
 */
public interface IViewHome<T> extends IViewBase<VolleyError> {
    void onSuccess(T data);
    void onPhpFail(String var);
}
