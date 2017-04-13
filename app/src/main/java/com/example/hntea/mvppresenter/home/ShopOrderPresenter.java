package com.example.hnTea.mvppresenter.home;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.home.IActionHome;
import com.example.hnTea.mvpmodel.home.ShopOrderData;
import com.example.hnTea.mvpmodel.home.bean.CheckOrderModel;
import com.example.hnTea.mvpmodel.home.bean.CreateOrderModel;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowModel;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowPagesModel;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class ShopOrderPresenter extends BasePresenter {
    private ShopOrderData mShopOrderData;

    public ShopOrderPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mShopOrderData = new ShopOrderData();
    }

    public void getCheckOrderData(final String rec_id,String num, final IViewHome<CheckOrderModel> iViewHome) {
        mShopOrderData.getCheckOrderData(rec_id,num, new IActionHome<CheckOrderModel>() {
            @Override
            public void success(CheckOrderModel data) {
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

    public void getCreateOrderData(final String rec_id,String num, final String address_id, final String pay_id, final IViewHome<CreateOrderModel> iViewHome) {
        mShopOrderData.getCreateOrderData(rec_id,num, address_id, pay_id, new IActionHome<CreateOrderModel>() {
            @Override
            public void success(CreateOrderModel data) {
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

    public void getShopYellow(String supplier_id, String sId, String page, IViewHome<ShopYellowModel> iViewHome) {
        mShopOrderData.getShopYellow(supplier_id, sId, page, new IActionHome<ShopYellowModel>() {
            @Override
            public void success(ShopYellowModel data) {
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

    public void getCompanyYellowPage(String supplier_id, IViewHome<ShopYellowPagesModel> iViewHome) {
        mShopOrderData.getCompanyYellowPage(supplier_id, new IActionHome<ShopYellowPagesModel>() {
            @Override
            public void success(ShopYellowPagesModel data) {
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
