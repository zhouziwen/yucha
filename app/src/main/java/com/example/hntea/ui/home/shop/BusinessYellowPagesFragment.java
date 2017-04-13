package com.example.hnTea.ui.home.shop;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hnTea.R;
import com.example.hnTea.adapter.pager.TablayoutPagerAdapter;
import com.example.hnTea.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessYellowPagesFragment extends BaseFragment {
    @BindView(R.id.yellow_page_tab_layout)
    TabLayout mYellowPageTabLayout;
    @BindView(R.id.container)
    ViewPager mContainer;
    private TablayoutPagerAdapter mPagerAdapterForFg;
    private List<String> mTitleList;
    private BusinessMainFragment mBusinessAllGoodsFragment;
    private BusinessHomeGoodsFragment mBusinessHomePageFragment;
    private BusinessContactStyleFragment mBusinessContactStyleFragment;
    public BusinessYellowPagesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_yellow_pages, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText(getArguments().getString("Business_name")+"官方旗舰店");
        mTitleList = new ArrayList<>();
        mTitleList.add("公司首页");
        mTitleList.add("全部商品");
        mTitleList.add("联系方式");
        mYellowPageTabLayout.setTabMode(MODE_FIXED);
        mYellowPageTabLayout.addTab(mYellowPageTabLayout.newTab().setText(mTitleList.get(0)));
        mYellowPageTabLayout.addTab(mYellowPageTabLayout.newTab().setText(mTitleList.get(1)));
        mYellowPageTabLayout.addTab(mYellowPageTabLayout.newTab().setText(mTitleList.get(2)));
        setPagerAdapter();
        mYellowPageTabLayout.setupWithViewPager(mContainer);
    }

    private void setPagerAdapter() {
        String supplier_id =getArguments().getString("supplier_id");
        List<BaseFragment> baseFragments = new ArrayList<>();
        if (mBusinessAllGoodsFragment == null) {
            mBusinessAllGoodsFragment=new BusinessMainFragment();
        }
        mBusinessAllGoodsFragment.setSupplier_id(supplier_id);
        baseFragments.add(mBusinessAllGoodsFragment);
        if (mBusinessHomePageFragment == null) {
            mBusinessHomePageFragment=new BusinessHomeGoodsFragment();
        }
        mBusinessHomePageFragment.setSupplier_id(supplier_id);
        baseFragments.add(mBusinessHomePageFragment);
        if (mBusinessContactStyleFragment == null) {
            mBusinessContactStyleFragment=new BusinessContactStyleFragment();
        }
        mBusinessContactStyleFragment.setSupplier_id(supplier_id);
        baseFragments.add(mBusinessContactStyleFragment);
        mPagerAdapterForFg=new TablayoutPagerAdapter(getChildFragmentManager(),baseFragments ,mTitleList);
        mContainer.setAdapter(mPagerAdapterForFg);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
    }
}
