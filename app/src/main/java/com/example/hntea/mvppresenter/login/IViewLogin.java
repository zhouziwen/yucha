package com.example.hnTea.mvppresenter.login;

import com.android.volley.VolleyError;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by 太能 on 2016/12/21.
 */
public interface IViewLogin<T> extends IViewBase<VolleyError> {
    void onSuccess(T data);
    void onPhpFail(String var);
    void onSmsCode(int code);
}
