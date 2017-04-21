package com.example.hnTea.ui.me.user.helper;


import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HnTeaFragment extends BaseFragment {
    @BindView(R.id.version_code)
    TextView mVersionCode;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hntea, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        String html = getArguments().getString("helper_html");
        mAppTitleBar.getTitle().setText("关于我们");
//        if (!title.equals("关于我们")) {
//            mRelativeLayout.setVisibility(View.GONE);
//            mWebView.setVisibility(View.VISIBLE);
//            mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
//        }
        mVersionCode.setText(getAppInfo());
    }
    private String getAppInfo() {
        try {
            PackageInfo pkg = getActivity().getPackageManager().getPackageInfo(MyApplication.getContext().getPackageName(), 0);

            String appName = pkg.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
            String versionName = pkg.versionName;
            return appName + "V" + versionName;
        } catch (Exception e) {
        }
        return null;
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
