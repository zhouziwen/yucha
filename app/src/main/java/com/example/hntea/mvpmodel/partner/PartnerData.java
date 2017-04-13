package com.example.hnTea.mvpmodel.partner;

import android.util.Log;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.cache.NewsListCache;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsDetailModel;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsModel;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/11/14.
 */
public class PartnerData extends BaseModel {

    //***************************合伙人资讯list数据*******************************
    public void getPartnerNewsData(final int page,
                                   final IActionPartner<List<PartnerNewsModel>> iActionPartner) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPartner.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "partner");
                map.put("m", "index");
                map.put("page", String.valueOf(page));
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {

                        try {
                            if (jsonObject.getInt("code") == 1) {
                                //success
                                String json = jsonObject.getString("data");
                                final List<PartnerNewsModel> list = JsonUtils.parseArray(json,
                                        new TypeToken<List<PartnerNewsModel>>() {
                                        });
                                for (PartnerNewsModel model : list) {
                                    if (model.getList().size()==2){
                                        model.setType(1);
                                    }else {
                                        model.setType(model.getList().size());
                                    }
                                    NewsListCache.getInstance(MyApplication.getContext()).addResultCache(null, model.getId(),
                                            model.getTitle(), model.getTime(), model.getType());
//                                    PartnerNewsListDao partnerNewsListDao = MyApplication.getDaoSession().getPartnerNewsListDao();
//                                    partnerNewsListDao.insert
//                                                (new PartnerNewsList(null, model.getId(),
//                                                        model.getTitle(), model.getTime(), model.getType()));
                                }
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPartner.success(list);
                                    }
                                });
                            } else {
                                //fail
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPartner.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPartner.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //*****************************合伙人资讯详情数据***************************************
    public void getPartnerNewsDetail(final String id,
                                     final IActionPartner<PartnerNewsDetailModel> iActionPartner){

        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPartner.start("");
                    }
                });
                TreeMap<String,String> map =new TreeMap<String, String>();
                map.put("c","partner");
                map.put("m","detail");
                map.put("id",id);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code")==1){
                                String json = jsonObject.getString("data");
                                final PartnerNewsDetailModel model
                                        =JsonUtils.parseArray(json,PartnerNewsDetailModel.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPartner.success(model);
                                    }
                                });
                            }else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPartner.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPartner.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //***************************合伙人报名数据*******************************
    public void mPartnerEnterData(final String name, final String phoneNum, final String sms, final IActionPartner<String> iActionPartner) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPartner.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "partner");
                map.put("m", "join");
                map.put("name", name);
                map.put("telephone", phoneNum);
                map.put("messagecode", sms);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPartner.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPartner.success("" + jsonObject);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPartner.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //***************************合作伙伴报名数据*******************************
    public void mBusinessPartnerData(final String name, final String phoneNum,
                                     final String address, final IActionPartner<String> iActionBusinessPartner) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionBusinessPartner.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "partner");
                map.put("m", "cooJoin");
                map.put("name", name);
                map.put("telephone", phoneNum);
                map.put("address", address);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        Log.i("data", jsonObject.toString());
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionBusinessPartner.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionBusinessPartner.success("" + jsonObject);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionBusinessPartner.fail(volleyError);
                            }

                        });
                    }
                });
            }
        });
    }
}