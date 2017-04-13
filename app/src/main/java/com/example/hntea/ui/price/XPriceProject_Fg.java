package com.example.hnTea.ui.price;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.mvpmodel.price.bean.X_ProjectModel;
import com.example.hnTea.mvppresenter.price.IViewPrice;
import com.example.hnTea.mvppresenter.price.PricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XPriceProject_Fg extends BaseFragment {

    private PullToRefreshListView mListView;
    private PricePresenter mPricePresenter;
    private CommonAdapter<X_ProjectModel> mAdapter;
    private PriceDetailsFragment mPriceDetailsFragment;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xprice_project__fg, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPricePresenter = new PricePresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.XPrice_listView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //上拉刷新
                setListViewData(1, 1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //下拉加载更多
                page++;
                setListViewData(page, 2);
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        setListViewBody();
        setListViewData(1, 1);
    }

    private void setListViewBody() {
        mAdapter = new CommonAdapter<X_ProjectModel>(getContext(), null, R.layout.xprice_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnclickListener(R.id.XPrice_Layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, X_ProjectModel item) {
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProject_name())
                        .setText(R.id.XPrice_listItem_time, item.getTime().isEmpty()?"时间":item.getTime())
                        .setText(R.id.XPrice_listItem_location, item.getProvince().isEmpty()?"项目地址":item.getProvince())
                        .setText(R.id.XPrice_listItem_type, item.getProject_type().isEmpty()?"类型":item.getProject_type())
                        .setText(R.id.XPrice_listItem_type1, item.getStage().isEmpty()?"项目阶段":item.getStage());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                Bundle bundle = new Bundle();
                bundle.putString("inquiry_sn1", mAdapter.getItem(position).getInquiry_sn());
                if (mPriceDetailsFragment == null) {
                    mPriceDetailsFragment = new PriceDetailsFragment();
                }

                bundle.putInt("type", 1);
                ShowFragmentUtils.showFragment(getActivity(),
                        mPriceDetailsFragment.getClass(),
                        FragmentTags.FRAGMENT_PRICE_DETAIL,
                        bundle, true);
            }
        };
        mListView.setAdapter(mAdapter);
        setEmptyView(mListView, R.mipmap.default_no_infomation);
    }

    private void setListViewData(int page, final int upDataOrAppend) {
        mPricePresenter.getX_ProjectListData("", "", "", "", page, new IViewPrice<List<X_ProjectModel>>() {
            @Override
            public void onSuccess(List<X_ProjectModel> data) {
                hiddenLoading();
                mListView.onRefreshComplete();
                if (upDataOrAppend == 1) {
                    mAdapter.update(data);
                } else {
                    //停止刷新
                    mListView.onRefreshComplete();
                    showAlertWithMsg("已加载");
                    for (X_ProjectModel model : data) {
                        mAdapter.append(model);
                    }
                }
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                mListView.onRefreshComplete();
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
                mListView.onRefreshComplete();
            }
        });
    }
}
