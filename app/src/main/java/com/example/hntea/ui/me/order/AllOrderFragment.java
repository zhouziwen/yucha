package com.example.hnTea.ui.me.order;


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
import com.example.hnTea.mvpmodel.user.bean.MyOrderModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends BaseFragment {
    private ListView mListView;
    private CommonAdapter<MyOrderModel> mAdapter;
    private UserConfigPresenter mUserConfigPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_order, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter=new UserConfigPresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.allOrder_listView);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        setListData();
        mUserConfigPresenter.getMyOrder("0", "1", "10", new IViewUser<List<MyOrderModel>>() {
            @Override
            public void onSuccess(List<MyOrderModel> response) {
                mAdapter.update(response);
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
    }
    private void setListData() {
        mAdapter=new CommonAdapter<MyOrderModel>(getContext(), null,R.layout.my_order_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.myOrder_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }
            //"order_id": "45",                       //订单id
//        "order_sn": "031511742737",             //订单号
//        "order_status": "0",           //订单的状态;0未确认,1确认,2已取消,3无效,4退货
//        "shipping_status": "0",    //商品配送情况;0未发货,1已发货,2已收货,4退货
//        "pay_status": "0",   //支付状态;0未付款;1付款中;2已付款
//        "consignee": "大大去",//收货人
//        "order_amount": "3800.00", //订单金额
//        "goods_info": [           //商品信息
            @Override
            protected void setViewData(int position, BaseViewHolder holder, MyOrderModel item) {
                holder.setText(R.id.myOrder_list_item_OrderNum, item.getOrder_sn())
                        .setText(R.id.myOrder_list_item_OrderUserName, item.getConsignee());

            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };

        mListView.setAdapter(mAdapter);
        setEmptyView(mListView, R.mipmap.default_no_infomation);

    }
}
