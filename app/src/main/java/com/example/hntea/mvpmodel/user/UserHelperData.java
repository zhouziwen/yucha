package com.example.hnTea.mvpmodel.user;

import com.android.volley.VolleyError;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.user.bean.MyInfo;
import com.example.hnTea.mvpmodel.user.bean.UserhelperModel;
import com.example.hnTea.utils.WorkFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

/**
 * Created by jason_syf on 2017/4/20.
 * Email: jason_sunyf@163.com
 */

public class UserHelperData extends BaseModel {
    public void getUserHelperData( String tag,final IActionUser<UserhelperModel> iActionUser) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionUser.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "common");
            map.put("m", "about");
            map.put("tag", tag);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            UserhelperModel userhelperModel = JsonUtils.parseArray(json, UserhelperModel.class);
                            mHandler.post(() -> iActionUser.success(userhelperModel));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionUser.phpFail(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onVolleyError(VolleyError volleyError) {
                    mHandler.post(() -> iActionUser.fail(volleyError));
                }
            });
        });
    }
}
