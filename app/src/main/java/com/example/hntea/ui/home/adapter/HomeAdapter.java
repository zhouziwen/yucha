package com.example.hnTea.ui.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.adapter.recyclerView.ItemViewDelegate;
import com.example.hnTea.adapter.recyclerView.MultiItemTypeAdapter;
import com.example.hnTea.mvpmodel.home.bean.HomeBean;
import com.example.hnTea.mvpmodel.home.bean.HomeNormalBean;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Banner;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Base;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Category;
import com.example.hnTea.mvpmodel.home.bean.MainShop_HotShop;
import com.example.hnTea.mvpmodel.home.bean.MainShop_Nav;
import com.example.hnTea.mvpmodel.home.bean.MainShop_ShangJia;
import com.example.hnTea.ui.custom.CustomGridView;
import com.example.hnTea.widget.ScrollViewForBanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2017/2/16.
 */

public abstract class HomeAdapter extends MultiItemTypeAdapter<HomeBean> {
    private Context mContext;
    private MainShop_Base mData;
    private CommonAdapter<String> mGridAdapter;
    private List<String> mImages = new ArrayList<>();
    private List<String> mStrings = new ArrayList<>();
    private List<HomeNormalBean> mNormalList = new ArrayList<>();
    private BaseRecyclerAdapter<MainShop_HotShop> mHotAdapter;
    private BaseRecyclerAdapter<MainShop_ShangJia> mShangJiaAdapter;

    private boolean isOnce = false;

