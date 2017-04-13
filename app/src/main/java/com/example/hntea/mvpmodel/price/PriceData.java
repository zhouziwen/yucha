package com.example.hnTea.mvpmodel.price;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.price.bean.B_ProjectDetailModel;
import com.example.hnTea.mvpmodel.price.bean.B_ProjectModel;
import com.example.hnTea.mvpmodel.price.bean.B_SingleDetailModel;
import com.example.hnTea.mvpmodel.price.bean.B_SingleModel;
import com.example.hnTea.mvpmodel.price.bean.BaseX_ProjectDetail;
import com.example.hnTea.mvpmodel.price.bean.X_ProjectModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleDetailModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleModel;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/11/15.
 */
public class PriceData extends BaseModel {

    //**********************************************产品询价列表*************************************
    public void getX_SinglePriceListData(final String brand,
                                         final String time,
                                         final String product_name,
                                         final int page,
                                         final IActionPrice<List<X_SingleModel>> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPrice.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "Inquiry");
                map.put("m", "showProductInquiry");
                map.put("brand", TextUtils.isEmpty(brand) ? "" : brand);
                map.put("time", TextUtils.isEmpty(time) ? "" : time);
                map.put("product_name", TextUtils.isEmpty(product_name) ? "" : product_name);
                map.put("page", String.valueOf(page));
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String bigJson = jsonObject.getString("data");
//                                HashMap<String, String> jsonMap = JsonUtils.parseArray(bigJson, HashMap.class);
//                                String var = JsonUtils.toJsonString(jsonMap.get("info"));
                                final List<X_SingleModel> list = JsonUtils.parseArray(bigJson,
                                        new TypeToken<List<X_SingleModel>>() {
                                        });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPrice.success(list);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPrice.phpFail(jsonObject.getString("message"));
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
                                iActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //**********************************************产品询价详情列表*************************************
    public void getX_singleDetailListData(final String inquiry_sn, final IActionPrice<X_SingleDetailModel> detailIActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        detailIActionPrice.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "Inquiry");
                map.put("m", "productInquiryDetail");
                map.put("inquiry_sn", inquiry_sn);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String bigJson = jsonObject.getString("data");
                                final X_SingleDetailModel model = JsonUtils.parseArray(bigJson, X_SingleDetailModel.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        detailIActionPrice.success(model);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            detailIActionPrice.phpFail(jsonObject.getString("message"));
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
                                detailIActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //**********************************************项目询价详情列表*************************************
    public void getX_ProjectDetailListData(final String inquiry_sn,
                                           final IActionPrice<BaseX_ProjectDetail> detailIActionPrice) {

        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        detailIActionPrice.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "Inquiry");
                map.put("m", "project_inquiry_product_list");
                map.put("inquiry_sn", inquiry_sn);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                final String data =jsonObject.getString("data");
                                final BaseX_ProjectDetail detail =JsonUtils.parseArray(data,BaseX_ProjectDetail.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                       detailIActionPrice.success(detail);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            detailIActionPrice.phpFail(jsonObject.getString("message"));
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
                                detailIActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }

        });

    }

    //**********************************************产品报价详情列表*************************************
    public void getB_SingleDetailListData(final String offer_sn, final IActionPrice<B_SingleDetailModel> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                iActionPrice.start("");
            }
        });
        final TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "offer");
        map.put("m", "productItem");
        map.put("offer_sn", offer_sn);
        map.put("token", MyApplication.getUserToken());
        Volley_StringRequest.toRequest(map, new VolleyBack() {
            @Override
            public void onRequestSuccess(final JSONObject jsonObject) {
                try {
                    if (jsonObject.getInt("code") == 1) {
                        String json = jsonObject.getString("data");
                        Log.i("tag", json);
                       final B_SingleDetailModel model = JsonUtils.parseArray(json, B_SingleDetailModel.class);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPrice.success(model);
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    iActionPrice.phpFail(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onVolleyError(final VolleyError volleyError) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPrice.fail(volleyError);
                    }
                });
            }
        });

    }

    //**********************************************项目报价详情列表*************************************
    public void getB_ProjectDetailListData(final String offer_sn, final IActionPrice<B_ProjectDetailModel> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                iActionPrice.start("");
            }
        });
        final TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "offer");
        map.put("m", "projectItem");
        map.put("offer_sn", offer_sn);
        map.put("token", MyApplication.getUserToken());
        Volley_StringRequest.toRequest(map, new VolleyBack() {
            @Override
            public void onRequestSuccess(final JSONObject jsonObject) {
                try {
                    if (jsonObject.getInt("code") == 1) {
                        String json = jsonObject.getString("data");
                        final B_ProjectDetailModel model = JsonUtils.parseArray(json, B_ProjectDetailModel.class);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPrice.success(model);
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    iActionPrice.phpFail(jsonObject.getString("message"));
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
                       iActionPrice.fail(volleyError);
                    }
                });
            }
        });
    }

    //*********************************************产品报价列表**************************************
    public void getB_SinglePriceListData(final int page,
                                         final String brand,
                                         final String start_time,
                                         final String product_name,
                                         final IActionPrice<List<B_SingleModel>> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPrice.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "offer");
                map.put("m", "productList");
                map.put("page", String.valueOf(page));
                map.put("limit", "10");
                map.put("brand", TextUtils.isEmpty(brand) ? "" : brand);
                map.put("start_time", TextUtils.isEmpty(start_time) ? "" : start_time);
                map.put("product_name", TextUtils.isEmpty(product_name) ? "" : product_name);
                map.put("token", TextUtils.isEmpty(MyApplication.getUserToken()) ?
                        "" : MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                final List<B_SingleModel> list = JsonUtils.parseArray(json, new TypeToken<List<B_SingleModel>>() {
                                });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPrice.success(list);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPrice.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                        }
                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //*********************************************项目报价列表**************************************
    public void getB_ProjectPriceListData(final int page,
                                          final IActionPrice<List<B_ProjectModel>> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPrice.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "offer");
                map.put("m", "projectList");
                map.put("page", String.valueOf(page));
                map.put("limit", "10");
                map.put("token", TextUtils.isEmpty(MyApplication.getUserToken()) ?
                        "" : MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");

                                final List<B_ProjectModel> list = JsonUtils.parseArray(json, new TypeToken<List<B_ProjectModel>>() {
                                });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPrice.success(list);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPrice.phpFail(jsonObject.getString("message"));
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
                                iActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //*********************************************项目询价列表**************************************
    public void getX_ProjectListData(final String province,
                                     final String time,
                                     final String project_type,
                                     final String project_name,
                                     final int page,
                                     final IActionPrice<List<X_ProjectModel>> iActionPrice) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionPrice.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "Inquiry");
                map.put("m", "showProjectInquiry");
                map.put("province", TextUtils.isEmpty(province) ? "" : province);
                map.put("time", TextUtils.isEmpty(time) ? "" : time);
                map.put("project_type", TextUtils.isEmpty(project_type) ? "" : project_type);
                map.put("project_name", TextUtils.isEmpty(project_name) ? "" : project_name);
                map.put("page", String.valueOf(page));
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                final List<X_ProjectModel> list = JsonUtils.parseArray(json,
                                        new TypeToken<List<X_ProjectModel>>() {
                                        });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionPrice.success(list);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionPrice.phpFail(jsonObject.getString("message"));
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
                                iActionPrice.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


}
