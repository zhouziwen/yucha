package com.example.hnTea.mvppresenter.price;

import com.android.volley.VolleyError;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by 太能 on 2016/11/15.
 */
public interface IViewPrice<T> extends IViewBase<VolleyError> {
    void onSuccess(T data);
    void onPhpFail(String var);
}
