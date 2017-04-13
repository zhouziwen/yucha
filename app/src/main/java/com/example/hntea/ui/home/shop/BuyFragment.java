package com.example.hnTea.ui.home.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.mvpmodel.home.bean.CheckOrderModel;
import com.example.hnTea.mvpmodel.home.bean.CreateOrderModel;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.mvppresenter.home.ShopOrderPresenter;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.rxjava.eventBean.EventAddress;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.shop.bean.ShopTypeModel;
import com.example.hnTea.ui.me.user.AddressFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.toast.ApToast;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends BaseFragment {
    private ShopOrderPresenter mShopOrderPresenter;
    private CheckOrderModel mData;
    private RecyclerView mRecyclerView;
    private RelativeLayout mNoAddressLayout, mHasAddressLayout;
    private BaseRecyclerAdapter<CheckOrderModel.goods> mAdapter;
    private AddressFragment mAddressFragment;
    private ListView mPayListView;
    private CommonAdapter<ShopTypeModel> mTypeAdapter;
    private List<Boolean> boolList = new ArrayList<>();
    private int mChose = 0;
    private TextView mToPay, mAddress_name, mAddress_num, mAddress_detail, mAllPrice;
    private String project_id = "";
    private String num = "";
    private String changeId ="";
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    private Subscription mSubscription;

    @Override
    public void onStart() {
        super.onStart();
        mSubscription = RxEventBus.getDefault().toObservable(EventAddress.class)
                .subscribe(eventAddress -> {
                    mAddress_name.setText(eventAddress.getReceiver());
                    mAddress_num.setText(eventAddress.getTelephone());
                    mAddress_detail.setText(eventAddress.getAddress());
                    if (changeId.equals(eventAddress.getId())){
                        //说明管理收货地址删除了地址
//                        mHasAddressLayout.setVisibility(View.GONE);
//                        mNoAddressLayout.setVisibility(View.VISIBLE);
                    }
                    changeId =eventAddress.getId();
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buy, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("确认订单");
        project_id = getArguments().getString("project_id");
        num = getArguments().getString("num");
        mShopOrderPresenter = new ShopOrderPresenter(null);
        mRecyclerView = mFindViewUtils.findViewById(R.id.buy_recyclerView);
        mPayListView = mFindViewUtils.findViewById(R.id.buy_payType_list);
        mNoAddressLayout = mFindViewUtils.findViewById(R.id.buy_noAddress);
        mHasAddressLayout = mFindViewUtils.findViewById(R.id.buy_hasAddress);
        mAllPrice = mFindViewUtils.findViewById(R.id.buy_allPrice);
        mAddress_name = mFindViewUtils.findViewById(R.id.buy_userName);
        mAddress_num = mFindViewUtils.findViewById(R.id.buy_userNum);
        mAddress_detail = mFindViewUtils.findViewById(R.id.buy_detail);
        mToPay = mFindViewUtils.findViewById(R.id.shop_toBuy);
        boolList.add(true);
        boolList.add(false);
        boolList.add(false);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mToPay.setOnClickListener(v -> {
            //去支付
            String id ="";
            if (changeId.equals("")){
                id =mData.getAddress().getId();
            }else {
                id =changeId;
            }
            mShopOrderPresenter.getCreateOrderData(project_id,
                    num,
                    id
                    , "" + mChose, new IViewHome<CreateOrderModel>() {
                        @Override
                        public void onSuccess(CreateOrderModel data) {
                            hiddenLoading();
                            showAlertWithMsg("订单已生成");
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
                        }
                    });
        });
        mHasAddressLayout.setOnClickListener(v ->
                toAddress()
        );
        mNoAddressLayout.setOnClickListener(v ->
                toAddress()
        );
    }

    @Override
    protected void setData() {
        super.setData();
        setPayListBody();
        setDataSourceRecyclerBody();
        setDataSourceRecyclerData();
    }

    private void setDataSourceRecyclerBody() {
        mAdapter = new BaseRecyclerAdapter<CheckOrderModel.goods>(getContext(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.buy_shop_item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, CheckOrderModel.goods item) {
                ImageView img = holder.getView(R.id.buy_shopIcon);
                Glide.with(getContext())
                        .load(item.getGoods_thumb())
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(img);
                holder.setText(R.id.buy_shopTitle, item.getGoods_name())
                        .setText(R.id.buy_shopPrice, "￥" + item.getUnit_price())
                        .setText(R.id.buy_shopNum, "x" + item.getNum());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setDataSourceRecyclerData() {
        mShopOrderPresenter.getCheckOrderData(project_id, num, new IViewHome<CheckOrderModel>() {
            @Override
            public void onSuccess(CheckOrderModel data) {
                hiddenLoading();
                mData = data;
                mAdapter.upData(data.getGoods());
                mAllPrice.setText("总计：￥" + mData.getTotal_price());
                if (data.getAddress() == null) {
                    //没有收货地址
                    mNoAddressLayout.setVisibility(View.VISIBLE);
                    ApToast.showBottom("请选择收货地址");
                    mToPay.setClickable(false);
                    mToPay.setBackground(getResources().getDrawable(R.drawable.shop_car_delete_bg));
                } else {
                    //有收货地址
                    mHasAddressLayout.setVisibility(View.VISIBLE);
                    mToPay.setClickable(true);
                    setAddressData();
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
                showAlertWithMsg("请检查网络");
            }
        });
    }

    private void setAddressData() {
        mAddress_name.setText(mData.getAddress().getReceiver()
                == null ? "" : "收货人:" + mData.getAddress().getReceiver());
        mAddress_num.setText(mData.getAddress().getTelephone()
                == null ? "" : mData.getAddress().getTelephone());
        mAddress_detail.setText(mData.getAddress().getAddress()
                == null ? "" : "收货地址:" + mData.getAddress().getAddress());
    }

    private void setPayListBody() {
        mTypeAdapter = new CommonAdapter<ShopTypeModel>(getContext(), getTypeData(), R.layout.pay_type) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.pay_type_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {
            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, ShopTypeModel item) {
                holder.setText(R.id.buy_type_type, item.getType())
                        .setImageRes(R.id.buy_type_icon, item.getIcon());
                if (position == mChose) {
                    holder.setImageRes(R.id.buy_type_boolView, R.mipmap.buy_selector);
                } else {
                    holder.setImageRes(R.id.buy_type_boolView, R.mipmap.buy_nomal);
                }
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                mChose = position;
                mTypeAdapter.notifyDataSetChanged();
            }
        };
        mPayListView.setAdapter(mTypeAdapter);
    }

    private void toAddress() {
        //跳转收货地址
        if (mAddressFragment == null) {
            mAddressFragment = new AddressFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("address_type", "2");
        ShowFragmentUtils.showFragment(getActivity(),
                mAddressFragment.getClass(),
                FragmentTags.FRAGMENT_MANAGE_ADDRESS,
                bundle, true);
    }

    private List<ShopTypeModel> getTypeData() {
        List<ShopTypeModel> list = new ArrayList<>();
        list.add(new ShopTypeModel(R.mipmap.buy_wechat, "微信支付", boolList.get(0)));
        list.add(new ShopTypeModel(R.mipmap.buy_alipay, "支付宝支付", boolList.get(1)));
        list.add(new ShopTypeModel(R.mipmap.buy_card, "银联支付", boolList.get(2)));
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
