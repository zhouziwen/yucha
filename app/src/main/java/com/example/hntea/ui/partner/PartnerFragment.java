package com.example.hnTea.ui.partner;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.pager.ViewPagerAdapterForView;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsAdapter;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsModel;
import com.example.hnTea.mvppresenter.partner.IViewPartner;
import com.example.hnTea.mvppresenter.partner.PartnerPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.HomeSearchFragment;
import com.example.hnTea.ui.price.SearchPriceFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class PartnerFragment extends BaseFragment {
    private PartnerPresenter mPartnerPresenter;
    private AddPartnerFragment mAddPartnerFragment;
    private BannerWebNewsFragment mBannerWebNewsFragment;
    private WebNewsFragment mWebNewsFragment;
    private LRecyclerView mRecyclerView;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private PartnerNewsAdapter mAdapter;

    private SearchPriceFragment mSearchPriceFragment;
    private TextView queryTv, mStateBar;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private ImageView[] tips;
    private int banners;
    private int page = 1;
    private int refreshInt;
    private boolean onlyBanner;
    //banner定时器
    private int i = 0;
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (i == banners - 1) {
                i = 0;
            } else {
                i++;
            }
            mViewPager.setCurrentItem(i);
            DefaultTips(i);
            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_partner, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mStateBar = mFindViewUtils.findViewById(R.id.state_bar);
        mStateBar.setHeight(getStatusHeight(getActivity()));
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getBack().setVisibility(View.GONE);
        mAppTitleBar.setTitle("合伙人");
        mWebNewsFragment = new WebNewsFragment();
        mPartnerPresenter = new PartnerPresenter(null);
        mBannerWebNewsFragment = new BannerWebNewsFragment();
        queryTv = mFindViewUtils.findViewById(R.id.query);
        mRecyclerView = mFindViewUtils.findViewById(R.id.partner_recyclerView);
    }

    @Override
    protected void setListener() {
        super.setListener();
//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //下拉刷新
        mRecyclerView.setOnRefreshListener(() -> setRecyclerViewData(1));
        //加载更多
        mRecyclerView.setOnLoadMoreListener(() -> {
            RecyclerViewStateUtils.setFooterViewState(getActivity(),
                    mRecyclerView, refreshInt,
                    LoadingFooter.State.Loading,
                    null);
            page++;
            setRecyclerViewData(page);
        });
    }

    @Override
    protected void setData() {
        super.setData();
        List<PartnerNewsModel> list = new ArrayList<>();
        setRecyclerViewBody(list);
        setRecyclerViewData(1);
    }

    private void setRecyclerViewBody(List<PartnerNewsModel> data) {
        mAdapter = new PartnerNewsAdapter(getContext(),
                R.layout.partner_list_header,
                R.layout.partner_list_item,
                R.layout.partner_list_item_img1,
                R.layout.partner_list_item_img3,
                data) {
            @Override
            protected void headerConvert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position) {
                //headerView
                mLinearLayout = holder.getView(R.id.home_banner_tips);
                mViewPager = holder.getView(R.id.partner_pagerr);
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        DefaultTips(position);
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
            protected void convert(BaseRecyclerViewHolder holder,
                                   final PartnerNewsModel t,
                                   int position) {
                //纯文字
                holder.setText(R.id.partner_listItem_title, t.getTitle());
                holder.setText(R.id.partner_listItem_time, t.getTime());
                holder.setOnClickListener(R.id.partner_listItem_Layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击跳转web
                        showWebWithUrl(t.getId());
                    }
                });
            }

            @Override
            protected void convert1(BaseRecyclerViewHolder holder,
                                    final PartnerNewsModel t,
                                    int position) {
                //一张图片
                holder.setText(R.id.partner_listItem_title, t.getTitle());
                holder.setText(R.id.partner_listItem_time, t.getTime());
                ImageView imageView = holder.getView(R.id.pic);
                Glide.with(getActivity())
                        .load(t.getList().get(0).get("imgUrl"))
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
                holder.setOnClickListener(R.id.partner_listItem_Layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击跳转web
                        showWebWithUrl(t.getId());
                    }
                });
            }

            @Override
            protected void convert2(BaseRecyclerViewHolder holder,
                                    final PartnerNewsModel t,
                                    int position) {
                //三张图片
                holder.setText(R.id.partner_listItem_title, t.getTitle());
                holder.setText(R.id.partner_listItem_time, t.getTime());
                ImageView imageView = holder.getView(R.id.pic);
                ImageView imageView1 = holder.getView(R.id.pic1);
                ImageView imageView2 = holder.getView(R.id.pic2);
                Glide.with(getActivity())
                        .load(t.getList().get(0).get("imgUrl"))
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
                Glide.with(getActivity())
                        .load(t.getList().get(1).get("imgUrl"))
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView1);
                Glide.with(getActivity())
                        .load(t.getList().get(2).get("imgUrl"))
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView2);
                holder.setOnClickListener(R.id.partner_listItem_Layout, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击跳转web
                        showWebWithUrl(t.getId());
                    }
                });
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mLRecyclerViewAdapter =
                new LRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (MyApplication.getInstance().is21Version()) {
            mRecyclerView.setNestedScrollingEnabled(true);
        }
    }

    private void setRecyclerViewData(final int page) {
        mPartnerPresenter.getPartnerNewsData(page, new IViewPartner<List<PartnerNewsModel>>() {
            @Override
            public void onSuccess(List<PartnerNewsModel> data) {
                hiddenLoading();
                mRecyclerView.refreshComplete();
                refreshInt = data.size();
                if (page == 1) {
                    List<PartnerNewsModel> list = new ArrayList<PartnerNewsModel>();
                    PartnerNewsModel model = new PartnerNewsModel("", "", "", null);
                    model.setType(100);
                    list.add(model);
                    list.addAll(data);
//                        PartnerNewsModel model = new PartnerNewsModel("", "", "", null);
//                        model.setType(100);
//                        List<PartnerNewsModel> newsLists = NewsListCache.getInstance(getContext()).getCacheById(page);
//                        for (PartnerNewsModel newsModel:newsLists
//                             ) {
//                            list.add(newsModel);
//                        }

//                  List<PartnerNewsList> newsLists= NewsListCache.getInstance(getContext()).getCacheById(page);
                    mAdapter.upData(list);
                    if (!onlyBanner) {
                        mHandler.postDelayed(() -> setBanner(), 1000);
                        onlyBanner = true;
                    }
                } else {
                    //加载更多
                    if (data.size() == 0) {
                        showAlertWithMsg("没有更多了");
                        mRecyclerView.refreshComplete();
                        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.TheEnd);
                    } else {
                        for (PartnerNewsModel model : data) {
                            mAdapter.append(model);
                        }
                        mRecyclerView.refreshComplete();
                        mLRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                if (!TextUtils.isEmpty(var)) {
                    showAlertWithMsg(var);
                }
            }

            @Override
            public void onStart(String var) {
                if (page == 1) {
                    showLoading();
                }
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
                showAlertWithMsg("请检查网络");
                mRecyclerView.refreshComplete();
            }
        });
    }

    private void setBanner() {
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            final ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            if (i == 0) {
                img.setImageResource(R.mipmap.newbanner3);
            } else if (i == 1) {
                img.setImageResource(R.mipmap.newbanner2);
            } else if (i == 2) {
                img.setImageResource(R.mipmap.newbanner1);
            } else {
                img.setImageResource(R.mipmap.partner_addpartner1);
            }
            views.add(img);
            img.setOnClickListener(v -> {
                switch (mViewPager.getCurrentItem()) {
                    case 1:
                        ShowFragmentUtils.showFragment(getActivity(),
                                mBannerWebNewsFragment.getClass(),
                                "bannerwebFragment", null, true);
                        break;
                    case 2:
                        ShowFragmentUtils.showFragment(getActivity(),
                                mBannerWebNewsFragment.getClass(),
                                "bannerwebFragment", null, true);
                        break;
                    case 0:
                        jumpAddPartner();
                        break;
                    case 3:
                        jumpAddPartner();
                        break;
                }
            });
        }
        //设置点点
        banners = views.size();
        tips = new ImageView[banners];
        for (int i = 0; i < banners; i++) {
            ImageView img = new ImageView(getContext());
            img.setLayoutParams(new LinearLayout.LayoutParams(6, 6));
            tips[i] = img;
            if (i == 0) {
                img.setBackgroundResource(R.drawable.tips_selector);
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
        ViewPagerAdapterForView mPagerAdapter = new ViewPagerAdapterForView(views);
        mViewPager.setAdapter(mPagerAdapter);
        addBannerTimer();
    }

    private void jumpAddPartner() {
        //banner点击事件
        if (mAddPartnerFragment == null) {
            mAddPartnerFragment = new AddPartnerFragment();
        }
        ShowFragmentUtils.showFragment(getActivity(),
                mAddPartnerFragment.getClass(),
                "add_partner", null, true);
    }

    //banner定时器
    private void addBannerTimer() {
        Timer mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 1000, 3000);
    }

    //改变点点的背景
    private void DefaultTips(int position) {
        for (int i = 0; i < banners; i++) {
            if (i == position) {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_selector);
            } else {
                ImageView img = tips[i];
                img.setBackgroundResource(R.drawable.tips_nomal);
            }
        }
    }

    private void showWebWithUrl(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("detail_id", id);
        ShowFragmentUtils.showFragment(getActivity(),
                mWebNewsFragment.getClass(),
                "webFragment", bundle, true);
    }

    private void pushSearchDetail() {
//        if (mHomeSearchFragment == null) {
//            mHomeSearchFragment = new HomeSearchFragment();
//        }
//        ShowFragmentUtils.showFragment(getActivity(),
//                mHomeSearchFragment.getClass(),
//                FragmentTags.FRAGMENT_HOME_SEARCH,
//                null, true);
        if (mSearchPriceFragment == null) {
            mSearchPriceFragment = new SearchPriceFragment();
        }
        ShowFragmentUtils.showFragment(getActivity(),
                mSearchPriceFragment.getClass(),
                "searchPrice",
                null, true);
    }
}
