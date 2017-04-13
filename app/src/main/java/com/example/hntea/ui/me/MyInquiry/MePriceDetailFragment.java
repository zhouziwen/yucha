package com.example.hnTea.ui.me.MyInquiry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.me.bean.Detail_B_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_B_offer;
import com.example.hnTea.mvpmodel.me.bean.Detail_J_Model;
import com.example.hnTea.mvpmodel.me.bean.Detail_J_offer;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Project_offer;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_inquiry;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Single_offer;
import com.example.hnTea.mvpmodel.me.bean.Detail_product;
import com.example.hnTea.mvppresenter.me.IViewMePrice;
import com.example.hnTea.mvppresenter.me.MePricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.custom.CustomListView;
import com.example.hnTea.ui.price.bean.PriceDetailModel;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MePriceDetailFragment extends BaseFragment {
    private MePricePresenter mMePricePresenter;
    private CustomListView mListView;
    private CustomListView mFooterListView;
    private CustomListView mHeaderListView;
    private View mHeaderView;
    private View mFooterView;
    private TextView mFooterTitle;
    private CommonAdapter<Detail_product> mListAdapter;
    private CommonAdapter<PriceDetailModel> mHeaderAdapter;
    private CommonAdapter<Detail_X_Single_offer> mFooterAdapter;
    private CommonAdapter<Detail_B_offer> mB_FooterAdapter;
    private CommonAdapter<Detail_X_Project_offer> mFooterAdapter1;
    private CommonAdapter<Detail_B_offer> mB_FooterAdapter1;
    private CommonAdapter<Detail_J_offer> mJFooterAdapter;
    private CommonAdapter<Detail_J_offer> mJFooterAdapter1;
    private int mePriceType;
    //接口文档"m" 对应的参数
    private static  final String METHOD_SINGLE = "productBidDetail";
    private static final String METHOD_PROJECT = "projectBidDetail";
    private RadioButton button, button1;
    //上个界面传来的询价单号
    private String inquiry_sn, b_inquiry_sn,j_inquiry_sn;
    private MeXPrinceOfferDetailFragment mMePriceDetailFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me_price_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setStateBar();
        mMePricePresenter = new MePricePresenter(null);
        mAppTitleBar.getTitle().setText("我的竞价单详情");
        mListView = mFindViewUtils.findViewById(R.id.me_price_detail_listView);
        mHeaderView = LayoutInflater.from(getContext()).inflate(
                R.layout.me_price_detail_header, null);
        mFooterView = LayoutInflater.from(getContext()).inflate(
                R.layout.me_price_detail_list_footer, null);
        mHeaderListView = (CustomListView) mHeaderView.findViewById(
                R.id.me_price_header_listView);
        mFooterListView = (CustomListView) mFooterView.findViewById(
                R.id.me_price_footer_listView);
        mFooterTitle = (TextView) mFooterView.findViewById(R.id.me_price_detail_footer_title);
        mListView.addHeaderView(mHeaderView);
        mePriceType = getArguments().getInt("mePriceType");
        button = (RadioButton) getActivity().findViewById(R.id.product_RB);
        button1 = (RadioButton) getActivity().findViewById(R.id.project_RB);
        //接受询价单号
        inquiry_sn = getArguments().getString("inquiry_sn");
        b_inquiry_sn = getArguments().getString("b_inquiry_sn");
        j_inquiry_sn = getArguments().getString("j_inquiry_sn");
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

    @Override
    protected void setData() {
        super.setData();
        setListAdapter();
        setHeaderAdapter();
        setTitle();
        setListViewData();
    }

    private void setTitle() {
        switch (mePriceType) {
            case 1:
                if (button.isChecked()) {
                    mAppTitleBar.getTitle().setText("我的产品询价单详情");
                } else {
                    mAppTitleBar.getTitle().setText("我的项目询价单详情");
                }
                mFooterTitle.setText("供应商报价信息");
                setX_FooterAadpter();
                break;
            case 3:
                if (button.isChecked()) {
                    mAppTitleBar.getTitle().setText("我的产品竞价单详情");
                } else {
                    mAppTitleBar.getTitle().setText("我的项目竞价单详情");
                }

                mFooterTitle.setText("供应商竞价信息");
                setJ_FooterAadpter();
                break;
            case 2:
                if (button.isChecked()) {
                    mAppTitleBar.getTitle().setText("我的产品报价单详情");
                } else {
                    mAppTitleBar.getTitle().setText("我的项目报价单详情");
                }
                mFooterTitle.setText("我的报价信息");
                setB_FooterAdapter();
                break;
        }
    }

    private void setListViewData() {
        if (button.isChecked()) {
            switch (mePriceType) {
                case 1:
                    mMePricePresenter.getMeDetail_X_Single(inquiry_sn,
                            new IViewMePrice<Detail_X_SingleModel>() {
                                @Override
                                public void onSuccess(Detail_X_SingleModel data) {
                                    hiddenLoading();
                                    mHeaderAdapter.update(setHeaderListData(data.getDetail_x_single_inquiries().get(0)));
                                    mListAdapter.update(data.getDetail__products());
                                    if (!data.getDetail_x_single_offers().isEmpty()) {
                                        mFooterAdapter.update(data.getDetail_x_single_offers());
                                    } else {
                                        mListView.removeFooterView(mFooterView);
                                    }
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
                    break;
                case 2:
                    mMePricePresenter.getMeDetail_B_Single(b_inquiry_sn, new IViewMePrice<Detail_B_SingleModel>() {
                        @Override
                        public void onSuccess(Detail_B_SingleModel data) {
                            hiddenLoading();
                            mHeaderAdapter.update(setHeaderListData(data.getDetail_x_single_inquiries().get(0)));
                            mListAdapter.update(data.getDetail__products());
                            if (!data.getDetail_b_single_offers().isEmpty()) {
                                mB_FooterAdapter.update(data.getDetail_b_single_offers());
                            } else {
                                mListView.removeFooterView(mFooterView);
                            }
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
                            showAlertWithMsg(volleyError.toString());
                        }
                    });
                    break;
                case 3:
                    mMePricePresenter.getMeDetail_J_Data(j_inquiry_sn, METHOD_SINGLE, new IViewMePrice<Detail_J_Model>() {
                        @Override
                        public void onSuccess(Detail_J_Model data) {
                            mHeaderAdapter.update(setHeaderListData(data.getDetail_J_inquiries().get(0)));
                            mListAdapter.update(data.getDetail_J_products());
                            if (!data.getDetail_J_offers().isEmpty()) {
                                mJFooterAdapter.update(data.getDetail_J_offers());
                            } else {
                                mListView.removeFooterView(mFooterView);
                            }
                        }
                        @Override
                        public void onPhpFail(String var) {

                        }
                        @Override
                        public void onStart(String var) {

                        }
                        @Override
                        public void onFail(VolleyError volleyError) {

                        }
                    });
                    break;
            }
        } else if (button1.isChecked()) {
            switch (mePriceType) {
                case 1:
                    mMePricePresenter.getMeDetail__X_Project(inquiry_sn, new IViewMePrice<Detail_X_ProjectModel>() {
                        @Override
                        public void onSuccess(Detail_X_ProjectModel data) {
                            hiddenLoading();
                            mHeaderAdapter.update(setHeaderListData(data.getDetail_x_project_inquiries().get(0)));
                            mListAdapter.update(data.getDetail_x_project_products());
                            if (!data.getDetail_x_project_offers().isEmpty()) {
                                mFooterAdapter1.update(data.getDetail_x_project_offers());
                            } else {
                                mListView.removeFooterView(mFooterView);
                            }
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
                            showAlertWithMsg(volleyError.toString());
                        }
                    });
                    break;
                case 2:
                    mMePricePresenter.getMeDetail_B_Project(b_inquiry_sn, new IViewMePrice<Detail_B_SingleModel>() {
                        @Override
                        public void onSuccess(Detail_B_SingleModel data) {
                            mHeaderAdapter.update(setHeaderListData(data.getDetail_x_single_inquiries().get(0)));
                            mListAdapter.update(data.getDetail__products());
                            if (!data.getDetail_b_single_offers().isEmpty()) {
                                mB_FooterAdapter1.update(data.getDetail_b_single_offers());
                            } else {
                                mListView.removeFooterView(mFooterView);
                            }
                        }
                        @Override
                        public void onPhpFail(String var) {

                        }
                        @Override
                        public void onStart(String var) {

                        }
                        @Override
                        public void onFail(VolleyError volleyError) {

                        }
                    });
                    break;
                case 3:
                    mMePricePresenter.getMeDetail_J_Data(j_inquiry_sn, METHOD_PROJECT, new IViewMePrice<Detail_J_Model>() {
                        @Override
                        public void onSuccess(Detail_J_Model data) {
                            mHeaderAdapter.update(setHeaderListData(data.getDetail_J_inquiries().get(0)));
                            mListAdapter.update(data.getDetail_J_products());
                            if (!data.getDetail_J_offers().isEmpty()) {
                                mJFooterAdapter1.update(data.getDetail_J_offers());
                            } else {
                                mListView.removeFooterView(mFooterView);
                            }
                        }
                        @Override
                        public void onPhpFail(String var) {

                        }
                        @Override
                        public void onStart(String var) {

                        }
                        @Override
                        public void onFail(VolleyError volleyError) {

                        }
                    });
                    break;
            }
        }
    }

    private void setListAdapter() {
        mListAdapter = new CommonAdapter<Detail_product>(getContext(), null, R.layout.me_xprice_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {

            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, Detail_product item) {
                holder.setText(R.id.me_xPrice_title, item.getProduct_name())
                        .setText(R.id.me_xPrice_canshu,  item.getParam().isEmpty()?"参数：无":String.format("%s%s", "参数：", item.getParam()))
                        .setText(R.id.me_xPrice_count, item.getQuantity().isEmpty()?"数量：无":String.format("%s%s%s", "数量：", item.getQuantity(), item.getUnit()))
                        .setText(R.id.me_xPrice_pinpai,item.getBrand().isEmpty()?"品牌：无": String.format("%s%s", "品牌：", item.getBrand()))
                        .setText(R.id.me_xPrice_type, item.getModel().isEmpty()?"型号：无":String.format("%s%s", "型号：", item.getModel()));
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };
        mListView.setAdapter(mListAdapter);
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

    private List<PriceDetailModel> setHeaderListData(Detail_inquiry x_single_inquiry) {
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("询价单号:", x_single_inquiry.getInquiry_sn().isEmpty()?"无":x_single_inquiry.getInquiry_sn()));
        list.add(new PriceDetailModel("收货人：", x_single_inquiry.getReceiver().isEmpty()?"无":x_single_inquiry.getReceiver()));
        list.add(new PriceDetailModel("联系方式", x_single_inquiry.getTelephone().isEmpty()?"无":x_single_inquiry.getTelephone()));
        list.add(new PriceDetailModel("地址:", x_single_inquiry.getAddress().isEmpty()?"无":x_single_inquiry.getAddress()));
        list.add(new PriceDetailModel("报价截止日期:", x_single_inquiry.getEnd_time().isEmpty()?"无":x_single_inquiry.getEnd_time()));
        list.add(new PriceDetailModel("交货时间:", x_single_inquiry.getReceive_cycle().isEmpty()?"无":x_single_inquiry.getReceive_cycle()));
        list.add(new PriceDetailModel("质保期:", x_single_inquiry.getWarranty().isEmpty()?"无":x_single_inquiry.getWarranty()+"个月"));
        list.add(new PriceDetailModel("是否含税:", x_single_inquiry.getIs_include_tax().isEmpty()?"无":(x_single_inquiry.getIs_include_tax().equals("1"))?"含17%增值税":"不含税"));
        list.add(new PriceDetailModel("是否含运费:",x_single_inquiry.getIs_include_shipping().isEmpty()?"无":(x_single_inquiry.getIs_include_shipping().equals("1"))?"包邮":"不包邮"));
        list.add(new PriceDetailModel("备注:", x_single_inquiry.getRemark().isEmpty()?"无":x_single_inquiry.getRemark()));
        return list;
    }

    private void setB_FooterAdapter() {
        mListView.addFooterView(mFooterView);
        if (button.isChecked()) {
            mB_FooterAdapter = new CommonAdapter<Detail_B_offer>(getContext(), null, R.layout.me_bj_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {
                }
                @Override
                protected void setViewDimen(View convertView) {

                }
                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_B_offer item) {
                    int num = position + 1;
                    holder.setText(R.id.me_bj_info_baoZhiQi,item.getWarranty().isEmpty()?"无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"))
                            .setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime())
                            .setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"无":String.format("%s%s", "交货时间：", item.getSupply_cycle()))
                            .setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"无":String.format("%s%s", "¥", item.getTotal_price()))
                            .setText(R.id.me_bj_info_title, String.format("%s%s%s", "第", num, "次报价"));
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {

                }
            };
            mFooterListView.setAdapter(mB_FooterAdapter);
        } else if (button1.isChecked()) {
            mB_FooterAdapter1 = new CommonAdapter<Detail_B_offer>(getContext(), null, R.layout.me_bj_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {

                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_B_offer item) {
                    int num = position + 1;
                    holder.setText(R.id.me_bj_info_baoZhiQi,item.getWarranty().isEmpty()?"质保期：无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"))
                            .setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime())
                            .setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"交货时间：无":String.format("%s%s", "交货时间：", item.getSupply_cycle()))
                            .setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"¥0":String.format("%s%s", "¥", item.getTotal_price()))
                            .setText(R.id.me_bj_info_title, String.format("%s%s%s", "第", num, "次报价"));
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {

                }
            };
            mFooterListView.setAdapter(mB_FooterAdapter1);
        }
    }

    private void setX_FooterAadpter() {
        mListView.addFooterView(mFooterView);
        if (button.isChecked()) {
            mFooterAdapter = new CommonAdapter<Detail_X_Single_offer>(getContext(), null, R.layout.me_xjjj_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {
                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_X_Single_offer item) {
                    holder.setText(R.id.me_bj_info_baoZhiQi, item.getWarranty().isEmpty()?"质保期：无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"));
                    holder.setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime());
                    holder.setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"交货日期：无":String.format("%s%s", "交货日期：", item.getSupply_cycle()));
                    holder.setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"¥0":String.format("%s%s", "¥", item.getTotal_price()));
                    holder.setText(R.id.me_bj_info_title, item.getComany_name().isEmpty()?"无":item.getComany_name());
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {

                }

            };
            mFooterListView.setAdapter(mFooterAdapter);
        } else if (button1.isChecked()) {
            mFooterAdapter1 = new CommonAdapter<Detail_X_Project_offer>(getContext(), null, R.layout.me_bj_offer_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {
                    view.setOnClickListener(holder);

                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_X_Project_offer item) {
                    holder.setText(R.id.me_bj_info_baoZhiQi, item.getWarranty().isEmpty()?"质保期：无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"));
                    holder.setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime());
                    holder.setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"交货时间：无":String.format("%s%s", "交货时间：", item.getSupply_cycle()));
                    holder.setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"¥0":String.format("%s%s", "¥", item.getTotal_price()));
                    holder.setText(R.id.me_bj_info_title, item.getComany_name().isEmpty()?"无":item.getComany_name());
