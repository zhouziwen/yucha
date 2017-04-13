package com.example.hnTea.https;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by 太能 on 2016/12/22.
 */
public interface VolleyBack {

    void onRequestSuccess(JSONObject jsonObject);

    void onVolleyError(VolleyError volleyError);

}
