package com.example.hnTea.ui.home.shop;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.CampusPagerAdapter;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Base;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.login.LoginActivity;
import com.example.hnTea.widget.BaseDialog;
import com.example.hnTea.widget.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends BaseFragment implements View.OnClickListener {
    private HomePresenter mHomePresenter;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private TextView mBack;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private List<String> mStrings = new ArrayList<>();
    private ImageView mCollection, mService;
    private TextView mToBuy, mAddShop;
    private ShopMsgFragment mShopMsgFragment;
    private ShopDetailFragment mShopDetailFragment;
    private String mShop_id ="";
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_detail, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePresenter = new HomePresenter(null);
        mPagerSlidingTabStrip = mFindViewUtils.findViewById(R.id.shopDetail_topLayout);
        mViewPager = mFindViewUtils.findViewById(R.id.shopDetail_pager);
        mBack = mFindViewUtils.findViewById(R.id.app_title_back);
        mToBuy = mFindViewUtils.findViewById(R.id.shop_toBuy);
        mAddShop = mFindViewUtils.findViewById(R.id.shop_addShop);
        mCollection = mFindViewUtils.findViewById(R.id.shop_collection);
        mService = mFindViewUtils.findViewById(R.id.shop_service);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mToBuy.setOnClickListener(this);
        mAddShop.setOnClickListener(this);
        mCollection.setOnClickListener(this);
        mService.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
        mStrings.add("商品信息");
        mStrings.add("商品详情");
        if (mShopMsgFragment == null) {
            mShopMsgFragment = new ShopMsgFragment();
        }
        if (mShopDetailFragment == null) {
            mShopDetailFragment = new ShopDetailFragment();
        }
        String shop_id ="";
        shop_id =getArguments().getString("shop_id");
        toRequest(shop_id);
        mShop_id =shop_id;
        //保存shop_id 浏览记录
        if (PreManager.instance().getString("goodsId").equals("")){
            PreManager.instance().saveString("goodId",shop_id);
        }else {
            String goodsId =PreManager.instance().getString("goodsId");
            String save =goodsId+","+shop_id;
            PreManager.instance().saveString("goodsId",save);
        }
        mFragments.add(mShopMsgFragment);
        mFragments.add(mShopDetailFragment);
        mViewPager.setAdapter(new CampusPagerAdapter(getChildFragmentManager(), mStrings, mFragments));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setFadeEnabled(true);
        mViewPager.setCurrentItem(0);
    }

    //网络请求
    private void toRequest(String shop_id) {
        String token = "";
        if (!MyApplication.getInstance().isLogin()) {
            token = MyApplication.getUserToken();
        }
        mHomePresenter.getShopDetailData(shop_id, token, new IViewHome<ShopDetail_Base>() {
            @Override
            public void onSuccess(ShopDetail_Base data) {
                hiddenLoading();
                mShopMsgFragment.dataHasChanged(data);
                mShopDetailFragment.getDetailHtml(data.getInfo().getGoods_desc());
                if (!MyApplication.getInstance().isLogin()) {
                    //如果用户登录则判断 是否收藏了该商品
                    if (data.getCollect_state_exists().equals("1")) {
                        //用户收藏该商品
                        mCollection.setImageResource(R.mipmap.shopdetail_collect_pressed_2);
                    }
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

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.shop_toBuy:
                //去购买结算
                if (isLogin()){
                    mShopMsgFragment.showPopWindow(2);
                }
                break;
            case R.id.shop_addShop:
                //加入购物车
                if (isLogin()){
                    mShopMsgFragment.showPopWindow(1);
                }
                break;
            case R.id.shop_collection:
                //收藏
                if (isLogin()) {
                    mHomePresenter.UserCollection(mShop_id, MyApplication.getUserToken(), new IViewHome<String>() {
                        @Override
                        public void onSuccess(String data) {
                            hiddenLoading();
                            if (data.equals("1")) {
                                //收藏
                                showAlertWithMsg("已收藏");
                                mCollection.setImageResource(R.mipmap.shopdetail_collect_pressed_2);
                            } else {
                                showAlertWithMsg("已取消收藏");
                                mCollection.setImageResource(R.mipmap.shopdetail_collect_2);
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

                break;
            case R.id.shop_service:
                //客服
                showDialogWithMsg("是否拨打客服电话4006-831536？");
                mDialog.setLeftButtonListener(() -> mDialog.dismiss());
                mDialog.setRightButtonListener(new BaseDialog.RightListener() {
                    @Override
                    public void onRightListener() {
                        mDialog.dismiss();
                        String number = "4006-831536";
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                        startActivity(intent);
                    }
                });
                break;
            case R.id.app_title_back:
                //返回键
                popSelf();
                break;
        }
    }

    private boolean isLogin(){
        if (MyApplication.getInstance().isLogin()) {
            //没有登录 提示去登录
            showDialogWithMsg("当前未登录，是否前往登录？");
            mDialog.setLeftButtonListener(() -> mDialog.dismiss());
            mDialog.setRightButtonListener(() -> {
                mDialog.dismiss();
                //跳转登录
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });
            return false;
        }else {
            return true;
        }
    }
}