//                    if (!item.getOfferProductses().isEmpty()) {
//                        holder.setText(R.id.me_bj_info_offer_product, item.getOfferProductses().
//                                toString().substring(1, item.getOfferProductses().toString().length()-2));
//                    }
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {
                    pushDetail(mFooterAdapter1.getItem(position).getOffer_sn());
                }
            };
            mFooterListView.setAdapter(mFooterAdapter1);
        }
    }
    private void setJ_FooterAadpter() {
        mListView.addFooterView(mFooterView);
        if (button.isChecked()) {
            mJFooterAdapter = new CommonAdapter<Detail_J_offer>(getContext(), null, R.layout.me_xjjj_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {

                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_J_offer item) {
                    holder.setText(R.id.me_bj_info_baoZhiQi, item.getWarranty().isEmpty()?"质保期：无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"));
                    holder.setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime());
                    holder.setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"交货时间：无":String.format("%s%s", "交货时间：", item.getSupply_cycle()));
                    holder.setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"¥0":String.format("%s%s", "¥", item.getTotal_price()));
                    holder.setText(R.id.me_bj_info_title, item.getComany_name().isEmpty()?"无":item.getComany_name());
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {

                }

            };
            mFooterListView.setAdapter(mJFooterAdapter);
        } else if (button1.isChecked()) {
            mJFooterAdapter1 = new CommonAdapter<Detail_J_offer>(getContext(), null, R.layout.me_bj_offer_info_item) {
                @Override
                protected void setListeners(BaseViewHolder holder, View view, int position) {

                }

                @Override
                protected void setViewDimen(View convertView) {

                }

                @Override
                protected void setViewData(int position, BaseViewHolder holder, Detail_J_offer item) {
                    holder.setText(R.id.me_bj_info_baoZhiQi, item.getWarranty().isEmpty()?"质保期：无":String.format("%s%s%s", "质保期：", item.getWarranty(), "个月"));
                    holder.setText(R.id.me_bj_info_date, item.getTime().isEmpty()?"无":item.getTime());
                    holder.setText(R.id.me_bj_info_harvest_cycle, item.getSupply_cycle().isEmpty()?"收货周期：无":String.format("%s%s", "收货周期：", item.getSupply_cycle()));
                    holder.setText(R.id.me_bj_info_price, item.getTotal_price().isEmpty()?"¥0":String.format("%s%s", "¥", item.getTotal_price()));
                    holder.setText(R.id.me_bj_info_title, item.getComany_name().isEmpty()?"无":item.getComany_name());
                }

                @Override
                public void onClickBack(int position, View view, BaseViewHolder holder) {

                }
            };
            mFooterListView.setAdapter(mJFooterAdapter1);
        }
    }
    private void pushDetail(String var) {

        mMePriceDetailFragment = new MeXPrinceOfferDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("offer_sn", var);
        ShowFragmentUtils.showFragment(getActivity(),
                mMePriceDetailFragment.getClass(),
                " ",
                bundle,
                true);
    }
}
