package com.example.hnTea.https;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.hnTea.utils.WorkFactory;
import com.example.hnTea.utils.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/12/22.
 */
public class Volley_StringRequest {

    public static void toRequest(final TreeMap<String, String> map,
                                 final VolleyBack mVolleyBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                BaseUrl.getBaseUrl(), response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        mVolleyBack.onRequestSuccess(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> mVolleyBack.onVolleyError(error)) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> endMap = new HashMap<String, String>();
                try {
                    endMap = DecodeMap.getParam(map);
                    Logger.i("param",endMap.toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return endMap;
            }
        };
        Logger.i("stringrequet",stringRequest.toString());
        WorkFactory.instance.queue().add(stringRequest);
    }
}
