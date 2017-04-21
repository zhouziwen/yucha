package com.example.hnTea.ui.me.user.helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.mvpmodel.user.bean.UserhelperModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserHelperPresenter;
import com.example.hnTea.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment {
    private UserHelperPresenter mUserHelperPresenter;
    private WebView mWebView;
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserHelperPresenter=new UserHelperPresenter(null);
        mAppTitleBar.getTitle().setText("");
        mWebView = mFindViewUtils.findViewById(R.id.common_question_webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
//        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
        webSettings.setBuiltInZoomControls(false);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mUserHelperPresenter.getUserHelperData("help", new IViewUser<UserhelperModel>() {
            @Override
            public void onSuccess(UserhelperModel response) {
                mAppTitleBar.getTitle().setText(response.getTitle());
                mWebView.loadDataWithBaseURL(BaseUrl.getBaseUrl(), response.getContent(), "text/html", "utf-8", null);
                hiddenLoading();
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                showAlertWithMsg(var);
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
                showAlertWithMsg("请检查网络");
            }
        });
    }
}
