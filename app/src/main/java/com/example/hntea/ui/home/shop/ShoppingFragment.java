package com.example.hnTea.ui.home.shop;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.adapter.recyclerView.shopCarAdapter.ShopCarListAdapter;
import com.example.hnTea.mvpmodel.home.bean.HomeHListViewModel;
import com.example.hnTea.mvpmodel.home.bean.ShopCarListModel;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.mvppresenter.home.ShopOrderPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.toast.ApToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView mShopCarRecyclerView;
    private RecyclerView.LayoutManager manager;
    private BaseRecyclerAdapter<HomeHListViewModel> mRecyclerAdapter;
    private CheckBox mAllCheckCheckBox;
    private LinearLayout mAllCheckLayout;
    private ShopCarListAdapter mCarListAdapter;
    private TextView mTotalPriceTxt1, mTotalPriceTxt2, mToPayTxt;
    protected static final int SELECT_TRUE = 1, SELECT_FALSE = 0;
    private List<ShopCarListModel> mShopCarListModels;
    private HomePresenter mHomePresenter;
    private ShopOrderPresenter mShopOrderPresenter;
    private BuyFragment mBuyFragment;
    private String projectIds ="";

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shoping, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePresenter = new HomePresenter(null);
        mShopOrderPresenter = new ShopOrderPresenter(null);
        mShopCarListModels = new ArrayList<>();
        mRecyclerView = mFindViewUtils.findViewById(R.id.shopping_recyclerView);
        mShopCarRecyclerView = mFindViewUtils.findViewById(R.id.shop_car_list);
        mAppTitleBar.getTitle().setText("购物车");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("编辑");
        mAllCheckCheckBox = mFindViewUtils.findViewById(R.id.shop_car_all_check_checkbox);
        mAllCheckLayout = mFindViewUtils.findViewById(R.id.all_check_layout);
        mTotalPriceTxt1 = mFindViewUtils.findViewById(R.id.shop_car_total_price1);
        mTotalPriceTxt2 = mFindViewUtils.findViewById(R.id.shop_car_total_price2);
        mToPayTxt = mFindViewUtils.findViewById(R.id.shop_car_topay_tv);
    }

    @Override
    protected void setListener() {
        super.setListener();
        //购物车编辑与完成的逻辑
        mAppTitleBar.getAction().setOnClickListener(v -> {
            if (mAppTitleBar.getAction().getText().equals("编辑")) {
                mAppTitleBar.getAction().setText("完成");
                mTotalPriceTxt1.setVisibility(View.GONE);
                mTotalPriceTxt2.setVisibility(View.GONE);
                mToPayTxt.setText("删除");
                mToPayTxt.setOnClickListener(v1 -> {
                    if (!projectIds .isEmpty()) {
                        deleShopCarGoods(projectIds);
                    } else {
                        ApToast.showBottom("购物车已经为空！");
                    }
                });
            } else {
                mAppTitleBar.getAction().setText("编辑");
                mTotalPriceTxt1.setVisibility(View.VISIBLE);
                mTotalPriceTxt2.setVisibility(View.VISIBLE);
                mToPayTxt.setText(String.format("%s%s%s", "去结算（", allSelect(mShopCarListModels), ")"));
                if (Double.parseDouble(getTotalPrice(mShopCarListModels)) > 0) {
                    mToPayTxt.setBackground(getResources().getDrawable(R.drawable.addpartner_selector));
                } else {
                    mToPayTxt.setBackground(getResources().getDrawable(R.drawable.shop_car_delete_bg));
                }
                mToPayTxt.setOnClickListener(v1 -> {
                    if (mBuyFragment == null){
                        mBuyFragment =new BuyFragment();
                    }
                    Bundle bundle =new Bundle();
                    bundle.putString("project_id",projectIds);
                    ShowFragmentUtils.showFragment(getActivity(),
                            mBuyFragment.getClass(),
                            "buy_fg",
                            bundle,true);
                });
            }
        });

        //全选layout点击事件
        mAllCheckLayout.setOnClickListener(v -> {
            if (mAllCheckCheckBox.isChecked()) {
                mAllCheckCheckBox.setChecked(false);
//                    if (mAppTitleBar.getAction().getText().equals("编辑")) {
////                        mToPayTxt.setBackground(getResources().getDrawable(R.drawable.shop_car_delete_bg));
//                    }
                //只有当点击全不选时才执行
                // 解决点击取消选择店铺或商品时，全选按钮取消选择状态，不会不变成全不选
                for (int i = 0; i < mShopCarListModels.size(); i++) {
                    for (int j = 0; j < mShopCarListModels.get(i).getGoods().size(); j++) {
                        //选择店铺的商品
                        if (mShopCarListModels.get(i).getGoods().get(j).getStatus() == SELECT_TRUE) {
                            mShopCarListModels.get(i).getGoods().get(j).setStatus(SELECT_FALSE);
                        }
                    }
                }
            } else {
                mAllCheckCheckBox.setChecked(true);
                for (int i = 0; i < mShopCarListModels.size(); i++) {
                    for (int j = 0; j < mShopCarListModels.get(i).getGoods().size(); j++) {
                        //选择店铺的商品
                        if (!(mShopCarListModels.get(i).getGoods().get(j).getStatus() == SELECT_TRUE)) {
                            mShopCarListModels.get(i).getGoods().get(j).setStatus(SELECT_TRUE);
                            mShopCarListModels.get(i).getGoods().get(j).getProduct_id();
                        }
                    }
                }
            }
            UpdateRecyclerView();
            mTotalPriceTxt2.setText(getTotalPrice(mShopCarListModels));
        });
        mToPayTxt.setOnClickListener(v -> {
            //去支付 跳转确认订单页面
            if (mBuyFragment == null){
                mBuyFragment =new BuyFragment();
            }
            Bundle bundle =new Bundle();
            bundle.putString("project_id",projectIds);
            bundle.putString("num","0");
            ShowFragmentUtils.showFragment(getActivity(),
                    mBuyFragment.getClass(),
                    "buy_fg",
                    bundle,true);
        });

        mAllCheckCheckBox.setClickable(false);
    }

    private void deleShopCarGoods(String rec_id) {
        mHomePresenter.deleteShopCarGoods(rec_id, new IViewHome<String>() {
            @Override
            public void onSuccess(String data) {
                ApToast.showBottom(data);
                getShopCarData();
                hiddenLoading();
            }

            @Override
            public void onPhpFail(String var) {
                Toast.makeText(getContext(),var, Toast.LENGTH_SHORT).show();
                hiddenLoading();
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                Toast.makeText(getContext(),volleyError.toString(), Toast.LENGTH_SHORT).show();

                hiddenLoading();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        setHorizontalRecyclerView();
        setRecyclerView();
        getShopCarData();
        mTotalPriceTxt2.setText(getTotalPrice(mShopCarListModels));
    }

    private void getShopCarData() {
        mHomePresenter.getShopCarData(new IViewHome<List<ShopCarListModel>>() {
            @Override
            public void onSuccess(List<ShopCarListModel> data) {
                mShopCarListModels.addAll(data);
                mCarListAdapter.upData(data);
                hiddenLoading();
                //拼接project_id
                for (ShopCarListModel bean:data) {
                    for (int i = 0; i <bean.getGoods().size() ; i++) {
                        if (bean.getGoods().get(i).getStatus() == 0) {
                            if (i==0){
                                projectIds =bean.getGoods().get(i).getProduct_id();
                            }else {
                                projectIds =projectIds+","+bean.getGoods().get(i).getProduct_id();
                            }
                        }
                    }
                }
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                Toast.makeText(getContext(), var, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setRecyclerView() {
        manager = new LinearLayoutManager(getContext());
        mShopCarRecyclerView.setLayoutManager(manager);
        //优化性能
        mShopCarRecyclerView.setHasFixedSize(true);
        mCarListAdapter = new ShopCarListAdapter(getContext(), null);
        mShopCarRecyclerView.setAdapter(mCarListAdapter);

        mCarListAdapter.setCallBack(new ShopCarListAdapter.allCheck() {
            @Override
            public void OnItemCheckListener(int isSelected, int parentposition, int chaildposition) {
                //保存店铺商品点击状态
                mShopCarListModels.get(parentposition).getGoods().get(chaildposition).setStatus(isSelected);
                //通知全选CheckBox的选择状态
                int num = 0;
                int beenNum = 0;
                for (int i = 0; i < mShopCarListModels.size(); i++) {
                    num = num + allChildSelectNum(i);
                }
                for (int i = 0; i < mShopCarListModels.size(); i++) {
                    for (int j = 0; j < mShopCarListModels.get(i).getGoods().size(); j++) {
                        if (mShopCarListModels.get(i).getGoods().get(j).getProduct_number() != -1) {
                            beenNum++;
                        }
                    }
                }
                if (num == beenNum) {
                    mAllCheckCheckBox.setChecked(true);
                } else {
                    mAllCheckCheckBox.setChecked(false);
                }
//                UpdateRecyclerView();
                mTotalPriceTxt2.setText(getTotalPrice(mShopCarListModels));

            }

            @Override
            public void OnItemClickListener(View view, int parentposition, final int childposition) {
//                if (been.get(parentposition).getList().get(childposition).getStatus()==0) {
//                    been.get(parentposition).getList().get(childposition).setStatus(SELECT_FALSE);
//                } else {
//                    been.get(parentposition).getList().get(childposition).setStatus(SELECT_TRUE);
//
//                }
//                UpdateRecyclerView();
                mTotalPriceTxt2.setText(getTotalPrice(mShopCarListModels));
//                modifyNum(mShopCarListModels.get(parentposition).getGoods().get(childposition).getProduct_id(),
//                        String.format("%s",mShopCarListModels.get(parentposition).getGoods().get(childposition).getProduct_number()));
            }
        });
    }

    /*
       *解决Recycleyview刷新报错问题
      */
    private void UpdateRecyclerView() {
        Handler handler = new Handler();
        final Runnable r = () -> mCarListAdapter.notifyDataSetChanged();
        handler.post(r);
    }
    public void modifyNum(String rec_id,String num) {
        mHomePresenter.modifyGoodsNum(rec_id, num, new IViewHome<String>() {
            @Override
            public void onSuccess(String data) {
               ApToast.showBottom(data);
                hiddenLoading();
            }

            @Override
            public void onPhpFail(String var) {
                Toast.makeText(getContext(), var, Toast.LENGTH_SHORT).show();
                hiddenLoading();
            } 

            @Override
            public void onStart(String var) {
                  showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                hiddenLoading();
            }
        });
    }

    //全部选择商品的总价
    private String getTotalPrice(List<ShopCarListModel> shopCarBeanList) {
        double toatlPrice = 0;
        for (int i = 0; i < shopCarBeanList.size(); i++) {
            ShopCarListModel shopCarBean = shopCarBeanList.get(i);
            toatlPrice = toatlPrice + getItemTotalPrice(shopCarBean.getGoods());
        }
        return toatlPrice + "";
    }

    //一个店铺选择商品的总价计算
    private Double getItemTotalPrice(List<ShopCarListModel.GoodsBean> goodsBeen) {
        double num = 0;
        for (int j = 0; j < goodsBeen.size(); j++) {
            ShopCarListModel.GoodsBean bean = goodsBeen.get(j);
            //判断是否选中的商品
            if (bean.getStatus() == 1) {
                num += Double.valueOf(bean.getPrice()).doubleValue() * Double.valueOf(bean.getProduct_number()).doubleValue();
            }
    }
        return num;
    }

    //计算店铺的选择数量的总价
    private double allSelectPrice(List<ShopCarListModel> shopCarBeanList) {
        double sum = 0;
        for (int i = 0; i < shopCarBeanList.size(); i++) {
            sum = sum + allChildSelectPrice(i);
        }
        return sum;
    }

    //计算商品的选择总数
    private int allSelect(List<ShopCarListModel> shopCarBeanList) {
        int sum = 0;
        for (int i = 0; i < shopCarBeanList.size(); i++) {
            sum = sum + allChildSelectNum(i);
        }
        return sum;
    }

    private int allChildSelectNum(int position) {
        int sum = 0;
        for (int i = 0; i < mShopCarListModels.get(position).getGoods().size(); i++) {
            if (mShopCarListModels.get(position).getGoods().get(i).getStatus() == SELECT_TRUE) {
                sum++;
            }
        }
        return sum;
    }

    //计算每个店铺商品的选择数量的总价
    private double allChildSelectPrice(int position) {
        double sum = 0;
        for (int i = 0; i < mShopCarListModels.get(position).getGoods().size(); i++) {
            if (mShopCarListModels.get(position).getGoods().get(i).getStatus() == SELECT_TRUE) {
                sum = sum + Integer.valueOf(mShopCarListModels.get(position).getGoods().get(i).getPrice()).
                        doubleValue() * Integer.valueOf(mShopCarListModels.get(position).getGoods().get(i).getProduct_number()).doubleValue();
            }
        }
        return sum;
    }

    private void setHorizontalRecyclerView() {
        //设置横向滑动的recyclerView的adapter
        mRecyclerAdapter = new BaseRecyclerAdapter<HomeHListViewModel>(getContext(), getData()) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.home_hlist_item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, HomeHListViewModel item) {
                holder.setText(R.id.home_HListView_title, item.getTitle())
                        .setText(R.id.home_HListView_price, item.getPrice())
                        .setImageResource(R.id.home_HListView_image, item.getImg());
            }
        };
        mRecyclerView.setAdapter(mRecyclerAdapter);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置默认的动画 事实证明 看不出什么动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<HomeHListViewModel> getData() {
        List<HomeHListViewModel> list = new ArrayList<>();
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item1, "SH253微型断路器", "￥32,326"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item2, "SH253微型断路器", "￥326,123"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item3, "SH253微型断路器", "￥12,226"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item1, "SH253微型断路器", "￥326"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item2, "SH253微型断路器", "￥326,123"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item3, "SH253微型断路器", "￥12,226"));
        list.add(new HomeHListViewModel(R.mipmap.home_hlist_item1, "SH253微型断路器", "￥326"));
        return list;
    }
}
