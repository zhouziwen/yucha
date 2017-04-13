package com.example.hnTea.ui.me.MyInquiry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.me.bean.Me_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_X_SingleModel;
import com.example.hnTea.mvppresenter.me.IViewMePrice;
import com.example.hnTea.mvppresenter.me.MePricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeXPriceFragment extends BaseFragment {
    private PullToRefreshListView mListView_Single, mListView_Project;
    private MePricePresenter mMePricePresenter;
    private MePriceDetailFragment mMePriceDetailFragment;
    private CommonAdapter<Me_X_SingleModel> mSingleAdapter;
    private CommonAdapter<Me_X_ProjectModel> mProjectAdapter;
    private List<Me_X_SingleModel> mList_Single = new ArrayList<>();
    private List<Me_X_ProjectModel> mList_Project = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_xprice, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMePricePresenter = new MePricePresenter(null);
        mListView_Single = mFindViewUtils.findViewById(R.id.me_XPrice_listView_single);
        mListView_Project = mFindViewUtils.findViewById(R.id.me_XPrice_listView_project);
        mListView_Single.setEmptyView(mFindViewUtils.findViewById(R.mipmap.default_no_infomation));
        mListView_Project.setEmptyView(mFindViewUtils.findViewById(R.mipmap.default_no_infomation));
        isSingleOrProject(getArguments().getInt("data"));
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        setSingleListBody();
        setProjectListBody();
        setSingleListData(1);
        setProjectListData(1);
    }

    private void isSingleOrProject(int type) {
        switch (type) {
            case 0:
                mListView_Single.setVisibility(View.VISIBLE);
                mListView_Project.setVisibility(View.GONE);
                break;
            case 1:
                mListView_Single.setVisibility(View.GONE);
                mListView_Project.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setSingleListBody() {
        //产品询价单列表
        mSingleAdapter = new CommonAdapter<Me_X_SingleModel>(getContext(),
                null,
                R.layout.x_price_single_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, Me_X_SingleModel item) {
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProduct_name())
                        .setText(R.id.XPrice_listItem_time, item.getTime())
                        .setText(R.id.XPrice_listItem_location, item.getBrand())
                        .setText(R.id.XPrice_listItem_type, item.getQuantity() + item.getUnit());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                pushDetail(mSingleAdapter.getItem(position).getInquiry_sn());
            }
        };
        mListView_Single.setAdapter(mSingleAdapter);
        setEmptyView(mListView_Single, R.mipmap.default_no_infomation);
    }

    private void setProjectListBody() {
        //项目询价单列表
        mProjectAdapter = new CommonAdapter<Me_X_ProjectModel>(getContext(),
                null,
                R.layout.xprice_list_item
        ) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, Me_X_ProjectModel item) {
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProject_name())
                        .setText(R.id.XPrice_listItem_time, item.getTime().isEmpty()?"无":item.getTime())
                        .setText(R.id.XPrice_listItem_location, item.getProvince().isEmpty()?"无":item.getProvince())
                        .setText(R.id.XPrice_listItem_type, item.getProject_type().isEmpty()?"无":item.getProject_type())
                        .setText(R.id.XPrice_listItem_type1, item.getStage().isEmpty()?"无":item.getProject_type());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                pushDetail(mProjectAdapter.getItem(position).getInquiry_sn());
            }
        };
        mListView_Project.setAdapter(mProjectAdapter);
        setEmptyView(mListView_Project, R.mipmap.default_no_infomation);
    }

    private void setSingleListData(int page) {
        mMePricePresenter.getMe_X_SingleListData(page, new IViewMePrice<List<Me_X_SingleModel>>() {
            @Override
            public void onSuccess(List<Me_X_SingleModel> data) {
                hiddenLoading();
                mSingleAdapter.update(data);
                mList_Single.addAll(data);
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

    private void setProjectListData(int page) {
        mMePricePresenter.getMe_X_ProjectListData(page, new IViewMePrice<List<Me_X_ProjectModel>>() {
            @Override
            public void onSuccess(List<Me_X_ProjectModel> data) {
                hiddenLoading();
                mProjectAdapter.update(data);
                mList_Project.addAll(data);
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

    private void pushDetail(String var) {

        mMePriceDetailFragment = new MePriceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("inquiry_sn", var);
        bundle.putInt("mePriceType", 1);
        ShowFragmentUtils.showFragment(getActivity(),
                mMePriceDetailFragment.getClass(),
                "me_price_detail",
                bundle,
                true);
    }
}
