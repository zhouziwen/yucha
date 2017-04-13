package com.example.hnTea.mvpmodel.home;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.IActionBase;

/**
 * Created by 太能 on 2016/11/16.
 */
public interface IActionHome<T> extends IActionBase<VolleyError> {
    void success(T data);
    void phpFail(String var);
}
