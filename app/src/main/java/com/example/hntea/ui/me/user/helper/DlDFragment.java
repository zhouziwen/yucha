package com.example.hnTea.ui.me.user.helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.example.hnTea.R;
import com.example.hnTea.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DlDFragment extends BaseFragment {
    private WebView mWebView;
    private RelativeLayout mRelativeLayout;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dl_d, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mWebView =mFindViewUtils.findViewById(R.id.helper_webView);
        mRelativeLayout =mFindViewUtils.findViewById(R.id.helper_layout);
        String html =getArguments().getString("helper_html");
        String title =getArguments().getString("helper_title");
        mAppTitleBar.getTitle().setText(title);
        if (!title.equals("关于我们")){
            mRelativeLayout.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
    }
}
