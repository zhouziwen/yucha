package com.example.hnTea.ui.home;


import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.apcontains.OnPopWinDisMisBack;
import com.example.hnTea.mvpmodel.home.bean.CategoryGoodsList;
import com.example.hnTea.mvpmodel.home.bean.ProduceSearchModel;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.shop.ShopFragment;
import com.example.hnTea.utils.ActionSheet;
import com.example.hnTea.utils.DisPlayUtils;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeListDetail_Fg extends BaseFragment implements OnPopWinDisMisBack {
    private HomePresenter mHomePresenter;
    private ListView mListView, mMoreList;
    private CommonAdapter<CategoryGoodsList.Goods> mListAdapter;
    private CommonAdapter<CategoryGoodsList.Category> mCategoryCommonAdapter;
    private CommonAdapter<CategoryGoodsList.SonCategory> mSonCategoryCommonAdapter;
    private CommonAdapter<String> mPop23Adapter;
    private View parentView;
    private TextView mSale, mPrice, mLocation;
    private ActionSheet mActionSheet;
    private ActionSheet mActionSheetMore;
    private EditText searchEdTx;
    private TextView backTv;
    //    item点击背景颜色
    private List<Boolean> mBooleens = new ArrayList<>();
    private ShopFragment mShopFragment;
    private List<CategoryGoodsList.Category> mCategories;
    private List<CategoryGoodsList.Goods> mGoodses;
    private String cId, cateGory;
    private String styleId = "";
    private CategoryGoodsList mData;
    //存储 左边list选中的item
    private int leftSelector = 0;
    //用来保存 每个搜索过后的值 方便排序时掉用
    private String selectorProject = "";

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_list_detail__fg, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePresenter = new HomePresenter(null);
        cId = getArguments().getString("fastEntrance");
        cateGory = getArguments().getString("category");
        if (cId.equals("")) {
            search(cateGory);
        }
        parentView = mFindViewUtils.findViewById(R.id.homeListDetail_view);
        mListView = mFindViewUtils.findViewById(R.id.homeListDetail_ListView);
        mSale = mFindViewUtils.findViewById(R.id.homeListDetail_sale);
        mPrice = mFindViewUtils.findViewById(R.id.homeListDetail_price);
        mLocation = mFindViewUtils.findViewById(R.id.homeListDetail_location);
        searchEdTx = mFindViewUtils.findViewById(R.id.query);
        backTv = mFindViewUtils.findViewById(R.id.title_back);
        Bundle bundle = getArguments();
        String title = (String) bundle.get("title");
        searchEdTx.setText(title);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSale.setOnClickListener(v -> {
            //销量
            showActionSheetMore();
            changeTextColor(mSale, true);
        });
        mPrice.setOnClickListener(v -> {
            //价钱
            List<String> list1 = new ArrayList<>();
            list1.add("销量从低到高");
            list1.add("销量从高到低");
            showActionSheet(list1, 2);
            changeTextColor(mPrice, true);
        });
        mLocation.setOnClickListener(v -> {
            //地区
            List<String> list2 = new ArrayList<>();
            list2.add("价格从低到高");
            list2.add("价格从高到低");
            showActionSheet(list2, 3);
            changeTextColor(mLocation, true);
        });
        backTv.setOnClickListener(v -> {
            //返回键
            popSelf();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setData() {
        mSale.setText(cateGory);
        super.setData();
        //详情页下面List
        setListViewData("all", styleId == null ? "" : styleId, cId, "", "1");
        setCategoryGoodsListData();
        searchEdTx.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search("");
                selectorProject = getText(searchEdTx);
                styleId = "";
            }
            return false;
        });
    }

    private void search(String text) {
        if (text.length() == 0) {
            text = getText(searchEdTx);
        }
        if (styleId.length() != 0) {
            text = selectorProject;
        }
        mHomePresenter.getProductSearch(text,
                styleId == null ? "" : styleId, "1",
                new IViewHome<ProduceSearchModel>() {
                    @Override
                    public void onSuccess(ProduceSearchModel data) {
                        mListAdapter.update(data.getGoodsList());
                    }

                    @Override
                    public void onPhpFail(String var) {

                    }

                    @Override
                    public void onStart(String var) {

                    }

                    @Override
                    public void onFail(VolleyError volleyError) {

                    }
                });
    }

    private void setCategoryGoodsListData() {
        mListAdapter = new CommonAdapter<CategoryGoodsList.Goods>(getContext(),
                null,
                R.layout.home_listdetail_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.home_listdetail_list_item_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, CategoryGoodsList.Goods item) {
                holder.setText(R.id.home_listDetail_listItem_title, item.getGoods_name())
                        .setText(R.id.home_listDetail_listItem_type, item.getCompany_name())
                        .setText(R.id.home_listDetail_listItem_location, "已销" + item.getSell_num() + "件")
                        .setText(R.id.home_listDetail_listItem_price, item.getShop_price());
                ImageView imageView = holder.getView(R.id.home_listDetail_listItem_image);
                Glide.with(getContext())
                        .load(item.getGoods_thumb())
                        .placeholder(R.mipmap.pic_banner_moren)
                        .into(imageView);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                if (mShopFragment == null) {
                    mShopFragment = new ShopFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putString("shop_id", mListAdapter.getData().get(position).getGoods_id());
                ShowFragmentUtils.showFragment(getActivity(),
                        mShopFragment.getClass(),
                        FragmentTags.FRAGMENT_SHOP_DETAIL,
                        bundle, true);
            }
        };
        mListView.setAdapter(mListAdapter);
    }

    private void setListViewData(String scId, String styleId, String cId, String brandId, String page) {
        mHomePresenter.getCategoryGoodsList(scId, styleId, cId, brandId, page, new IViewHome<CategoryGoodsList>() {
            @Override
            public void onSuccess(CategoryGoodsList data) {
                mData = data;
                if (!cId.equals("")){
                    mListAdapter.update(mData.getGoodsList());
                }
                mCategories = mData.getCategoryList();
                mGoodses = mData.getGoodsList();
            }

            @Override
            public void onPhpFail(String var) {

            }

            @Override
            public void onStart(String var) {

            }

            @Override
            public void onFail(VolleyError volleyError) {

            }
        });
    }

    //产品分类
    private void showActionSheetMore() {
        mActionSheetMore = new ActionSheet(getContext(),
                R.layout.action_morelist,
                DisPlayUtils.getWidthPx(),
                DisPlayUtils.getHeightPx(),
                this
        ) {
            @Override
            public void setData(View view) {
                ListView typeList = (ListView) view.findViewById(R.id.moreList_typeList);
                mMoreList = (ListView) view.findViewById(R.id.moreList_typeList_more);
                if (mData == null) {
                    showAlertWithMsg("请检查网络");
                    return;
                }
                setCateAdapter(typeList, mCategories);
                setSonCateAdapter(mMoreList, mData.getCategoryList().get(leftSelector).getSon_cat());
                ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
                DismissListener(bg, 1);
            }
        };
        for (int i = 0; i < mCategoryCommonAdapter.getData().size(); i++) {
            mBooleens.add(false);
        }
        mActionSheetMore.showAsParentView(parentView);
    }

    //销量排序 和 价格排序 通用一个actionSheet
    private void showActionSheet(final List<String> strings, final int who) {
        mActionSheet = new ActionSheet(
                getContext(),
                R.layout.action_list,
                DisPlayUtils.getWidthPx(),
                DisPlayUtils.getHeightPx(),
                this) {
            @Override
            public void setData(View view) {
                ListView listView = (ListView) view.findViewById(R.id.action_list_listView);
                setPop23Adapter(listView, strings, who);
                ImageView bg = (ImageView) view.findViewById(R.id.action_list_bgView);
                DismissListener(bg, who);
            }
        };
        mActionSheet.showAsParentView(parentView);
    }

    //dismiss的Listener
    private void DismissListener(ImageView imageView, final int who) {
        imageView.setOnClickListener(v -> {
            switch (who) {
                case 1:
                    mActionSheetMore.dismissWindow();
                    changeTextColor(mSale, false);
                    break;
                case 2:
                    mActionSheet.dismissWindow();
                    changeTextColor(mPrice, false);
                    break;
                case 3:
                    mActionSheet.dismissWindow();
                    changeTextColor(mLocation, false);
                    break;
            }
        });
    }

    //价格和销量排序的listAapter
    private void setPop23Adapter(ListView listView, final List<String> strings, final int who) {
        mPop23Adapter = new CommonAdapter<String>(getContext(), strings, R.layout.action_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setLineLayoutListener(R.id.action_list_item_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, String item) {
                holder.setText(R.id.action_list_item_tv, item);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                switch (who) {
                    case 2:
                        //销量排序
                        if (position == 0) {
                            styleId = "3";
                            search("");
                        } else {
                            styleId = "4";
                            search("");
                        }
                        mActionSheet.dismissWindow();
                        break;
                    case 3:
                        //价格排序
                        if (position == 0) {
                            styleId = "1";
                            search("");
                        } else {
                            styleId = "2";
                            search("");
                        }
                        mActionSheet.dismissWindow();
                        break;
                }
            }
        };
        listView.setAdapter(mPop23Adapter);
    }

    //这里让界面里的产品分类的adapter
    private void setCateAdapter(final ListView listView,
                                final List<CategoryGoodsList.Category> categoryList) {

        mCategoryCommonAdapter = new CommonAdapter<CategoryGoodsList.Category>(getContext(), categoryList, R.layout.action_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setLineLayoutListener(R.id.action_list_item_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, CategoryGoodsList.Category item) {
                holder.setText(R.id.action_list_item_tv, item.getCat_name());
                TextView tv = holder.getView(R.id.action_list_item_tv);
                if (position == leftSelector) {
                    tv.setBackgroundColor(getResources().getColor(R.color.pop_list_bg));
                } else {
                    tv.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                setSonCateAdapter(mMoreList, categoryList.get(position).getSon_cat());
                leftSelector = position;
                mSale.setText(categoryList.get(position).getCat_name());
                mCategoryCommonAdapter.notifyDataSetChanged();
                search(categoryList.get(position).getCat_name());
                selectorProject = categoryList.get(position).getCat_name();
            }
        };
        listView.setAdapter(mCategoryCommonAdapter);
    }

    //产品分类的子分类的Adapter
    private void setSonCateAdapter(ListView listView,
                                   final List<CategoryGoodsList.SonCategory> sonCategoryList
    ) {
        mSonCategoryCommonAdapter = new CommonAdapter<CategoryGoodsList.SonCategory>(getContext(), sonCategoryList, R.layout.action_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setLineLayoutListener(R.id.action_list_item_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, CategoryGoodsList.SonCategory item) {
                holder.setText(R.id.action_list_item_tv, item.getSon_cat_name());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                mActionSheetMore.dismissWindow();
                search(sonCategoryList.get(position).getSon_cat_name());
                selectorProject = sonCategoryList.get(position).getSon_cat_name();
            }
        };
        listView.setAdapter(mSonCategoryCommonAdapter);
    }

    //改变top文字的颜色
    private void changeTextColor(TextView textView, boolean isRed) {
        if (isRed) {
            textView.setTextColor(getResources().getColor(R.color.price_viewColor));
            setTvDrawable(textView, R.mipmap.price_up_pressed);
        } else {
            textView.setTextColor(getResources().getColor(R.color.price_textColor_pressed));
            setTvDrawable(textView, R.mipmap.price_down_normal);
        }
    }

    public void setTvDrawable(TextView tv, int image) {
        Drawable d = getResources().getDrawable(image);
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        tv.setCompoundDrawables(null, null, d, null);
    }

    //popWindow消失时的回调
    @Override
    public void onPopWindowDismiss() {
        changeTextColor(mSale, false);
        changeTextColor(mPrice, false);
        changeTextColor(mLocation, false);
    }
}
