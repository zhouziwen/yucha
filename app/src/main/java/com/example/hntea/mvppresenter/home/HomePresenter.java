package com.example.hnTea.mvppresenter.home;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.home.HomeData;
import com.example.hnTea.mvpmodel.home.IActionHome;
import com.example.hnTea.mvpmodel.home.bean.CategoryGoodsList;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Base;
import com.example.hnTea.mvpmodel.home.bean.ProduceSearchModel;
import com.example.hnTea.mvpmodel.home.bean.ShopCarListModel;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Base;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

import java.util.List;

/**
 * Created by 太能 on 2016/11/16.
 */
public class HomePresenter extends BasePresenter {
    private HomeData mHomeData;

    public HomePresenter(IViewBase iViewBase) {
        super(iViewBase);
        mHomeData = new HomeData();
    }


    public void getShopDetailData(final String goodsId,
                                  final String token,
                                  final IViewHome<ShopDetail_Base> IViewHome) {
        mHomeData.getShopDetailData(goodsId, token, new IActionHome<ShopDetail_Base>() {
            @Override
            public void success(ShopDetail_Base data) {
                IViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewHome.onStart(var);
            }
        });
    }

    public void getCategoryGoodsList(final String sonCategoryId, final String styleId,
                                     final String categoryId, final String brandId,
                                     final String page, final IViewHome<CategoryGoodsList> iViewHome) {
        mHomeData.getCategoryGoodsList(sonCategoryId, styleId, categoryId, brandId, page, new IActionHome<CategoryGoodsList>() {
            @Override
            public void success(CategoryGoodsList data) {
                iViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewHome.onStart(var);
            }
        });
    }


    public void UserCollection(final String goodId,
                               final String token,
                               final IViewHome<String> IViewHome) {
        mHomeData.UserCollection(goodId, token, new IActionHome<String>() {
            @Override
            public void success(String data) {
                IViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewHome.onStart(var);
            }
        });
    }

    public void GetMainHomeData(final IViewHome<MainShop_Base> iActionHome) {
        mHomeData.GetMainHomeData(new IActionHome<MainShop_Base>() {
            @Override
            public void success(MainShop_Base data) {
                iActionHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iActionHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iActionHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iActionHome.onStart(var);
            }
        });
    }

    public void getShopCarData(final IViewHome<List<ShopCarListModel>> iViewHome) {
        mHomeData.getShopCarData(new IActionHome<List<ShopCarListModel>>() {
            @Override
            public void success(List<ShopCarListModel> data) {
                iViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewHome.onStart(var);
            }
        });
    }

    public void getAddShopCar(final String product_id, final String product_number, final IViewHome<String> iViewHome) {
        mHomeData.getAddShopCar(product_id, product_number, new IActionHome<String>() {
            @Override
            public void success(String data) {
                iViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewHome.onStart(var);
            }
        });
    }

    public void getProductSearch(final String keyWord, final String styleId, final String page, final IViewHome<ProduceSearchModel> IViewHome) {
        mHomeData.getProductSearch(keyWord, styleId, page, new IActionHome<ProduceSearchModel>() {
            @Override
            public void success(ProduceSearchModel data) {
                IViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewHome.onStart(var);
            }
        });
    }

    //************************************** 商品删除 *******************************************
    public void deleteShopCarGoods(String product_id, IViewHome<String> iViewHome) {
        mHomeData.deleteShopCarGoods(product_id, new IActionHome<String>() {
            @Override
            public void success(String data) {
                iViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewHome.onStart(var);
            }
        });
    }
    //************************************** 修改数量 *******************************************
    public void modifyGoodsNum(String product_id, String num, IViewHome<String> iViewHome) {
        mHomeData.modifyGoodsNum(product_id, num, new IActionHome<String>() {
            @Override
            public void success(String data) {
                iViewHome.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewHome.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewHome.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewHome.onStart(var);
            }
        });
    }
}

