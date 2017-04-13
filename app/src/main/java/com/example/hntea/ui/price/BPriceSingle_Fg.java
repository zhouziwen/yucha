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
import com.example.hnTea.mvpmodel.price.bean.B_SingleModel;
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
public class BPriceSingle_Fg extends BaseFragment {
    private PullToRefreshListView mListView;
    private PricePresenter mPricePresenter;
    private CommonAdapter<B_SingleModel> mAdapter;
    private PriceDetailsFragment mPriceDetailsFragment;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bprice_single__fg, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPricePresenter = new PricePresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.BPrice_Single_ListView);
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
        mAdapter = new CommonAdapter<B_SingleModel>(getContext(), null, R.layout.b_price_single_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnclickListener(R.id.b_price_single_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, B_SingleModel item) {
                //XPrice_listItem_type1 截事件
                holder.setText(R.id.XPrice_listItem_title,
                        "【" + item.getInquiry_sn() + "】  " + item.getProduct_name())
                        .setText(R.id.XPrice_listItem_time, item.getComany_name().isEmpty()?"无":item.getComany_name())
                        .setText(R.id.XPrice_listItem_location, item.getQuantity().isEmpty()?"0":item.getQuantity() + item.getUnit())
                        .setText(R.id.XPrice_listItem_type,item.getNum().isEmpty()?"0":item.getNum() + "次")
                        .setText(R.id.XPrice_listItem_type1, "截止时间：" + item.getPendtime());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                Bundle bundle = new Bundle();
                bundle.putString("offer_sn2", mAdapter.getItem(position).getOffer_sn());

                if (mPriceDetailsFragment == null) {
                    mPriceDetailsFragment = new PriceDetailsFragment();
                }
                bundle.putInt("type", 4);
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
        mPricePresenter.getB_SinglePriceListData(page, "", "", "", new IViewPrice<List<B_SingleModel>>() {
            @Override
            public void onSuccess(List<B_SingleModel> data) {
                hiddenLoading();
                mListView.onRefreshComplete();
                if (upDataOrAppend == 1) {
                    mAdapter.update(data);
                } else {
                    //停止刷新
                    mListView.onRefreshComplete();
                    showAlertWithMsg("已加载");
                    for (B_SingleModel model : data) {
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


    //   if (mPriceDetailsFragment == null){
//                            mPriceDetailsFragment =new PriceDetailsFragment();
//                        }
//                        Bundle bundle =new Bundle();
//                        bundle.putInt("type",4);
//                        ShowFragmentUtils.showFragment(getActivity(),
//                                mPriceDetailsFragment.getClass(),
//                                FragmentTags.FRAGMENT_PRICE_DETAIL,
//                                bundle,true);

}
