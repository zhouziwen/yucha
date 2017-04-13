package com.example.hnTea.ui.price;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hnTea.adapter.pager.ViewPagerAdapterForFg;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.R;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

public class PriceFragment extends BaseFragment implements ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {
    private ViewPager mViewPager_X, mViewPager_B;
    private RadioGroup mGroup;
    private TextView mProjectText, mSingleText,mStateBar;
    private ImageView mImage_Project, mImage_Single;
    private ViewPagerAdapterForFg mAdapter, mAdapter1;
    private RelativeLayout mLayout_Project, mLayout_Single;
    private RadioButton mButton;
    private XPriceProject_Fg mX_P;
    private XPriceSingle_Fg mX_S;
    private BPriceProject_Fg mB_P;
    private BPriceSingle_Fg mB_S;
    private SearchPriceFragment mSearchPriceFragment;
    private FloatingActionButton mActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_price, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mStateBar =mFindViewUtils.findViewById(R.id.state_bar);
        mStateBar.setHeight(getStatusHeight(getActivity()));
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager_X = mFindViewUtils.findViewById(R.id.campus_viewpager);
        mViewPager_B = mFindViewUtils.findViewById(R.id.campus_viewpager1);
        mLayout_Project = mFindViewUtils.findViewById(R.id.price_layout_proj);
        mLayout_Single = mFindViewUtils.findViewById(R.id.price_layout_single);
        mImage_Project = mFindViewUtils.findViewById(R.id.price_image_proj);
        mImage_Single = mFindViewUtils.findViewById(R.id.price_image_single);
        mProjectText = mFindViewUtils.findViewById(R.id.price_top_project);
        mSingleText = mFindViewUtils.findViewById(R.id.price_top_single);
        mButton = mFindViewUtils.findViewById(R.id.X_tab);
        mGroup = mFindViewUtils.findViewById(R.id.price_radio_group);
        mActionButton = mFindViewUtils.findViewById(R.id.float_btn);
        if (mX_P == null) {
            mX_P = new XPriceProject_Fg();
        }
        if (mX_S == null) {
            mX_S = new XPriceSingle_Fg();
        }
        if (mB_P == null) {
            mB_P = new BPriceProject_Fg();
        }
        if (mB_S == null) {
            mB_S = new BPriceSingle_Fg();
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mGroup.setOnCheckedChangeListener(this);
        mLayout_Project.setOnClickListener(this);
        mLayout_Single.setOnClickListener(this);
        mViewPager_X.setOnPageChangeListener(this);
        mViewPager_B.setOnPageChangeListener(this);
        mActionButton.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
        mViewPager_X.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
        mViewPager_B.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);
        List<BaseFragment> list = new ArrayList<>();
        list.add(mX_S);
        list.add(mX_P);
        mAdapter = new ViewPagerAdapterForFg(getFragmentManager(), list);
        mViewPager_X.setAdapter(mAdapter);
        List<BaseFragment> list1 = new ArrayList<>();
        list1.add(mB_S);
        list1.add(mB_P);
        mAdapter1 = new ViewPagerAdapterForFg(getFragmentManager(), list1);
        mViewPager_B.setAdapter(mAdapter1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                changeTextColor(2);
                break;
            case 1:
                changeTextColor(1);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //顶部radioBtn的点击事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.X_tab:
                changeTextTxt(1);
                break;
            case R.id.B_tab:
                changeTextTxt(2);
                break;
        }
        mViewPager_X.setVisibility(mButton.isChecked() ? View.VISIBLE : View.GONE);
        mViewPager_B.setVisibility(mButton.isChecked() ? View.GONE : View.VISIBLE);
    }

    //text的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.price_layout_proj:
                changeTextColor(1);
                mViewPager_X.setCurrentItem(1);
                mViewPager_B.setCurrentItem(1);
                break;
            case R.id.price_layout_single:
                changeTextColor(2);
                mViewPager_X.setCurrentItem(0);
                mViewPager_B.setCurrentItem(0);
                break;
            case R.id.float_btn:
                //点击跳转询报价搜索
                if (mSearchPriceFragment == null) {
                    mSearchPriceFragment = new SearchPriceFragment();
                }
                ShowFragmentUtils.showFragment(getActivity(),
                        mSearchPriceFragment.getClass(),
                        "searchPrice",
                        null, true);
                break;
        }
    }

    private void changeTextColor(int position) {
        if (position == 1) {
            mProjectText.setTextColor(getResources().getColor(R.color.tabBarColor));
            mSingleText.setTextColor(getResources().getColor(R.color.price_textColor_nomal));
            mImage_Project.setImageResource(R.mipmap.price_proj_selector);
            mImage_Single.setImageResource(R.mipmap.price_single_nomal);
        } else {
            mProjectText.setTextColor(getResources().getColor(R.color.price_textColor_nomal));
            mSingleText.setTextColor(getResources().getColor(R.color.tabBarColor));
            mImage_Project.setImageResource(R.mipmap.price_proj_nomal);
            mImage_Single.setImageResource(R.mipmap.price_single_selector);
        }
    }

    private void changeTextTxt(int position) {
        if (position == 1) {
            mProjectText.setText("项目询价");
            mSingleText.setText("产品询价");
        } else {
            mProjectText.setText("项目报价");
            mSingleText.setText("产品报价");
        }
    }
}
