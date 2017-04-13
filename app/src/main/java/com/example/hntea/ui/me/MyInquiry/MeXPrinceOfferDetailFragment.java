package com.example.hnTea.ui.me.MyInquiry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.me.bean.Detail_XProject_offer_product;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Project_offer_detail;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Project_offer_detail_header;
import com.example.hnTea.mvppresenter.me.IViewMePrice;
import com.example.hnTea.mvppresenter.me.MePricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.custom.CustomListView;
import com.example.hnTea.ui.price.bean.PriceDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeXPrinceOfferDetailFragment extends BaseFragment {
    private View mHeaderView;
    private CustomListView mListView;
    private CustomListView mHeaderListView;
    private MePricePresenter mMePricePresenter;
    private CommonAdapter<Detail_XProject_offer_product> mListAdapter;
    private CommonAdapter<PriceDetailModel> mHeaderAdapter;
    private String offer_sn;
    private TextView mHeaderListTitle, mListTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me_xprince_offer_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mMePricePresenter = new MePricePresenter(null);
        mAppTitleBar.getTitle().setText("我的竞价单详情");
        mListView = mFindViewUtils.findViewById(R.id.me_price_detail_listView);
        mHeaderView = LayoutInflater.from(getContext()).inflate(
                R.layout.me_price_detail_header, null);
        mHeaderListTitle = (TextView) mHeaderView.findViewById(R.id.header_list_title);
        mListTitle = (TextView) mHeaderView.findViewById(R.id.list_title);
        mHeaderListView = (CustomListView) mHeaderView.findViewById(
                R.id.me_price_header_listView);
        mListView.addHeaderView(mHeaderView);
        offer_sn = getArguments().getString("offer_sn");
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("供应商报价详情");
        mHeaderListTitle.setText("供应商信息");
        mListTitle.setText("报价清单");
        setListAdapter();
        setHeaderAdapter();
        setListViewData();

    }

    private void setHeaderAdapter() {
        mHeaderAdapter = new CommonAdapter<PriceDetailModel>(getContext(), null, R.layout.price_detail_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {

            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, PriceDetailModel item) {

                holder.setText(R.id.price_detail_title, item.getTitle())
                        .setText(R.id.price_detail_content, item.getContent());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };
        mHeaderListView.setAdapter(mHeaderAdapter);
    }

    private String castString(Detail_X_Project_offer_detail_header detail_header) {
        switch (detail_header.getOffer_status()) {
            case "0":
                return "报价中";
            case "1":
                return "已中标";
            case "2":
                return "已过期";
        }
        return "";
    }
    private List<PriceDetailModel> setHeaderListData(Detail_X_Project_offer_detail_header detail_header) {
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("报价单号:", detail_header.getOffer_sn().isEmpty()?"无":detail_header.getOffer_sn()));
        list.add(new PriceDetailModel("交货时间：", detail_header.getSupply_cycle().isEmpty()?"无":detail_header.getSupply_cycle()));
        list.add(new PriceDetailModel("质保期", detail_header.getWarranty().isEmpty()?"无":detail_header.getWarranty()+"个月"));
        list.add(new PriceDetailModel("报价有效期:", detail_header.getEnd_time().isEmpty()?"无":detail_header.getEnd_time()));
        list.add(new PriceDetailModel("报价时间:", detail_header.getTime().isEmpty()?"无":detail_header.getTime()));
        list.add(new PriceDetailModel("报价次数:", detail_header.getOffer_num().isEmpty()?"无":detail_header.getOffer_num()+"次"));
        list.add(new PriceDetailModel("报价状态:", detail_header.getOffer_status().isEmpty()?"无":castString(detail_header)));
        list.add(new PriceDetailModel("总价:", detail_header.getTotal_price().isEmpty() ? "无" : detail_header.getTotal_price()+"元"));
        list.add(new PriceDetailModel("联系人:", detail_header.getContact_name().isEmpty() ? "无" : detail_header.getContact_name()));
        list.add(new PriceDetailModel("联系电话:", detail_header.getContact_phone().isEmpty()?"无":detail_header.getContact_phone()));
        list.add(new PriceDetailModel("公司名称:", detail_header.getComany_name().isEmpty()?"无":detail_header.getComany_name()));
        list.add(new PriceDetailModel("备注:", detail_header.getRemark().isEmpty()?"无":detail_header.getRemark()));
        return list;
    }
    private void setListViewData() {
        mMePricePresenter.getMeDetail_X_Project_Offer_detail(offer_sn, new IViewMePrice<Detail_X_Project_offer_detail>() {
            @Override
            public void onSuccess(Detail_X_Project_offer_detail data) {
                hiddenLoading();
                mHeaderAdapter.update(setHeaderListData(data.getOffer_detail_header()));
                mListAdapter.update(data.getDetail_products());
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
                showAlertWithMsg(volleyError + "");
            }
        });
    }
    private void setListAdapter() {
        mListAdapter = new CommonAdapter<Detail_XProject_offer_product>(getContext(), null, R.layout.me_xprice_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {

            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, Detail_XProject_offer_product item) {
                holder.setText(R.id.me_xPrice_title, item.getProduct_name())
                        .setText(R.id.me_xPrice_canshu, item.getParam().isEmpty() ? "参数：无" : String.format("%s%s", "参数：", item.getParam()))
                        .setText(R.id.me_xPrice_count, item.getQuantity().isEmpty() ? "数量：无" : String.format("%s%s%s", "数量：", item.getQuantity(), item.getUnit()))
                        .setText(R.id.me_xPrice_pinpai, item.getBrand().isEmpty() ? "品牌：无" : String.format("%s%s", "品牌：", item.getBrand()))
                        .setText(R.id.me_xPrice_type, item.getModel().isEmpty() ? "型号：无" : String.format("%s%s", "型号：", item.getModel()))
                        .setText(R.id.price, item.getPrice().isEmpty() ? "" : String.format("%s%s%s", "单价：", item.getPrice(), "元"));
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };
        mListView.setAdapter(mListAdapter);
    }
}
