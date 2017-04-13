package com.example.hnTea.ui.me.MyInquiry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.me.bean.Me_B_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_B_SingleModel;
import com.example.hnTea.mvppresenter.me.IViewMePrice;
import com.example.hnTea.mvppresenter.me.MePricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeBPriceFragment extends BaseFragment {
    private RadioButton button, button1;
    private PullToRefreshListView mListView;
    private MePricePresenter mMePricePresenter;
    private CommonAdapter<Me_B_SingleModel> mAdapter;
    private CommonAdapter<Me_B_ProjectModel> mAdapter1;
    private MePriceDetailFragment mMePriceDetailFragment;

    public MeBPriceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_bprice, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMePricePresenter = new MePricePresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.me_BPrice_listView);
        button = (RadioButton) getActivity().findViewById(R.id.product_RB);
        button1 = (RadioButton) getActivity().findViewById(R.id.project_RB);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        setListData();
        getServerData(1);
        mListView.setEmptyView(mFindViewUtils.findViewById(R.mipmap.default_no_infomation));
    }

    //获取服务器数据
    private void getServerData(int page) {
        if (button.isChecked()) {
            mMePricePresenter.getMe_B_Single(page, new IViewMePrice<List<Me_B_SingleModel>>() {
                @Override
                public void onSuccess(List<Me_B_SingleModel> data) {
                    mAdapter.update(data);
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
                    showAlertWithMsg(volleyError.toString());
                }
            });
        } else if (button1.isChecked()) {
            mMePricePresenter.getMe_B_Project(page, new IViewMePrice<List<Me_B_ProjectModel>>() {
                @Override
                public void onSuccess(List<Me_B_ProjectModel> data) {
                    mAdapter1.update(data);
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
                    showAlertWithMsg(volleyError.toString());
                }
            });
        }
    }

    private void setListData() {
        if (button.isChecked()) {
            mAdapter = new CommonAdapter<Me_B_SingleModel>(getContext(), null, R.layout.b_price_single_list_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {
                    view.setOnClickListener(holder);
                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Me_B_SingleModel item) {
                    //XPrice_listItem_type1 截事件
                    holder.setText(R.id.XPrice_listItem_title,
                            "【" + item.getInquiry_sn() + "】  " + item.getProduct_name())
                            .setText(R.id.XPrice_listItem_time,item.getBrand().isEmpty()?"无":String.format("%s%s", "品牌:", item.getBrand()))
                            .setText(R.id.XPrice_listItem_location, item.getQuantity().isEmpty()?"无":String.format("%s%s%s", "数量：", item.getQuantity(), item.getUnit()))
                            .setText(R.id.XPrice_listItem_type,item.getNum().isEmpty()?"无":String.format("%s%s%s", "报价次数:", item.getNum(), "次"))
                            .setText(R.id.XPrice_listItem_type1, item.getPendtime().isEmpty()?"无":"截止时间：" + item.getPendtime());
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {
                    pushDetail(mAdapter.getItem(position).getInquiry_sn());
                }
            };
        } else if (button1.isChecked()) {
            mAdapter1 = new CommonAdapter<Me_B_ProjectModel>(getContext(), null, R.layout.b_price_project_list_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {
                    view.setOnClickListener(holder);
                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Me_B_ProjectModel item) {
                    holder.setText(R.id.XPrice_listItem_title,
                            "【" + item.getInquiry_sn() + "】  " + item.getProject_name())
                            .setText(R.id.XPrice_listItem_time, item.getProvince().isEmpty()?"无":String.format("%s%s", "省份：", item.getProvince()))
                            .setText(R.id.XPrice_listItem_location, item.getProject_type().isEmpty()?"无":String.format("%s%s", "项目类型：", item.getProject_type()))
                            .setText(R.id.XPrice_listItem_type, item.getStage().isEmpty()?"无":String.format("%s%s", "项目阶段：", item.getStage()))
                            .setText(R.id.XPrice_listItem_type1,item.getPendtime().isEmpty()?"无": String.format("%s%s", "截止时间：", item.getPendtime()));
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {
                    pushDetail(mAdapter1.getItem(position).getInquiry_sn());
                }
            };
        }
        mListView.setAdapter(button.isChecked() ? mAdapter : mAdapter1);
    }

    private void pushDetail(String var) {

        mMePriceDetailFragment = new MePriceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("b_inquiry_sn", var);
        bundle.putInt("mePriceType", 2);
        ShowFragmentUtils.showFragment(getActivity(),
                mMePriceDetailFragment.getClass(),
                "me_price_detail",
                bundle,
                true);
    }
}
