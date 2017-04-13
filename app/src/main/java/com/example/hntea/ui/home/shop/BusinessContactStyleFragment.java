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
import android.widget.Toast;

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
public class BusinessContactStyleFragment extends BaseFragment {

    @BindView(R.id.contact_style_web)
    WebView mContactStyleWeb;
    private ShopOrderPresenter mShopOrderPresenter;
    private String supplier_id;
    public BusinessContactStyleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_contact_style, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mShopOrderPresenter=new ShopOrderPresenter(null);
        WebSettings webSettings = mContactStyleWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        mContactStyleWeb.setWebViewClient(new WebViewClient());
        mContactStyleWeb.setWebChromeClient(new WebChromeClient());
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
                mContactStyleWeb.loadDataWithBaseURL(null, data.getContact_desc(), "text/html", "utf-8", null);
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
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                hiddenLoading();
            }
        });
    }
    public void setSupplier_id(String var) {
        supplier_id = var;
    }
}
