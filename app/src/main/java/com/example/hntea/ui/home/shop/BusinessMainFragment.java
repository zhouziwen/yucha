package com.example.hnTea.ui.home.shop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowPagesModel;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.mvppresenter.home.ShopOrderPresenter;
import com.example.hnTea.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessMainFragment extends BaseFragment {
    @BindView(R.id.business_home_web)
    WebView mBusinessHomeWeb;
    private ShopOrderPresenter mShopOrderPresenter;
    private String supplier_id = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mShopOrderPresenter = new ShopOrderPresenter(null);
        WebSettings webSettings = mBusinessHomeWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        mBusinessHomeWeb.setWebViewClient(new WebViewClient());
        mBusinessHomeWeb.setWebChromeClient(new WebChromeClient());
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mShopOrderPresenter.getCompanyYellowPage(supplier_id, new IViewHome<ShopYellowPagesModel>() {
            @Override
            public void onSuccess(ShopYellowPagesModel data) {
                mBusinessHomeWeb.loadDataWithBaseURL(null, data.getIntroduce_desc(), "text/html", "utf-8", null);
                hiddenLoading();
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
            }
        });
    }
    public void setSupplier_id(String var){
        supplier_id =var;
    }
}
