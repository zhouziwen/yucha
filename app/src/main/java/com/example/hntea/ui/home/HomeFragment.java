package com.example.hnTea.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.mvpmodel.home.bean.HomeBean;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Base;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Category;
import com.example.hnTea.mvpmodel.home.bean.MainShop_ShangJia;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.adapter.HomeAdapter;
import com.example.hnTea.ui.home.shop.BusinessYellowPagesFragment;
import com.example.hnTea.ui.home.shop.ShopFragment;
import com.example.hnTea.ui.home.shop.ShoppingFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.toast.ApToast;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private HomePresenter mHomePresenter;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private ShoppingFragment mShoppingFragment;
    private HomeListDetail_Fg homeListDetail_fg;
    private BusinessYellowPagesFragment mBusinessYellowPagesFragment;
    private List<MainShop_Category> mMainShop_categories;
    private List<MainShop_ShangJia> mMainShop_shangJias;
    private ShopFragment mShopFragment;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePresenter = new HomePresenter(null);
        mRecyclerView = mFindViewUtils.findViewById(R.id.home_listView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.ycMainColor), Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            mHandler.post(() -> {
                request();
                setData();
                mSwipeRefreshLayout.setRefreshing(false);
            });

        });
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mAppTitleBar.getAction().setOnClickListener(v -> {
//            //跳转购物车
//            if (mShoppingFragment == null) {
//                mShoppingFragment = new ShoppingFragment();
//            }
//            ShowFragmentUtils.showFragment(getActivity(),
//                    mShoppingFragment.getClass(),
//                    FragmentTags.FRAGMENT_SHOPPING,
//                    null, true);
//        });
    }

    @Override
    protected void setData() {
        super.setData();
        request();
        List<HomeBean> list = new ArrayList<>();
        list.add(new HomeBean(1));
        list.add(new HomeBean(2));
        for (int i = 0; i < 8; i++) {
            list.add(new HomeBean(3));
        }
        mAdapter = new HomeAdapter(getContext(),
                list,
                R.layout.home_list_header,
                R.layout.home_list_h_recycler,
                R.layout.home_list_item
        ) {

            @Override
            public void bannerClick(int position) {
                //banner点击事件
                showAlertWithMsg("" + position);
            }

            @Override
            public void convert1(int position) {
                //快速入口的点击事件
                if (homeListDetail_fg == null) {
                    homeListDetail_fg = new HomeListDetail_Fg();
                }
                Bundle bundle = new Bundle();
                bundle.putString("fastEntrance", mMainShop_categories.get(position).getCat_id());
                bundle.putString("category", mMainShop_categories.get(position).getCat_name());
                if (homeListDetail_fg != null) {
                    ShowFragmentUtils.showFragment(getActivity(),
                            homeListDetail_fg.getClass(),
                            "",
                            bundle,
                            true);
                }
            }

            @Override
            public void convert2(int position, String good_id) {
                //热门商品 点击事件
                if (mShopFragment == null) {
                    mShopFragment = new ShopFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putString("shop_id", good_id);
                ShowFragmentUtils.showFragment(getActivity(),
                        mShopFragment.getClass(),
                        "",
                        bundle, true);
            }

            @Override
            public void convert3(int position) {
                //入住商家的点击事件
                if (mBusinessYellowPagesFragment == null) {
                    mBusinessYellowPagesFragment = new BusinessYellowPagesFragment();
                }
                Bundle bundle = new Bundle();
                String supplier_id = mMainShop_shangJias.get(position).getSupplier_id();
                bundle.putString("supplier_id", supplier_id);
                bundle.putString("Business_name",mMainShop_shangJias.get(position).getCompany_name());
                ShowFragmentUtils.showFragment(getActivity(),
                        mBusinessYellowPagesFragment.getClass(),
                        "",
                        bundle, true);
            }

            @Override
            public void convert4(String cat_name) {
                //产品分类的点击事件
                pushDetail("", cat_name);
            }

            @Override
            public void convert5(String cat_id, String cat_name) {
                pushDetail(cat_id, cat_name);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void request() {
        mHomePresenter.GetMainHomeData(new IViewHome<MainShop_Base>() {
            @Override
            public void onSuccess(MainShop_Base data) {
                hiddenLoading();
                mAdapter.upData(data);
                mMainShop_categories = data.getCategory();
                mMainShop_shangJias = data.getShnagJia();
            }

            @Override
            public void onPhpFail(String var) {
                ApToast.showBottom(var);
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                ApToast.showBottom(volleyError.getMessage());
            }
        });
    }

    private void pushDetail(String cat_id, String cat_name) {
        if (homeListDetail_fg == null) {
            homeListDetail_fg = new HomeListDetail_Fg();
        }
        Bundle bundle = new Bundle();
        bundle.putString("fastEntrance", cat_id);
        bundle.putString("category", cat_name);
        if (homeListDetail_fg != null) {
            ShowFragmentUtils.showFragment(getActivity(),
                    homeListDetail_fg.getClass(),
                    FragmentTags.FRAGMENT_HOME_LIST_DETAIL,
                    bundle, true);
        }
    }
}
