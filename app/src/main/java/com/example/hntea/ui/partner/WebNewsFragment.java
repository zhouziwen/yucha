package com.example.hnTea.ui.partner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsDetailModel;
import com.example.hnTea.mvppresenter.partner.IViewPartner;
import com.example.hnTea.mvppresenter.partner.PartnerPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.UMShareUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebNewsFragment extends BaseFragment {
    private PartnerPresenter mPartnerPresenter;
    private ListView mListView;
    private View mHeaderView;
    private View mFooterView;
    private WebView mWebView;
    private TextView mTitle, mTime, mAuthor;
    private PartnerNewsDetailModel mData;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_news, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Bundle bundle = getArguments();
        id = bundle.getString("detail_id");
        mPartnerPresenter = new PartnerPresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.news_list);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.news_header, null);
        mTime = (TextView) mHeaderView.findViewById(R.id.news_time);
        mTitle = (TextView) mHeaderView.findViewById(R.id.news_title);
        mAuthor = (TextView) mHeaderView.findViewById(R.id.news_author);
        mListView.addHeaderView(mHeaderView);
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.news_footer_web, null);
        mWebView = (WebView) mFooterView.findViewById(R.id.news_web);
        mListView.addFooterView(mFooterView);

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
        setTvDrawable(mAppTitleBar.getAction(), R.mipmap.me_user_fenxiang);
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享按钮
                if (mData != null) {
                    if (TextUtils.isEmpty(mData.getShare_img())) {
                        //没有图片的头像
                        UMShareUtils shareUtils =
                                new UMShareUtils(getActivity(),
                                        getContext(),
                                        R.mipmap.ycicon,
                                        mData.getShare_url(),
                                        mData.getTitle(),
                                        mData.getShare_content());
                        shareUtils.openShareDialog();
                    } else {
                        UMShareUtils shareUtils = new UMShareUtils(getActivity(),
                                getContext(),
                                mData.getShare_img(),
                                mData.getShare_url() == null ? "" : mData.getShare_url(),
                                mData.getTitle() == null ? "" : mData.getTitle(),
                                mData.getShare_content());
                        shareUtils.openShareDialog();
                    }
                }
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        requestDetail();
        CommonAdapter<String> adapter =
                new CommonAdapter<String>(getContext(), null, R.layout.main_tabbar_layout) {
                    @Override
                    protected void setListeners(BaseViewHolder holder, View view, int position) {
                    }

                    @Override
                    protected void setViewDimen(View convertView) {
                    }

                    @Override
                    protected void setViewData(int position, BaseViewHolder holder, String item) {
                    }

                    @Override
                    public void onClickBack(int position, View view, BaseViewHolder holder) {
                    }
                };
        mListView.setAdapter(adapter);
        mAppTitleBar.getTitle().setText("资讯详情");
    }

    private void requestDetail() {
        mPartnerPresenter.getPartnerNewsDetail(id, new IViewPartner<PartnerNewsDetailModel>() {
            @Override
            public void onSuccess(PartnerNewsDetailModel data) {
                hiddenLoading();
                mData = data;
                mAuthor.setText(data.getAuthor());
                mTime.setText(data.getTime());
                mTitle.setText(data.getTitle());
                mWebView.loadDataWithBaseURL(null, data.getContent(), "text/html", "utf-8", null);
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
