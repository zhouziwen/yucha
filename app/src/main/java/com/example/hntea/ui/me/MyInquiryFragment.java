package com.example.hnTea.ui.me;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hnTea.R;
import com.example.hnTea.adapter.CampusPagerAdapter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.me.MyInquiry.MeBPriceFragment;
import com.example.hnTea.ui.me.MyInquiry.MeJPriceFragment;
import com.example.hnTea.ui.me.MyInquiry.MeXPriceFragment;
import com.example.hnTea.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInquiryFragment extends BaseFragment {
//  可以添加tab内容 list的adapter
    private CampusPagerAdapter mAdapter, mAdapter1;
    private PagerSlidingTabStrip mPagerSlidingTabStrip, mPagerSlidingTabStrip1;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager, mViewPager1;
    private RadioButton mButton;
    private ImageView mImageView;
    private MeXPriceFragment meXPriceFragment;
    private MeJPriceFragment meJPriceFragment;
    private MeBPriceFragment meBPriceFragment;
    private MeXPriceFragment meXPriceFragment1;
    private MeJPriceFragment meJPriceFragment1;
    private MeBPriceFragment meBPriceFragment1;

    private List<String> mStrings = new ArrayList<>();

    public MyInquiryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_inquiry, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        mPagerSlidingTabStrip = mFindViewUtils.findViewById(R.id.myOrder_sub_tab_strip);
        mPagerSlidingTabStrip1 = mFindViewUtils.findViewById(R.id.myOrder_sub_tab_strip1);
        mRadioGroup = mFindViewUtils.findViewById(R.id.me_inquiry_radio_group);
        mButton = mFindViewUtils.findViewById(R.id.product_RB);
        mImageView = mFindViewUtils.findViewById(R.id.back_img);
        mViewPager = mFindViewUtils.findViewById(R.id.me_inquiry_viewpager);
        mViewPager1 = mFindViewUtils.findViewById(R.id.me_inquiry_viewpager1);
        mStrings.add("询价单");
        mStrings.add("报价单");
        mStrings.add("竞价单");
        //3个fragment每个new两个实例  分别对应两个viewpager
        if (meXPriceFragment == null) {
            meXPriceFragment = new MeXPriceFragment();
        }
        if (meJPriceFragment == null) {
            meJPriceFragment = new MeJPriceFragment();
        }
        if (meBPriceFragment == null) {
            meBPriceFragment = new MeBPriceFragment();
        }
        if (meXPriceFragment1 == null) {
            meXPriceFragment1 = new MeXPriceFragment();
        }
        if (meJPriceFragment1 == null) {
            meJPriceFragment1 = new MeJPriceFragment();
        }
        if (meBPriceFragment1 == null) {
            meBPriceFragment1 = new MeBPriceFragment();
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.product_RB:
                        // 当选择产品信息  第一个viewpager  visible,第一个pagerTab  visible
                        mViewPager.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
                        mViewPager1.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);
                        mPagerSlidingTabStrip.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
                        mPagerSlidingTabStrip1.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);

                        break;
                    case R.id.project_RB:
                        // 当选择项目信息  第2个viewpager  visible,第2个pagerTab  visible
                        mViewPager.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
                        mViewPager1.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);
                        mPagerSlidingTabStrip.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
                        mPagerSlidingTabStrip1.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);
                        break;
                }
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSelf();
            }
        });

    }

    @Override
    protected void setData() {
        super.setData();

        Bundle bundle = new Bundle();
        bundle.putInt("data", 0);
        meXPriceFragment.setArguments(bundle);
        meBPriceFragment.setArguments(bundle);
        meJPriceFragment.setArguments(bundle);
        List<BaseFragment> list = new ArrayList<>();

        meXPriceFragment.setArguments(bundle);
        meJPriceFragment.setArguments(bundle);
        meBPriceFragment.setArguments(bundle);

        list.add(meXPriceFragment);
        list.add(meBPriceFragment);
        list.add(meJPriceFragment);
        mAdapter = new CampusPagerAdapter(getChildFragmentManager(), mStrings, list);
        mViewPager.setAdapter(mAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setFadeEnabled(true);

        Bundle bundlea = new Bundle();
        bundlea.putInt("data", 1);
        meXPriceFragment1.setArguments(bundlea);
        meBPriceFragment1.setArguments(bundlea);
        meJPriceFragment1.setArguments(bundlea);

        List<BaseFragment> list1 = new ArrayList<>();
        list1.add(meXPriceFragment1);
        list1.add(meBPriceFragment1);
        list1.add(meJPriceFragment1);
        mAdapter1 = new CampusPagerAdapter(getChildFragmentManager(), mStrings, list1);
        mViewPager1.setAdapter(mAdapter1);
        mPagerSlidingTabStrip1.setViewPager(mViewPager1);
        mPagerSlidingTabStrip1.setFadeEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

}
