package com.example.hnTea.mvpmodel.user;

import com.android.volley.VolleyError;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.ui.me.bean.HistoryModel;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;
import com.umeng.socialize.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by jason_syf on 2017/3/15.
 * Email: jason_sunyf@163.com
 */

public class UserLookHistoryData extends BaseModel {
    public void getUserHistory(final String goodsId, final IActionUser<List<HistoryModel>> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(() -> iActionUser.start(""));
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "Goods");
                map.put("m", "history_goods");
                map.put("goodsId", goodsId);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                JSONObject obj = jsonObject.getJSONObject("data");
                                String json =obj.getString("goods");
                                List<HistoryModel> list =JsonUtils.parseArray(json,new TypeToken<List<HistoryModel>>(){});
                               mHandler.post(() -> {
                                   iActionUser.success(list);
                               });
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
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(() -> iActionUser.fail(volleyError));
                    }
                });
            }
        });
    }
}
