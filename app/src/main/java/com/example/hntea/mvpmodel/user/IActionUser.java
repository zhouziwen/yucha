package com.example.hnTea.mvpmodel.user;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.IActionBase;

/**
 * Created by 太能 on 2016/12/23.
 */
public interface IActionUser<T> extends IActionBase<VolleyError>{

    void success(T response);
    void phpFail(String var);
}