    public HomeAdapter(final Context context,
                       List<HomeBean> data,
                       final int layoutHeader,
                       final int layoutHRecycler,
                       final int layoutNormal) {
        super(context, data);
        mContext = context;

        addItemViewDelegate(new ItemViewDelegate<HomeBean>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutHeader;
            }

            @Override
            public boolean isForViewType(HomeBean item, int position) {
                if (item.getType() == 1) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, HomeBean homeBean, int position) {
                //banner
                final ViewPager pager = holder.getView(R.id.home_listHeader_Pager);
                LinearLayout linearLayout = holder.getView(R.id.home_banner_tips);
                if (mData != null) {
                    if (!isOnce) {
                        List<String> urls = new ArrayList<>();
                        for (MainShop_Banner banner : mData.getBanners()) {
                            if (banner != null) {
                                urls.add(banner.getImage());
                            }
                        }
                        ScrollViewForBanner scrollViewForBanner =
                                new ScrollViewForBanner(pager, urls, linearLayout, mContext, false) {
                                    @Override
                                    public void imagesClick(int position) {
                                        bannerClick(position);
                                    }
                                };
                        scrollViewForBanner.adder();
                        //快速导航
                        CustomGridView recyclerView = holder.getView(R.id.home_listHeader_RecyclerView1);
                        CommonAdapter<String> mAdapter =
                                new CommonAdapter<String>(mContext, getString(), R.layout.home_h_recycler_item) {
                                    @Override
                                    protected void setListeners(BaseViewHolder holder, View view, int position) {
                                        holder.setOnclickListener(R.id.home_h_recycler_layout);
                                    }

                                    @Override
                                    protected void setViewDimen(View convertView) {
                                    }

                                    @Override
                                    protected void setViewData(int position, BaseViewHolder holder, String item) {
                                        ImageView img = holder.getView(R.id.home_h_recycler_image);
                                        Glide.with(context)
                                                .load(mImages.get(position))
                                                .placeholder(R.mipmap.pic_fenlei_moren)
                                                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                                .into(img);
                                        holder.setText(R.id.home_h_recycler_text, item);
                                    }

                                    @Override
                                    public void onClickBack(int position, View view, BaseViewHolder holder) {
                                        convert1(position);
                                    }
                                };
                        recyclerView.setAdapter(mAdapter);

                        isOnce = true;
                    }
                }

            }
        });

        addItemViewDelegate(new ItemViewDelegate<HomeBean>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutHRecycler;
            }

            @Override
            public boolean isForViewType(HomeBean item, int position) {
                if (item.getType() == 2) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, HomeBean homeBean, int position) {
                if (mData != null) {

                    //热门产品
                    RecyclerView mHotRecycler = holder.getView(R.id.home_listHeader_RecyclerView2);
                    mHotAdapter
                            = new BaseRecyclerAdapter<MainShop_HotShop>(mContext, mData.getHotShops()) {
                        @Override
                        public int getItemLayoutId(int viewType) {
                            return R.layout.home_hlist_item;
                        }

                        @Override
                        public void bindData(BaseRecyclerViewHolder holder, final int position, MainShop_HotShop item) {
                            holder.setText(R.id.home_HListView_title, item.getGoods_name())
                                    .setText(R.id.home_HListView_price, item.getShop_price());
                            ImageView img = holder.getView(R.id.home_HListView_image);
                            String url = item.getGoods_thumb();
                            Glide.with(context)
                                    .load(url)
                                    .placeholder(R.mipmap.pic_remen_moren)
                                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                    .into(img);
                            holder.setOnClickListener(R.id.home_HListView_Layout,
                                    v -> convert2(position, item.getGoods_id()));
                        }
                    };
                    mHotRecycler.setAdapter(mHotAdapter);
                    mHotRecycler.setLayoutManager(new LinearLayoutManager(mContext,
                            LinearLayoutManager.HORIZONTAL,
                            false));

                    //入住商家
                    RecyclerView mShopRecycler = holder.getView(R.id.home_listHeader_RecyclerView3);
                    mShangJiaAdapter =
                            new BaseRecyclerAdapter<MainShop_ShangJia>(mContext, mData.getShnagJia()) {
                                @Override
                                public int getItemLayoutId(int viewType) {
                                    return R.layout.home_hlist_item1;
                                }

                                @Override
                                public void bindData(BaseRecyclerViewHolder holder, final int position, MainShop_ShangJia item) {
                                    ImageView img = holder.getView(R.id.home_hlist_item1_image);
                                    String url = item.getLogo_img();
                                    Glide.with(context)
                                            .load(url)
                                            .placeholder(R.mipmap.pic_shangjia_moren)
                                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                                            .into(img);
                                    holder.setText(R.id.home_hlist_item1_text, item.getCompany_name());
                                    holder.setOnClickListener(R.id.home_HListView_Layout1,
                                            v -> convert3(position));
                                }
                            };
                    mShopRecycler.setAdapter(mShangJiaAdapter);
                    mShopRecycler.setLayoutManager(new LinearLayoutManager(mContext,
                            LinearLayoutManager.HORIZONTAL,
                            false));
                }
            }
        });

        addItemViewDelegate(new ItemViewDelegate<HomeBean>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutNormal;
            }

            @Override
            public boolean isForViewType(HomeBean item, int position) {
                if (item.getType() == 3) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, HomeBean homeBean, int positions) {
                //分类楼层
                if (mData == null) return;
                setData();
                ImageView img = holder.getView(R.id.home_list_item_header);
                Glide.with(context)
                        .load(mNormalList.get(positions - 2).getImage())
                        .placeholder(R.mipmap.pic_fenlei_moren)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(img);
                img.setOnClickListener(v -> {
                    convert5(mData.getCategory().get(positions-2).getCat_id(),
                            mData.getCategory().get(positions-2).getCat_name());
                });
                CustomGridView gridView = holder.getView(R.id.home_list_gridView);
                mGridAdapter = new CommonAdapter<String>(context,
                        mNormalList.get(positions-2).getStrings(),
                        R.layout.home_list_detailitem) {
                    @Override
                    protected void setListeners(BaseViewHolder holder, View view, int position) {
                        holder.setOnClickListener(R.id.home_list_detailItem_title);
                    }
                    @Override
                    protected void setViewDimen(View convertView) {
                    }
                    @Override
                    protected void setViewData(int position, BaseViewHolder holder, String item) {
                        holder.setText(R.id.home_list_detailItem_title,item);
                    }
                    @Override
                    public void onClickBack(int position, View view, BaseViewHolder holder) {
                        convert4(mNormalList.get(positions-2).getStrings().get(position));
                    }
                };
                gridView.setAdapter(mGridAdapter);
            }
        });
    }

    public abstract void bannerClick(int position);

    public abstract void convert1(int position);

    public abstract void convert2(int position, String good_id);

    public abstract void convert3(int position);

    public abstract void convert4(String cat_name);

    public abstract void convert5(String cat_id,String cat_name);

    public void upData(MainShop_Base Data) {
        if (Data != null) {
            mData = Data;
        }
        notifyDataSetChanged();
    }

    private void setData() {
        for (MainShop_Category cat : mData.getCategory()) {
            List<String> left = new ArrayList<>();
            for (int i = 0; i <cat.getChildrens().size() ; i++) {
                left.add(cat.getChildrens().get(i).getCat_name());
            }
            mNormalList.add(new HomeNormalBean(cat.getImage(), left));
        }
    }

    private List<String> getString() {
        if (mData != null) {
            if (mData.getNav() != null) {
                for (MainShop_Nav nav : mData.getNav()) {
                    mStrings.add(nav.getCat_name());
                    mImages.add(nav.getImage());
                }
            }
        }
        return mStrings;
    }
}
