package com.example.hnTea.mvpmodel.home;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.https.JsonUtils;
import com.example.hnTea.https.VolleyBack;
import com.example.hnTea.https.Volley_StringRequest;
import com.example.hnTea.mvpmodel.BaseModel;
import com.example.hnTea.mvpmodel.home.bean.CategoryGoodsList;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Banner;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Base;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Category;
import com.example.hnTea.mvpmodel.home.bean.MainShop_HotShop;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Nav;
import com.example.hnTea.mvpmodel.home.bean.MainShop_ShangJia;
import com.example.hnTea.mvpmodel.home.bean.ProduceSearchModel;
import com.example.hnTea.mvpmodel.home.bean.ShopCarListModel;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Base;
import com.example.hnTea.utils.WorkFactory;
import com.google.gson.reflect.TypeToken;
import com.umeng.socialize.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by 太能 on 2016/11/16.
 */
public class HomeData extends BaseModel {

    private MainShop_Base mainShop_base;

    //*****************************************首页 商城收据***********************************
    public void GetMainHomeData(final IActionHome<MainShop_Base> iActionHome) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mainShop_base = new MainShop_Base(null, null, null, null, null);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionHome.start("");
                    }
                });
                //轮播图
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "Shop");
                map.put("m", "banner");
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                List<MainShop_Banner> banners = JsonUtils.parseArray(json,
                                        new TypeToken<List<MainShop_Banner>>() {
                                        });
                                mainShop_base.setBanners(banners);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(VolleyError volleyError) {

                    }
                });
                //首页分类 快速导航
                TreeMap<String, String> map1 = new TreeMap<>();
                map1.put("c", "Shop");
                map1.put("m", "category");
                Volley_StringRequest.toRequest(map1, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                List<MainShop_Nav> navs = JsonUtils.parseArray(json,
                                        new TypeToken<List<MainShop_Nav>>() {
                                        });
                                mainShop_base.setNav(navs);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(VolleyError volleyError) {

                    }
                });
                //热门商品模块
                TreeMap<String, String> map2 = new TreeMap<>();
                map2.put("c", "Shop");
                map2.put("m", "hotGoods");
                Volley_StringRequest.toRequest(map2, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                List<MainShop_HotShop> hots = JsonUtils.parseArray(json,
                                        new TypeToken<List<MainShop_HotShop>>() {
                                        });
                                mainShop_base.setHotShops(hots);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(VolleyError volleyError) {

                    }
                });
                //入住商家
                TreeMap<String, String> map3 = new TreeMap<>();
                map3.put("c", "Shop");
                map3.put("m", "getSupplier");
                Volley_StringRequest.toRequest(map3, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                List<MainShop_ShangJia> shangjia = JsonUtils.parseArray(json,
                                        new TypeToken<List<MainShop_ShangJia>>() {
                                        });
                                mainShop_base.setShnagJia(shangjia);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(VolleyError volleyError) {

                    }
                });
                //分类楼层
                TreeMap<String, String> map4 = new TreeMap<>();
                map4.put("m", "categoryList");
                map4.put("c", "shop");
                Volley_StringRequest.toRequest(map4, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                List<MainShop_Category> category =
                                        JsonUtils.parseArray(json, new TypeToken<List<MainShop_Category>>() {
                                        });
                                mainShop_base.setCategory(category);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.success(mainShop_base);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onVolleyError(VolleyError volleyError) {

                    }
                });
            }
        });
    }

    //**************************************商品 详情 的数据***********************************
    public void getShopDetailData(final String goodsId,
                                  final String token,
                                  final IActionHome<ShopDetail_Base> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap map = new TreeMap();
            map.put("c", "Goods");
            map.put("m", "getGoodsInfo");
            map.put("goodsId", goodsId);
            map.put("token", token);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            //解析数据
                            String json = jsonObject.getString("data");
                            final ShopDetail_Base shopDetail_base =
                                    JsonUtils.parseArray(json, ShopDetail_Base.class);
                            mHandler.post(() -> iActionHome.success(shopDetail_base));
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
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            iActionHome.fail(volleyError);
                        }
                    });
                }
            });
        });
    }

    //**************************************类型商品 列表 的数据***********************************
    public void getCategoryGoodsList(final String sonCategoryId, final String styleId,
                                     final String categoryId, final String brandId,
                                     final String page, final IActionHome<CategoryGoodsList> iActionHome) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionHome.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "Goods");
                map.put("m", "goodsList");
                map.put("scId", sonCategoryId);
                map.put("sId", styleId);
                map.put("cId", categoryId);
                map.put("page", page);
                map.put("bId", brandId);
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {

                        try {
                            if (jsonObject.getInt("code") == 1) {
                                //解析数据
                                String json = jsonObject.getString("data");
                                final CategoryGoodsList categoryGoodsList = JsonUtils.parseArray(json, CategoryGoodsList.class);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.success(categoryGoodsList);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionHome.phpFail(jsonObject.getString("message"));
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
                                iActionHome.fail(volleyError);
                            }
                        });
                    }
                });
            }

        });
    }


    //**************************************用户收藏商品*******************************************
    public void UserCollection(final String goodId,
                               final String token,
                               final IActionHome<String> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap map = new TreeMap();
            map.put("c", "Goods");
            map.put("m", "GoodsCollection");
            map.put("goodsId", goodId);
            map.put("token", token);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        iActionHome.success(jsonObject.getString("data"));
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
                                        iActionHome.phpFail(jsonObject.getString("message"));
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
                            iActionHome.fail(volleyError);
                        }
                    });
                }
            });
        });
    }

    //**************************************购物车商品拉取*******************************************
    public void getShopCarData(final IActionHome<List<ShopCarListModel>> iActionHome) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionHome.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<String, String>();
                map.put("c", "Cart");
                map.put("m", "cartList");
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                String json = jsonObject.getString("data");
                                final List<ShopCarListModel> shopCarListModels = JsonUtils.parseArray(json,
                                        new TypeToken<List<ShopCarListModel>>() {
                                        });
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.success(shopCarListModels);
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            iActionHome.phpFail(jsonObject.getString("message"));
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
                                iActionHome.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //**************************************加入购物车*******************************************
    public void getAddShopCar(final String product_id,
                              final String product_number,
                              final IActionHome<String> iActionHome) {
        WorkFactory.instance.service().submit(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iActionHome.start("");
                    }
                });
                TreeMap<String, String> map = new TreeMap<>();
                map.put("c", "Cart");
                map.put("m", "add");
                map.put("product_id", product_id);
                map.put("product_number", product_number);
                map.put("token", MyApplication.getUserToken());
                Volley_StringRequest.toRequest(map, new VolleyBack() {
                    @Override
                    public void onRequestSuccess(final JSONObject jsonObject) {
                        try {
                            if (jsonObject.getInt("code") == 1) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.success("添加成功");
                                    }
                                });
                            } else if (jsonObject.getInt("code") == 2) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.phpFail("商品库存不足");
                                    }
                                });
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        iActionHome.phpFail("您必须选择一件商品，且商品数量不能为0");
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
                                iActionHome.fail(volleyError);
                            }
                        });
                    }
                });
            }
        });
    }

    //************************************** 商品搜索 *******************************************
    public void getProductSearch(final String keyWord,
                                 final String styleId,
                                 final String page,
                                 final IActionHome<ProduceSearchModel> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<>();
            map.put("c", "Goods");
            map.put("m", "search");
            map.put("k", keyWord);
            map.put("sId", styleId);
            map.put("page", page);
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(final JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("data");
                            final ProduceSearchModel produceSearchModel = JsonUtils.parseArray(json, ProduceSearchModel.class);
                            mHandler.post(() -> iActionHome.success(produceSearchModel));
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

    //************************************** 商品删除 *******************************************
    public void deleteShopCarGoods(String product_id, IActionHome<String> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Cart");
            map.put("m", "delete");
            map.put("product_id", product_id);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("message");
                            mHandler.post(() -> iActionHome.success(json));
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

    //************************************** 修改数量 *******************************************
    public void modifyGoodsNum(String product_id, String num, IActionHome<String> iActionHome) {
        WorkFactory.instance.service().submit(() -> {
            mHandler.post(() -> iActionHome.start(""));
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("c", "Cart");
            map.put("m", "update");
            map.put("product_id", product_id);
            map.put("num", num);
            map.put("token", MyApplication.getUserToken());
            Volley_StringRequest.toRequest(map, new VolleyBack() {
                @Override
                public void onRequestSuccess(JSONObject jsonObject) {
                    try {
                        if (jsonObject.getInt("code") == 1) {
                            String json = jsonObject.getString("message");
                            mHandler.post(() -> iActionHome.success(json));
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



