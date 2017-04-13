package com.example.hnTea.mvpmodel.me;

import android.util.Log;
import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_B_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_J_Model;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Project_offer_detail;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_B_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_B_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_J_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_J_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_X_SingleModel;
import com.example.hnTea.mvpmodel.price.IActionPrice;
import com.example.hnTea.mvpmodel.user.IActionUser;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/11/15.
 */
public class MePriceData extends BaseModel {

    //*********************************************修改手机号*****************************************
    public void modify_phoneNum( final String old_userphone , final String password, final String new_userphone,
                                final String sms, final IActionUser<String>  iActionModifyNum ){
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionModifyNum.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "user");
            map.put("m", "changePhone");
            map.put("old_userphone", old_userphone);
            map.put("password", password);
            map.put("new_userphone", new_userphone);
            map.put("messagecode", sms);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 0) {
                            mHandler.post(() -> {
                                try {
                                    iActionModifyNum.phpFail(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            mHandler.post(() -> iActionModifyNum.success("" + jsonObject));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                @Override
                public void onVolleyError(final VolleyError volleyError) {
                    mHandler.post(() -> iActionModifyNum.fail(volleyError));
                }
            });
        });

    }

    //*********************************************我的产品询价***************************************
    public void getMe_X_SingleListData(final int page,
                                       final IActionPrice<List<Me_X_SingleModel>> iActionPrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionPrice.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Inquiry");
            map.put("m", "showMyProductInquiryList");
            map.put("token", MyApplication.getUserToken());
            map.put("page", String.valueOf(page));
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final List<Me_X_SingleModel> list =
                                    JsonUtils.parseArray(json, new TypeToken<List<Me_X_SingleModel>>() {
                                    });
                            for (Me_X_SingleModel model : list) {
                                Log.i("request", model.getProduct_name());
                            }
                            mHandler.post(() -> iActionPrice.success(list));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionPrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionPrice.fail(volleyError));
                }
            });
        });
    }


    //*********************************************我的项目询价************************************
    public void getMe_X_ProjectListData(final int page,
                                        final IActionPrice<List<Me_X_ProjectModel>> iActionPrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionPrice.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Inquiry");
            map.put("m", "showMyProjectInquiryList");
            map.put("token", MyApplication.getUserToken());
            map.put("page", String.valueOf(page));
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final List<Me_X_ProjectModel> list =
                                    JsonUtils.parseArray(json, new TypeToken<List<Me_X_ProjectModel>>() {
                                    });
                            mHandler.post(() -> iActionPrice.success(list));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionPrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionPrice.fail(volleyError));
                }
            });
        });
    }

    //*********************************************我的产品报价************************************
    public void getMe_B_Single(final int page,final IActionMePrice<List<Me_B_SingleModel>> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> iActionMePrice.start(""));
        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "Offer");
        map.put("m", "MyProductOfferList");
        map.put("token", MyApplication.getUserToken());
        map.put("page", String.valueOf(page));
        Volley_StringRequest.toRequest(map, new VolleyBack() {
            @Override
            public void onRequestSuccess(final JSONObject jsonObject) {
                Log.i("data", jsonObject.toString());
                try {
                    if (jsonObject.getInt("code") == 1) {
                        String json = jsonObject.getString("data");
                        final List<Me_B_SingleModel> list =
                                JsonUtils.parseArray(json, new TypeToken<List<Me_B_SingleModel>>() {
                                });
                        for (Me_B_SingleModel model : list) {
                            Log.i("request", model.getProduct_name());
                        }
                        mHandler.post(() -> iActionMePrice.success(list));
                    } else {
                        mHandler.post(() -> {
                            try {
                                iActionMePrice.phpFail(jsonObject.getString("message"));
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
                mHandler.post(() -> iActionMePrice.fail(volleyError));
            }

        });
    }

    //*********************************************我的项目报价************************************
    public void getMe_B_Project(final int page,final IActionMePrice<List<Me_B_ProjectModel>> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> iActionMePrice.start(""));
        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "Offer");
        map.put("m", "MyProjectOfferList");
        map.put("token", MyApplication.getUserToken());
        map.put("page", String.valueOf(page));
        Volley_StringRequest.toRequest(map, new VolleyBack() {
            @Override
            public void onRequestSuccess(final JSONObject jsonObject) {
                Log.i("data1", jsonObject.toString());
                try {
                    if (jsonObject.getInt("code") == 1) {
                        String json = jsonObject.getString("data");
                        final List<Me_B_ProjectModel> list =
                                JsonUtils.parseArray(json, new TypeToken<List<Me_B_ProjectModel>>() {
                                });
                        for (Me_B_ProjectModel model : list) {
                            Log.i("request", model.getProject_name());
                        }
                        mHandler.post(() -> iActionMePrice.success(list));
                    } else {
                        mHandler.post(() -> {
                            try {
                                iActionMePrice.phpFail(jsonObject.getString("message"));
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
                mHandler.post(() -> iActionMePrice.fail(volleyError));
            }

        });
    }

    //*********************************************我的产品竟价************************************
    public void getMe_J_Single(final int page, final int limit,
                               final IActionMePrice<List<Me_J_SingleModel>> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionMePrice.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Bidding");
            map.put("m", "productBidList");
            map.put("token", MyApplication.getUserToken());
            map.put("page", String.valueOf(page));
            map.put("limit", String.valueOf(limit));
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final List<Me_J_SingleModel> list =
                                    JsonUtils.parseArray(json, new TypeToken<List<Me_J_SingleModel>>() {
                                    });
                            for (Me_J_SingleModel model : list) {
                                Log.i("request", model.getProduct_name());
                            }
                            mHandler.post(() -> iActionMePrice.success(list));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }@Override
                public void onVolleyError(final VolleyError volleyError) {
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }
    //*********************************************我的项目竟价************************************
    public void getMe_J_Project(final int page, final int limit,
                               final IActionMePrice<List<Me_J_ProjectModel>> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionMePrice.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Bidding");
            map.put("m", "projectBidList");
            map.put("token", MyApplication.getUserToken());
            map.put("page", String.valueOf(page));
            map.put("limit", String.valueOf(limit));
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final List<Me_J_ProjectModel> list =
                                    JsonUtils.parseArray(json, new TypeToken<List<Me_J_ProjectModel>>() {
                                    });
                            mHandler.post(() -> iActionMePrice.success(list));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }@Override
                public void onVolleyError(final VolleyError volleyError) {
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }
    //**********************************************详情********************************************
    //我的产品询价详情
    public void getMeDetail_X_Single(final String inquiry_sn,
                                     final IActionMePrice<Detail_X_SingleModel> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionMePrice.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Inquiry");
            map.put("m", "MyProductInquiryDetail");
            map.put("inquiry_sn", inquiry_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_X_SingleModel model =
                                    JsonUtils.parseArray(json, Detail_X_SingleModel.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }

    //我的项目询价详情
    public void getMeDetail_X_Project(final String inquiry_sn, final IActionMePrice<Detail_X_ProjectModel> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    iActionMePrice.start("");
                }
            });
            final TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Inquiry");
            map.put("m", "MyProjectInquiryDetail");
            map.put("inquiry_sn", inquiry_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_X_ProjectModel model = JsonUtils.parseArray(json, Detail_X_ProjectModel.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });

    }

    //  我的项目询价-供应商报价详情
    public void getMeDetail_X_Project_Offer_detail(final String offer_sn, final IActionMePrice<Detail_X_Project_offer_detail> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    iActionMePrice.start("");
                }
            });
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "Inquiry");
            map.put("m", "offerDetail");
            map.put("offer_sn", offer_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_X_Project_offer_detail model = JsonUtils.parseArray(json, Detail_X_Project_offer_detail.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }
    //我的产品报价详情
    public void getMeDetail_B_Single(final String inquiry_sn, final IActionMePrice<Detail_B_SingleModel> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    iActionMePrice.start("");
                }
            });
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "Offer");
            map.put("m", "MyProductOfferDetail");
            map.put("inquiry_sn", inquiry_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    Log.i("data", jsonObject.toString());
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_B_SingleModel model  = JsonUtils.parseArray(json, Detail_B_SingleModel.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }

    //我的项目报价详情
    public void getMeDetail_B_Project(final String inquiry_sn, final IActionMePrice<Detail_B_SingleModel> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    iActionMePrice.start("");
                }
            });
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "Offer");
            map.put("m", "MyProjectOfferDetail");
            map.put("inquiry_sn", inquiry_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_B_SingleModel model  = JsonUtils.parseArray(json, Detail_B_SingleModel.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }

    //我的产品竞价详情
    //我的项目竞价详情
    public void getMeDetail_J(final String inquiry_sn, final String method, final IActionMePrice<Detail_J_Model> iActionMePrice) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionMePrice.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "Bidding");
            map.put("m", method);
            map.put("inquiry_sn", inquiry_sn);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final Detail_J_Model model  = JsonUtils.parseArray(json, Detail_J_Model.class);
                            mHandler.post(() -> iActionMePrice.success(model));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionMePrice.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionMePrice.fail(volleyError));
                }
            });
        });
    }

}
