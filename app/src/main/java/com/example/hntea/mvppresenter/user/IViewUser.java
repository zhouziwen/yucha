package com.example.hnTea.mvppresenter.user;

import com.android.volley.VolleyError;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by 太能 on 2016/12/23.
 */
public interface IViewUser<T> extends IViewBase<VolleyError> {
    void onSuccess(T response);
    void onPhpFail(String var);
}
