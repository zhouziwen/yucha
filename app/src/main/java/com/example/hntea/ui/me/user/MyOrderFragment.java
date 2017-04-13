package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hnTea.R;
import com.example.hnTea.adapter.CampusPagerAdapter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.me.order.AllOrderFragment;
import com.example.hnTea.ui.me.order.AlsoFinishFragment;
import com.example.hnTea.ui.me.order.AlsoSendSthFragment;
import com.example.hnTea.ui.me.order.WaitPayFragment;
import com.example.hnTea.ui.me.order.WaitSendSthFragment;
import com.example.hnTea.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderFragment extends BaseFragment {
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private List<String> mStrings = new ArrayList<>();


    private ViewPager mPager;
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("我的订单");
        mPagerSlidingTabStrip =mFindViewUtils.findViewById(R.id.myOrder_sub_tab_strip);
        mPager =mFindViewUtils.findViewById(R.id.myOrder_pager);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mStrings.add("全部");
        mStrings.add("待付款");
        mStrings.add("待发货");
        mStrings.add("已发货");
        mStrings.add("已完成");
        mFragments.add(new AllOrderFragment());
        mFragments.add(new WaitPayFragment());
        mFragments.add(new WaitSendSthFragment());
        mFragments.add(new AlsoSendSthFragment());
        mFragments.add(new AlsoFinishFragment());
        mPager.setAdapter(new CampusPagerAdapter(getChildFragmentManager(), mStrings, mFragments));
        mPagerSlidingTabStrip.setViewPager(mPager);
        mPagerSlidingTabStrip.setFadeEnabled(true);
        mPager.setCurrentItem(0);
    }

}
