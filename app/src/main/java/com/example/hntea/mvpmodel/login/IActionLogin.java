package com.example.hnTea.mvpmodel.login;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.IActionBase;

/**
 * Created by 太能 on 2016/12/21.
 */
public interface IActionLogin<T> extends IActionBase<VolleyError> {
    void success(T data);
    void phpFail(String var);
    void smsCode(int code);
}
