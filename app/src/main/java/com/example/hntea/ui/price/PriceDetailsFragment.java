package com.example.hnTea.ui.price;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.price.bean.B_ProjectDetailModel;
import com.example.hnTea.mvpmodel.price.bean.B_SingleDetailModel;
import com.example.hnTea.mvpmodel.price.bean.BaseX_ProjectDetail;
import com.example.hnTea.mvpmodel.price.bean.ProductParamItemModel;
import com.example.hnTea.mvpmodel.price.bean.X_ProjectDetailModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleDetailModel;
import com.example.hnTea.mvppresenter.price.IViewPrice;
import com.example.hnTea.mvppresenter.price.PricePresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.price.bean.PriceDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PriceDetailsFragment extends BaseFragment {
    private ListView mListView,mFootListView;
    private CommonAdapter<PriceDetailModel> mAdapter;
    private CommonAdapter<PriceDetailModel> mAdapter1;
    private View mHeaderView;
    private TextView mHeadText;
    private String inquiry_sn1,inquiry_sn2,offer_sn1,offer_sn2;
    private PricePresenter mPricePresenter;
    //处理项目报价与项目询价详情的地址处理
    private View mFooterView1,mFooterView2;
    private TextView xjLocationTx, xjTypeTx,toBPrice1,toBPrice2;
    private int type;
    private X_ProjectDetailModel model;
    private List<ProductParamItemModel> itemModel;
    List<PriceDetailModel> list = new ArrayList<>();

    //******
    //  1 项目询价
    //  2 产品询价
    //  3 项目报价
    //  4 产品报价
    //******
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.fragment_bg));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_price_details, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPricePresenter = new PricePresenter(null);
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        mListView = mFindViewUtils.findViewById(R.id.priceDetail_listView);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.price_detail_list_header, null);
        mHeadText = (TextView) mHeaderView.findViewById(R.id.price_detail_title_header);
        //项目询价footView
        mFooterView1 = LayoutInflater.from(getContext()).inflate(R.layout.xj_project_footer, new LinearLayout(getContext()));
        toBPrice1 = (TextView) mFooterView1.findViewById(R.id.toBPrice);
        xjLocationTx = (TextView) mFooterView1.findViewById(R.id.xj_project_location);
        xjTypeTx = (TextView) mFooterView1.findViewById(R.id.xj_project_type    );
        mFooterView2= LayoutInflater.from(getContext()).inflate(R.layout.price_detail_list_footer, null);
        toBPrice2 = (TextView) mFooterView2.findViewById(R.id.toBPrice);
        mFootListView = (ListView) mFooterView2.findViewById(R.id.XP_CS_listView);
        mListView.addHeaderView(mHeaderView);
        Bundle sn = getArguments();
        inquiry_sn1 = sn.getString("inquiry_sn1");
        inquiry_sn2 = sn.getString("inquiry_sn2");
        offer_sn1 = sn.getString("offer_sn1");
        offer_sn2 = sn.getString("offer_sn2");
    }

    @Override
    protected void setListener() {
        super.setListener();
        if (toBPrice1!=null){
            toBPrice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //我要报价点击事件
                   showDialog();
                }
            });
        }
       if (toBPrice2!=null) {
           toBPrice2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //我要报价点击事件
                    showDialog();
               }
           });
       }
    }

    @Override
    protected void setData() {
        super.setData();
        String barTitle = null;
        setListAdapter();
        switch (type) {
            case 1:
                barTitle = "项目询价单详情";
                break;
            case 2:
                barTitle = "产品询价单详情";
                setFootListAdapter();
                break;
            case 3:
                barTitle = "项目报价单详情";
                break;
            case 4:
                barTitle = "产品报价单详情";
                break;
        }
        mAppTitleBar.getTitle().setText(barTitle);
        setListViewData(type);
    }

    private void setListViewData(int type) {
        switch (type) {
            case 1:
             mPricePresenter.getX_ProjectDetailListData(inquiry_sn1, new IViewPrice<BaseX_ProjectDetail>() {
                 @Override
                 public void onSuccess(BaseX_ProjectDetail data) {
                     hiddenLoading();
                     model = data.getX_projectDetailModel().get(0);
                     itemModel = data.getItemModel();
                     xjLocationTx.setText(model.getProvince().isEmpty()?"省份":model.getProvince());
                     xjTypeTx.setText(model.getProject_type().isEmpty()?"项目类型":model.getProject_type());
                     mHeadText.setText((model.getProject_name().isEmpty()?"项目名称":model.getProject_name()));
                     setListData(null,model,itemModel,null,null);
                     mAdapter.update(getProject_X(model,itemModel));
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
                     showAlertWithMsg(volleyError+"");
                 }
             });

                break;
            case 2:
                mPricePresenter.getX_SingleDetailListData(inquiry_sn2, new IViewPrice<X_SingleDetailModel>() {
                    @Override
                    public void onSuccess(X_SingleDetailModel data) {
                        hiddenLoading();
                        setListData(data,null,null,null,null);
                        mHeadText.setText((data.getProduct_name().isEmpty()?"项目名称":data.getProduct_name()));
                        mAdapter.update(getSingle_X(data));
                        mAdapter1.update(footList(data));
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
                break;
            case 3:
                mPricePresenter.getB_ProjectDetailListData(offer_sn1, new IViewPrice<B_ProjectDetailModel>() {
                    @Override
                    public void onSuccess(B_ProjectDetailModel data) {
                        hiddenLoading();
                        setListData(null,null,null,null,data);
                        xjLocationTx.setText(data.getProvince().isEmpty()?"省份":data.getProvince());
                        xjTypeTx.setText(data.getProject_type().isEmpty()?"项目类型":data.getProject_type());
                        mHeadText.setText((data.getProject_name().isEmpty()?"项目名称":data.getProject_name()));
                        mAdapter.update(getProject_B(data));
                    }

                    @Override
                    public void onPhpFail(String var) {
                        hiddenLoading();
                        showAlertWithMsg(var);
                    }

                    @Override
                    public void onStart(String var) {

                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        hiddenLoading();
                        showAlertWithMsg("请检查网络");
                    }
                });
                break;
            case 4:
                mPricePresenter.getB_SingleDetailListData(offer_sn2, new IViewPrice<B_SingleDetailModel>() {
                    @Override
                    public void onSuccess(B_SingleDetailModel data) {
                        hiddenLoading();
                        setListData(null,null,null,data,null);
                        mHeadText.setText((data.getProduct_name().isEmpty()?"项目名称":data.getProduct_name()));
                        mAdapter.update(getSingle_B(data));

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
                break;
        }
    }

    private List<PriceDetailModel> footList(X_SingleDetailModel data) {
        List<PriceDetailModel> footList = new ArrayList<>();
        footList.add(new PriceDetailModel("发布时间：", data.getPublishTime()));
        footList.add(new PriceDetailModel("产品系列：", data.getProduct_series()));
        footList.add(new PriceDetailModel("产品型号：", data.getProduct_model()));
        footList.add(new PriceDetailModel("产品参数：", data.getParam()));
        footList.add(new PriceDetailModel("产品单位：", data.getUnit()));
        return footList;
    }


    private void setFootListAdapter() {
        mAdapter1 = new CommonAdapter<PriceDetailModel>(getContext(), null, R.layout.price_detail_list_item) {
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
            mFootListView.setAdapter(mAdapter1);
    }

    private void setListAdapter() {
        mAdapter =new CommonAdapter<PriceDetailModel>(getContext(),list,R.layout.price_detail_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {

            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, PriceDetailModel item) {
                holder.setText(R.id.price_detail_title,item.getTitle())
                        .setText(R.id.price_detail_content,item.getContent());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };
        mListView.setAdapter(mAdapter);
    }

    private void setListData(X_SingleDetailModel data,X_ProjectDetailModel data1,
                             List<ProductParamItemModel> itemData,B_SingleDetailModel
                                     b_singleDetailModel,B_ProjectDetailModel b_P_Detailmodel) {
        switch (type) {
            case 1:
                list = getProject_X(data1,itemData);
                mListView.addFooterView(mFooterView1);
                break;
            case 2:
                list = getSingle_X(data);
                mListView.addFooterView(mFooterView2);
                break;
            case 3:
                list = getProject_B(b_P_Detailmodel);
                mListView.addFooterView(mFooterView1);
                toBPrice1.setVisibility(View.GONE);
                break;
            case 4:
                list = getSingle_B(b_singleDetailModel);
                break;
        }
    }

    //我要报价的弹框
    private void showDialog(){
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.btn_star)
                .setTitle("提示：")
                .setMessage("移动端暂无法报价，需报价请上PC端，电力电网址www.dianlidian.com")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }
    private List<PriceDetailModel> getSingle_X(X_SingleDetailModel data) {
        //产品询价数据源
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("询价单号:", data.getInquiry_sn().isEmpty()?"无":data.getInquiry_sn()));
        list.add(new PriceDetailModel("品牌:", data.getBrand().isEmpty()?"无":data.getBrand()));
        list.add(new PriceDetailModel("需求数量:", data.getQuantity().isEmpty()?"无":data.getQuantity()+"台"));
        list.add(new PriceDetailModel("质保期:",data.getWarranty().isEmpty()?"无": String.format("%s%s",data.getWarranty(),"个月")));
        list.add(new PriceDetailModel("交货时间:", data.getReceive_cycle().isEmpty()?"无":data.getReceive_cycle()));
        list.add(new PriceDetailModel("报价截止日期:", data.getEnd_time().isEmpty()?"无":data.getEnd_time()));
        list.add(new PriceDetailModel("是否含税:",(data.getIs_include_tax().equals("1"))? "(含税)含17%增值税":"不含税"));
        list.add(new PriceDetailModel("是否包邮:", (data.getIs_include_shipping().equals("1"))?"包邮":"不包邮"));
        list.add(new PriceDetailModel("备注:", (data.getRemark().isEmpty())?"无":data.getRemark()));
        return list;
    }

    private List<PriceDetailModel> getProject_X(X_ProjectDetailModel data, List<ProductParamItemModel> itemData) {
        //项目询价数据源
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("询价单号:", data.getInquiry_sn().isEmpty()?"无":data.getInquiry_sn()));
        list.add(new PriceDetailModel("询价产品:", itemData.isEmpty()?"暂无":formatList(itemData)));//itemData.toString().substring(1, itemData.toString().length()-2))
        list.add(new PriceDetailModel("项目阶段:", data.getStage().isEmpty()?"项目阶段":data.getStage()));
        list.add(new PriceDetailModel("质保期:", data.getWarranty().isEmpty()?"无": String.format("%s%s",data.getWarranty(),"个月")));
        list.add(new PriceDetailModel("交货时间:",data.getReceive_cycle().isEmpty()?"无":data.getReceive_cycle()));
        list.add(new PriceDetailModel("报价截止日期:", data.getEnd_time().isEmpty()?"无":data.getEnd_time()));
        list.add(new PriceDetailModel("是否含税:",(data.getIs_include_tax().equals("1"))?"(含税)含17%增值税":"不含税"));
        list.add(new PriceDetailModel("是否包邮:", (data.getIs_include_shipping().equals("1"))?"包邮":"不包邮"));
        list.add(new PriceDetailModel("备注:", (data.getRemark().isEmpty())?"无":data.getRemark()));
        return list;
    }

    private List<PriceDetailModel> getSingle_B(B_SingleDetailModel model) {
        //产品报价
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("询价单号:",model.getInquiry_sn().isEmpty()?"无":model.getInquiry_sn()));
        list.add(new PriceDetailModel("品牌:", model.getBrand().isEmpty()?"无":model.getBrand()));
        list.add(new PriceDetailModel("报价次数:", model.getNum().isEmpty()?"无":String.format("%s%s",model.getNum(),"次")));
        list.add(new PriceDetailModel("最低报价:",model.getPrice().isEmpty()?"无":String.format("%s%s",model.getPrice(),"元")));
        list.add(new PriceDetailModel("质保期:", model.getWarranty().isEmpty()?"无":String.format("%s%s",model.getWarranty(),"个月")));
        list.add(new PriceDetailModel("交货时间：", model.getSupply_cycle().isEmpty()?"无":model.getSupply_cycle()));
        list.add(new PriceDetailModel("报价截止日期:",model.getEnd_time().isEmpty()?"无":model.getEnd_time()));
        list.add(new PriceDetailModel("报价有效期:",model.getUseful_time().isEmpty()?"无":model.getUseful_time()));
        list.add(new PriceDetailModel("是否含税:",(model.getIs_include_tax().isEmpty()?"无":(model.getIs_include_tax().equals("1"))?"(含税)含17%增值税":"不含税")));
        list.add(new PriceDetailModel("是否包邮:", (model.getIs_include_shipping().isEmpty()?"无":(model.getIs_include_shipping().equals("1"))?"包邮":"不包邮")));
        list.add(new PriceDetailModel("备注:", (model.getRemark().isEmpty())?"无":model.getRemark()));
        return list;
    }

    private List<PriceDetailModel> getProject_B(B_ProjectDetailModel b_P_Detailmodel) {
        //项目报价
        List<PriceDetailModel> list = new ArrayList<>();
        list.add(new PriceDetailModel("询价单号:",b_P_Detailmodel.getInquiry_sn().isEmpty()?"无":b_P_Detailmodel.getInquiry_sn()));
        list.add(new PriceDetailModel("报价产品",b_P_Detailmodel.getProductItemModels().isEmpty()?"无":formatList(b_P_Detailmodel.getProductItemModels())));
        list.add(new PriceDetailModel("项目阶段:",b_P_Detailmodel.getStage().isEmpty()?"无":b_P_Detailmodel.getStage()));
        list.add(new PriceDetailModel("报价次数:", b_P_Detailmodel.getNum().isEmpty()?"无":String.format("%s%s",b_P_Detailmodel.getNum(),"次")));
        list.add(new PriceDetailModel("最低报价:",  b_P_Detailmodel.getPrice().isEmpty()?"无":b_P_Detailmodel.getPrice()+"元"));
        list.add(new PriceDetailModel("质保期:", b_P_Detailmodel.getWarranty().isEmpty()?"无":String.format("%s%s",b_P_Detailmodel.getWarranty(),"个月") ));
        list.add(new PriceDetailModel("交货时间:", b_P_Detailmodel.getSupply_cycle().isEmpty()?"无":b_P_Detailmodel.getSupply_cycle()));
        list.add(new PriceDetailModel("报价截止日期:",  b_P_Detailmodel.getEnd_time().isEmpty()?"无":b_P_Detailmodel.getEnd_time()));
        list.add(new PriceDetailModel("报价有效期:",  b_P_Detailmodel.getUseful_time().isEmpty()?"无":b_P_Detailmodel.getUseful_time()));
        list.add(new PriceDetailModel("是否含税:",(b_P_Detailmodel.getIs_include_tax().isEmpty()?"无":(b_P_Detailmodel.getIs_include_tax().equals("1"))?"(含税)含17%增值税":"不含税")));
        list.add(new PriceDetailModel("是否包邮:", (b_P_Detailmodel.getIs_include_shipping().isEmpty()?"无":(b_P_Detailmodel.getIs_include_shipping().equals("1"))?"包邮":"不包邮")));
        list.add(new PriceDetailModel("备注:", (b_P_Detailmodel.getRemark().isEmpty())?"无":b_P_Detailmodel.getRemark()));
        return list;
    }

}
