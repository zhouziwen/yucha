package com.example.hnTea.mvpmodel.user;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.user.bean.HelperModel;
import com.example.hnTea.mvpmodel.user.bean.MyCollectModel;
import com.example.hnTea.mvpmodel.user.bean.MyInfo;
import com.example.hnTea.mvpmodel.user.bean.MyOrderModel;
import com.example.hnTea.mvpmodel.user.bean.UserAddress;
import com.example.hnTea.mvpmodel.user.bean.VersionModel;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/12/23.
 */
public class UserConfigData extends BaseModel {

    //************************************我的信息*************************************
    public void getUserInfo(final IActionUser<MyInfo> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "myinfo");
                if (!TextUtils.isEmpty(MyApplication.getUserToken())) {
                    map.put("token", MyApplication.getUserToken());
                }
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("userinfo");
                                final MyInfo model = JsonUtils.parseArray(json, MyInfo.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionUser.success(model);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //************************************修改昵称*************************************
    public void getChangeUserNickName(final String changeUserName,
                                      final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "changeName");
                map.put("username", changeUserName);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //*************************************修改手机号***********************************
    public void getChangeUserPhoneNumber(final String old_num,
                                         final String old_psw,
                                         final String new_num,
                                         final String sms,
                                         final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "changePhone");
                map.put("old_userphone", old_num);
                map.put("password", old_psw);
                map.put("new_userphone", new_num);
                map.put("messagecode", sms);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //**************************************修改密码************************************
    public void getChangeUserPsw(final String old_psw,
                                 final String new_psw,
                                 final String result_psw,
                                 final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "changePassword");
                map.put("old_pwd", old_psw);
                map.put("new_pwd", new_psw);
                map.put("re_pwd", result_psw);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //****************************************收货地址相关*******************************

    //添加收货地址
    public void getAddAddress(final String receiver,
                              final String telephone,
                              final String address,
                              final String postcode,
                              final String defaultCode,
                              final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "user");
                map.put("m", "addAddress");
                map.put("receiver", receiver);
                map.put("telephone", telephone);
                map.put("address", address);
                map.put("postcode", postcode);
                map.put("default", defaultCode);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //获取收货地址列表
    public void getAddressList(final IActionUser<List<UserAddress>> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "getAddress");
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("address");
                                final List<UserAddress> list = JsonUtils.parseArray(json,
                                        new TypeToken<List<UserAddress>>() {
                                        });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionUser.success(list);
                                    }
                                });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //编辑收货地址
    public void getEditAddress(final String id,
                               final String receiver,
                               final String telephone,
                               final String address,
                               final String postcode,
                               final String defaultCode,
                               final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "editAddress");
                map.put("id", id);
                map.put("receiver", receiver);
                map.put("telephone", telephone);
                map.put("address", address);
                map.put("postcode", postcode);
                map.put("default", defaultCode);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //设置默认的收货地址
    public void getSetDefaultAddress(final String id,
                                     final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "setDefault");
                map.put("id", id);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //删除收货地址
    public void getDeleteAddress(final String id,
                                 final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                final TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "delAddress");
                map.put("id", id);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //*************************************意见反馈*************************************************
    public void getFeedBack(final String content,
                            final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "common");
                map.put("m", "suggest");
                map.put("content", content);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.success(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //**************************************判断token是否过期****************************************
    public void checkToken(final IActionUser<String> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                iActionUser.start("");
            }
        });
        TreeMap<String, String> map = new TreeMap<>();
        map.put("c", "common");
        map.put("m", "checkToken");
        map.put("token", MyApplication.getUserToken());
        Volley_StringRequest.toRequest(map, new VolleyBack() {
            @Override
            public void onRequestSuccess(final JSONObject jsonObject) {
                try {
                    if (jsonObject.getInt("code") == 1) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    iActionUser.success(jsonObject.getString("message"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    iActionUser.phpFail(jsonObject.getString("message"));
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
                        iActionUser.fail(volleyError);
                    }
                });
            }
        });

    }


    //*************************************版本更新**************************************************
    public void checkVersion(final String version, final IActionUser<VersionModel> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "common");
                map.put("m", "updateVersion");
                map.put("version", version);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                //发现新版本
                                String json = jsonObject.getString("data");
                                final VersionModel versionModel
                                        = JsonUtils.parseArray(json, VersionModel.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionUser.success(versionModel);
                                    }
                                });
                            } else {
                                //该版本是最新的
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //*************************************我的收藏**************************************************
    public void getMyCollect(final String page, final IActionUser<MyCollectModel> iActionUser) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionUser.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "User");
                map.put("m", "lookMyCollectGoods");
                map.put("token", MyApplication.getUserToken());
                map.put("page", page);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                final MyCollectModel collectModel = JsonUtils.parseArray(json, MyCollectModel.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionUser.success(collectModel);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionUser.phpFail(jsonObject.getString("message"));
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
                                iActionUser.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //*************************************我的订单**************************************************
    public void getMyOrder(String type, String page, String limit, IActionUser<List<MyOrderModel>> iActionUser) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionUser.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "user");
            map.put("m", "myOrder");
            map.put("token", MyApplication.getUserToken());
            map.put("type", type);
            map.put("page", page);
            map.put("limit", limit);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            List<MyOrderModel> myOrderModels = JsonUtils.parseArray(json, new TypeToken<List<MyOrderModel>>() {
                            });
                            mHandler.post(() -> iActionUser.success(myOrderModels));
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
    //*************************************帮助中心**************************************************
    public void getHelperMsg(IActionUser<List<HelperModel>> iActionUser){
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> {iActionUser.start("");});
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c","common");
            map.put("m","about");
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") ==1){
                            String json =jsonObject.getString("data");
                            List<HelperModel> list =JsonUtils.parseArray(json,new TypeToken<List<HelperModel>>(){});
                            mHandler.post(() -> {
                                iActionUser.success(list);
                            });
                        }else {
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
                    mHandler.post(() -> {
                        iActionUser.fail(volleyError);
                    });
                }
            });
        });
    }

}
