package com.example.hnTea.mvpmodel.home;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.home.bean.CheckOrderModel;
import com.example.hnTea.mvpmodel.home.bean.CheckOrderModel_Address;
import com.example.hnTea.mvpmodel.home.bean.CreateOrderModel;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowModel;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowPagesModel;
import com.example.hnTea.utils.WorkFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */
//order 订单  ’companyYellowPages’ 商家黄页
public class ShopOrderData extends BaseModel {

    //*******************************************确认订单********************************************
    public void getCheckOrderData(final String rec_id,String num,final IActionHome<CheckOrderModel> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            if (!num.equals("0")){
                //商品
                map.put("m", "checkGoods");
                map.put("num",num);
            }else {
                //购物车
                map.put("m", "checkOrder");
            }
            map.put("c", "order");
            map.put("token", MyApplication.getUserToken());
            map.put("product_id", rec_id);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            JSONObject obj =jsonObject.getJSONObject("data");
                            String addressStr =obj.getString("address");
                            String check =obj.getString("check_address");
                            //0是没有收货地址  1是有
                            if (check.equals("0")){
                                CheckOrderModel checkOrderModel1 =JsonUtils.parseArray(json,CheckOrderModel.class);
                                mHandler.post(() -> iActionHome.success(checkOrderModel1));
                            }else {
                                CheckOrderModel checkOrderModel2 =JsonUtils.parseArray(json,CheckOrderModel.class);
                                CheckOrderModel_Address address =JsonUtils.parseArray(addressStr,CheckOrderModel_Address.class);
                                checkOrderModel2.setAddress(address);
                                mHandler.post(() -> iActionHome.success(checkOrderModel2));
                            }
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionHome.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionHome.fail(volleyError));
                }
            });
        });
    }

    //******************************************提交订单********************************************
    public void getCreateOrderData(final String rec_id,
                                   final String num,
                                   final String address_id,
                                   final String pay_id,
                                   final IActionHome<CreateOrderModel> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "order");
            map.put("m", "createOrder");
            map.put("num",num);
            map.put("product_id",rec_id);
            map.put("token", MyApplication.getUserToken());
            map.put("address_id", address_id);
            map.put("pay_id", pay_id);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final CreateOrderModel createOrderModel = JsonUtils.parseArray(json, CreateOrderModel.class);
                            mHandler.post(() -> iActionHome.success(createOrderModel));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionHome.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionHome.fail(volleyError));
                }
            });
        });
    }

    public void getShopYellow(String supplier_id, String sId, String page, IActionHome<ShopYellowModel> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Shop");
            map.put("m", "yellow");
            map.put("supplier_id", supplier_id);
            map.put("sId", sId);
            map.put("page", page);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            ShopYellowModel shopYellowModel = JsonUtils.parseArray(json, ShopYellowModel.class);
                            mHandler.post(() -> iActionHome.success(shopYellowModel));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionHome.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionHome.fail(volleyError));
                }
            });
        });

    }

    public void getCompanyYellowPage(String supplier_id, IActionHome<ShopYellowPagesModel> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Partner");
            map.put("m", "companyYellowPages");
            map.put("supplier_id", supplier_id);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final ShopYellowPagesModel shopYellowPagesModel = JsonUtils.parseArray(json, ShopYellowPagesModel.class);
                            mHandler.post(() -> iActionHome.success(shopYellowPagesModel));
                        } else {
                            mHandler.post(() -> {
                                try {
                                    iActionHome.phpFail(jsonObject.getString("message"));
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
                    mHandler.post(() -> iActionHome.fail(volleyError));
                }
            });
        });

    }
}
