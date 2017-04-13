package com.example.hnTea.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hnTea.R;
import com.example.hnTea.adapter.pager.ViewPagerAdapterForView;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class UserGuideActivity extends BaseActivity {
    private ViewPager mPager;
    private ViewPagerAdapterForView mPagerAdapter;
    private LinearLayout mLinearLayout;
    private ImageView[] tips;
    private int banners;
    private Handler mHandler =new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_user_guide);
    }

    @Override
    protected void initView() {
        super.initView();
        mLinearLayout =mFindViewUtils.findViewById(R.id.home_banner_tips);
        mPager = (ViewPager) findViewById(R.id.userGuide_pager);
        //是否第一次启动 缓存到本地
        PreManager.instance().saveBool("once",true);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                DefaultTips(position);
                if (position==2){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent =new Intent();
                            intent.setClass(UserGuideActivity.this,SplashActivity.class);
                            startActivity(intent);
                        }
                    },1000);
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        List<View> views =new ArrayList<>();
        int[] ints ={R.mipmap.pic_hello11,
                     R.mipmap.pic_hello22,
                     R.mipmap.pic_hello33};
        for (int i = 0; i <ints.length ; i++) {
            ImageView img =new ImageView(this);
            img.setImageResource(ints[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(img);
        }
        mPagerAdapter =new ViewPagerAdapterForView(views);
        mPager.setAdapter(mPagerAdapter);
        setBanner();
    }

    private void setBanner() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView img = new ImageView(this);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //banner点击事件
                }
            });
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            if (i == 0) {
                img.setImageResource(R.mipmap.newbanner1);
            } else if (i == 1) {
                img.setImageResource(R.mipmap.newbanner2);
            } else if (i == 2) {
                img.setImageResource(R.mipmap.newbanner3);
            } else {
                img.setImageResource(R.mipmap.partner_addpartner1);
            }
            views.add(img);
        }
        //设置点点
        banners = views.size();
        tips = new ImageView[banners];
        for (int i = 0; i < banners; i++) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new LinearLayout.LayoutParams(6, 6));
            tips[i] = img;
            if (i == 0) {
                img.setBackgroundResource(R.drawable.tips_selector_gray);
            } else {
                img.setBackgroundResource(R.drawable.tips_nomal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 5;
            params.rightMargin = 5;
            params.height = 20;
            params.width = 20;
            mLinearLayout.addView(img, params);
        }
    }
    //改变点点的背景
    private void DefaultTips(int position) {
        for (int i = 0; i < banners; i++) {
            if (i == position) {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_selector_gray);
            } else {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_nomal);
            }
        }
    }
}
