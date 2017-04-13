package com.example.hnTea.mvpmodel.login;

import android.util.Log;

import com.android.volley.VolleyError;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.login.bean.UserInfo;
import com.example.hnTea.utils.WorkFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

/**
 * Created by 太能 on 2016/12/21.
 */
public class LoginData extends BaseModel {

    //****************************************获取手机验证码***********************************
    public void getSmsCode(final IActionLogin<String> iActionLogin, final String phoneNum, final int isLoginOrForget) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionLogin.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "common");
                map.put("m", "sendCode");
                if (isLoginOrForget == 1) {
                    map.put("type", "reg");
                } else {
                    map.put("type","find");
                }

                map.put("userphone", phoneNum);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionLogin.phpFail(jsonObject.getString("message"));
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
                                            iActionLogin.smsCode(jsonObject.getInt("messagecode"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionLogin.success("" + jsonObject);
                            }
                        });

                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionLogin.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //****************************************注册接口***********************************
    public void register(final String phoneNum,
                         final String psw,
                         final String resultPsw,
                         final String sms,
                         final IActionLogin<String> iActionLogin) {

        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionLogin.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "register");
                map.put("userphone", phoneNum);
                map.put("password", psw);
                map.put("repassword", resultPsw);
                map.put("messagecode", sms);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionLogin.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionLogin.success("" + jsonObject);
                            }
                        });
                    }

                    @Override
                    public void onVolleyError(final VolleyError volleyError) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iActionLogin.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //*****************************************登录接口*****************************************
    public void login(final String userName,
                      final String psw,
                      final IActionLogin<UserInfo> iActionLogin) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionLogin.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "login");
                map.put("userphone", userName);
                map.put("password", psw);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    iActionLogin.phpFail(jsonObject.getString("message"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                });
                            } else {
                                String json = jsonObject.getString("userinfo");
                                final UserInfo userInfo = JsonUtils.parseArray(json, UserInfo.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionLogin.success(userInfo);
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
                                iActionLogin.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }


    //****************************************找回密码*******************************************
    public void forget(final String phoneNum,
                       final String psw,
                       final String resultPsw,
                       final String sms,
                       final IActionLogin<String> iActionLogin) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionLogin.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "user");
                map.put("m", "resetPassword");
                map.put("userphone", phoneNum);
                map.put("password", psw);
                map.put("repassword", resultPsw);
                map.put("messagecode", sms);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        Log.i("sms",""+jsonObject);
                        try {
                            if (jsonObject.getInt("code") == 0) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionLogin.phpFail(jsonObject.getString("message"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionLogin.success("");
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
                                iActionLogin.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

}


