package com.example.hnTea.ui.home.shop;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Base;
import com.example.hnTea.mvpmodel.home.bean.ShopDetail_Related;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.home.shop.bean.ChoseTypeAdapter;
import com.example.hnTea.ui.login.LoginActivity;
import com.example.hnTea.utils.ActionSheet;
import com.example.hnTea.utils.DisPlayUtils;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.SystemUtil;
import com.example.hnTea.utils.UMShareUtils;
import com.example.hnTea.widget.ScrollViewForBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopMsgFragment extends BaseFragment {
    // list header
    @BindView(R.id.shop_header_title)
    TextView mShopHeaderTitle;
    @BindView(R.id.shop_header_price)
    TextView mShopHeaderPrice;
    @BindView(R.id.shop_header_shopType)
    TextView mShopHeaderShopType;
    @BindView(R.id.shop_header_inventory)
    TextView mShopHeaderInventory;
    @BindView(R.id.shop_header_isYunFei)
    TextView mShopHeaderIsYunFei;
    @BindView(R.id.shop_header_isFaPiao)
    TextView mShopHeaderIsFaPiao;
    @BindView(R.id.shop_header_isTime)
    TextView mShopHeaderIsTime;
    @BindView(R.id.shop_header_isZhiBao)
    TextView mShopHeaderIsZhiBao;

    private HomePresenter mHomePresenter;
    private BuyFragment mBuyFragment;
    private ShopDetail_Base mData;
    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private ChoseTypeAdapter mChoseAdapter;
    private View mHeaderView, mParentView;
    private View mFooterView;
    private RecyclerView mRecyclerView;
    private ViewPager mPager;
    private TextView mDelete, mAdd, mNum, mShare;
    private BaseRecyclerAdapter<ShopDetail_Related> mRecyclerAdapter;
    private ActionSheet mSheet, mChoseTypeSheet;
    private ImageView mCloseChoseType;
    private View bgView;
    private String shop_id = "";

    //型号选择
    private RelativeLayout mChoseType;
    //商品轮播图下面的点点
    private LinearLayout mLinearLayout;
    //型号选择弹窗
    private int mShopNum = 1;
    private boolean canPay = true;
    private boolean canPay2 = false;
    private TextView mActionNum, mActionToPay;
    private int mActionInt = 0;
    private String mProject_id = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_msg, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePresenter = new HomePresenter(null);
        //在界面刚进来时要做网络请求
        mParentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shop_msg, null);
        mListView = mFindViewUtils.findViewById(R.id.shopMsg_listView);
        bgView = mFindViewUtils.findViewById(R.id.ShopMsg_bgView);
        mPager = mFindViewUtils.findViewById(R.id.shop_header_viewPager);
        mLinearLayout = mFindViewUtils.findViewById(R.id.shop_banner_tips);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //list的header
        mHeaderView = inflater.inflate(R.layout.shop_header, null);
        mChoseType = (RelativeLayout) mHeaderView.findViewById(R.id.shopMsg_choseType);
        mShare = (TextView) mHeaderView.findViewById(R.id.shop_header_share);
        mListView.addHeaderView(mHeaderView);
        ButterKnife.bind(this, mHeaderView);
        //list的footer
        mFooterView = inflater.inflate(R.layout.shop_footer, null);
        mRecyclerView = (RecyclerView) mFooterView.findViewById(R.id.shop_footer_recyclerView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mShare.setOnClickListener(v -> {
            String shareUrl = BaseUrl.getBaseUrlNoEnd() +
                    "/goods/getGoodsInfo?goodsId=" +
                    mData.getInfo().getGoods_id();
            UMShareUtils shareUtils = new UMShareUtils(getActivity(),
                    getContext(),
                    mData.getGallery().get(0).getImg_url()==null?
                            "www.dianlidian.com":mData.getGallery().get(0).getImg_url(),
                    shareUrl,
                    mData.getInfo().getGoods_name(),
                    mData.getInfo().getGoods_desc()) {
            };
            shareUtils.openShareDialog();
        });
        mChoseType.setOnClickListener(v -> showPopWindow(2));
    }

    private void initPopWindow() {
        mChoseTypeSheet = new ActionSheet(getContext(),
                R.layout.action_chose_type,
                DisPlayUtils.getWidthPx(),
                DisPlayUtils.getHeightPx() / 5 * 4,
                () -> {
                    setWindowBg(1.0f);
                    bgView.setVisibility(View.GONE);
                }) {
            @Override
            public void setData(View view) {
                //去支付的按钮
                mActionToPay = (TextView) view.findViewById(R.id.add_partner_toBuy);
                //商品缩略小图
                ImageView smallIcon = (ImageView) view.findViewById(R.id.choseType_image);
                if (mData.getGallery().size() == 0 ) {
                    smallIcon.setImageResource(R.mipmap.pic_banner_moren);
                }else {
                    Glide.with(getContext())
                            .load(mData.getGallery().get(0).getImg_url())
                            .placeholder(R.mipmap.pic_banner_moren)
                            .diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .into(smallIcon);
                }
                //price
                TextView price = (TextView) view.findViewById(R.id.choseType_price);
                price.setText("¥" + mData.getInfo().getShop_price());
                //已选的件数
                mActionNum = (TextView) view.findViewById(R.id.choseType_number);
                //库存
                final TextView shopNum = (TextView) view.findViewById(R.id.choseType_shop_num);

                mCloseChoseType = (ImageView) view.findViewById(R.id.choseType_close);
                ListView listView =
                        (ListView) view.findViewById(R.id.choseType_list);
                mChoseAdapter = new ChoseTypeAdapter(getContext(), mData.getAttrs(), mData.getNums()) {

                    @Override
                    public void changeStates(String s) {
                        if (s.equals("1")) {
                            //有货
                            canPay = true;
                            refresh();
                        } else {
                            //无货
                            canPay = false;
                            shopNum.setText("库存:(暂无库存)");
                            refresh();
                        }
                    }

                    @Override
                    public void selectorAll(int position) {
                        canPay2 = true;
                        shopNum.setText("库存:" + mData.getNums().get(position).getProduct_number());
                        mProject_id = mData.getNums().get(position).getProduct_id();
                        price.setText("" + mData.getNums().get(position).getProduct_price());
                    }
                };
                mActionToPay.setOnClickListener(v -> {
                    //弹窗去支付页面 加入购物车
                    if (canPay) {
                        if (canPay2) {
                            if (isLogin()){
                                if (mActionInt == 1) {
                                    //加入购物车
                                    toRequestAddShopping();
                                } else {
                                    //去支付
                                    toRequestOrder();
                                }
                            }
                        } else {
                            showAlertWithMsg("参数不完整");
                        }
                    }
                });
                listView.setAdapter(mChoseAdapter);
                View footer = LayoutInflater.from(getContext()).
                        inflate(R.layout.chose_type_list_footer, null);
                mDelete = (TextView) footer.findViewById(R.id.chose_delete);
                mAdd = (TextView) footer.findViewById(R.id.chose_add);
                mNum = (TextView) footer.findViewById(R.id.chose_num);
                setChoseTypeListener();
                listView.addFooterView(footer);
            }
        };
    }

    //1表示加入购物车 2表示去支付
    public void showPopWindow(int i) {
        mActionInt = i;
        bgView.setVisibility(View.VISIBLE);
        setWindowBg(0.5f);
        refresh();
        mChoseTypeSheet.showAsDownWindow(mParentView);
    }

    public void refresh() {
        if (mActionInt == 1) {
            //加入购物火车
            mActionToPay.setText("加入购物车");
        } else {
            mActionToPay.setText("去支付");
        }
        if (!canPay) {
            mActionToPay.setText("暂无库存");
            mActionToPay.setBackground(getResources().getDrawable(R.drawable.no_shop));
        } else {
            mActionToPay.setBackground(getResources().getDrawable(R.drawable.addpartner_selector));
        }
    }

    private void setChoseTypeListener() {
        //***
        //*关闭型号选择的弹框
        //***
        if (mCloseChoseType != null) {
            mCloseChoseType.setOnClickListener(v -> mChoseTypeSheet.dismissWindow());
        }
        //***
        //*商品数量  加号
        //***
        if (mAdd != null) {
            mAdd.setOnClickListener(v -> {
                mShopNum++;
                refreshShopNum();
            });
        }
        //***
        //*商品数量  减号
        //***
        if (mDelete != null) {
            mDelete.setOnClickListener(v -> {
                if (mShopNum != 1) {
                    mShopNum--;
                    refreshShopNum();
                }
            });
        }
    }

    private void refreshShopNum() {
        mNum.setText("" + mShopNum);
        mActionNum.setText("已选:" + mShopNum + "件");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setData() {
        super.setData();
        setBaseListView();
        setHListView();
    }

    private void toRequestOrder() {
        mChoseTypeSheet.dismissWindow();
        if (mBuyFragment == null) {
            mBuyFragment = new BuyFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("project_id", mProject_id);
        bundle.putString("num", "" + mShopNum);
        ShowFragmentUtils.showFragment(getActivity(),
                mBuyFragment.getClass(),
                "buy_fg",
                bundle,
                true);
    }

    private void toRequestAddShopping() {
        mHomePresenter.getAddShopCar(mProject_id, "" + mShopNum, new IViewHome<String>() {
            @Override
            public void onSuccess(String data) {
                hiddenLoading();
                showAlertWithMsg(data);
                mChoseTypeSheet.dismissWindow();
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

    //设置商品基本信息
    private void setShopDetail() {
        mShopHeaderTitle.setText(mData.getInfo().getGoods_name()
                == null ? "" : mData.getInfo().getGoods_name());
        mShopHeaderPrice.setText(mData.getInfo().getShop_price()
                == null ? "" : "¥" + mData.getInfo().getShop_price());
        mShopHeaderShopType.setText(mData.getInfo().getMinimum_order_quantity()
                == null ? "" : "销量:" + mData.getInfo().getCell_number() + "件");
        mShopHeaderInventory.setText(mData.getInfo().getGoods_number()
                == null ? "" : "库存:" + mData.getInfo().getGoods_number() + "件");
        mShopHeaderIsYunFei.setText(Integer.parseInt(mData.getInfo().getIs_shipping()) == 0 ? "不包邮" : "包邮");
        mShopHeaderIsFaPiao.setText(Integer.parseInt(mData.getInfo().getIs_invoice()) == 0 ? "不提供发票" : "提供发票");
        mShopHeaderIsTime.setText(mData.getInfo().getShipment_time()
                == null ? "" : "现货" + mData.getInfo().getShipment_time() + "天内发货");
        mShopHeaderIsZhiBao.setText(mData.getInfo().getOrder_shipment_time()
                == null ? "" : "订货" + mData.getInfo().getOrder_shipment_time() + "天内发货");
    }

    //该界面的listView
    private void setBaseListView() {
        mAdapter = new CommonAdapter<String>(getContext(), null, R.layout.home_listheader_grid_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
            }

            @Override
            protected void setViewDimen(View convertView) {
            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, String item) {
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
            }
        };
        mListView.setAdapter(mAdapter);
    }

    //商品推荐
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setHListView() {
        //设置横向滑动的recyclerView的adapter
        mRecyclerAdapter = new BaseRecyclerAdapter<ShopDetail_Related>(getContext(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.home_hlist_item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, ShopDetail_Related item) {
                ImageView imageView = holder.getView(R.id.home_HListView_image);
                Glide.with(getContext())
                        .load(item.getGoods_thumb())
                        .placeholder(R.mipmap.pic_banner_moren)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
                holder.setText(R.id.home_HListView_title, item.getGoods_name())
                        .setText(R.id.home_HListView_price, item.getShop_price());
            }
        };
        mRecyclerView.setAdapter(mRecyclerAdapter);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置默认的动画 事实证明 看不出什么动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListView.addFooterView(mFooterView);
        if (MyApplication.getInstance().is21Version()) {
            mListView.setNestedScrollingEnabled(true);
        }
    }

    private void setPager() {
        final List<String> urls = new ArrayList<>();
        for (int i = 0; i < mData.getGallery().size(); i++) {
            urls.add(mData.getGallery().get(i).getImg_url());
        }
        ScrollViewForBanner s = new ScrollViewForBanner(mPager, urls, mLinearLayout, getContext(), false) {
            @Override
            public void imagesClick(int position) {
                //商品信息 点击图片看大图
                DisplayMetrics displayMetrics = new DisplayMetrics();
                mSheet = new ActionSheet(getContext(),
                        R.layout.action_draw_pic,
                        DisPlayUtils.getWidthPx(),
                        displayMetrics.equals(DisPlayUtils.getHeightPx()) ?
                                DisPlayUtils.getHeightPx() :
                                DisPlayUtils.getHeightPx() + SystemUtil.getVirtualBarHeight(),
                        () -> {
                        }) {
                    @Override
                    public void setData(View view) {
                        LinearLayout l = (LinearLayout) view.findViewById(R.id.action_pic_tips);
                        ViewPager p = (ViewPager) view.findViewById(R.id.action_pic_Pager);
                        ScrollViewForBanner s =
                                new ScrollViewForBanner(p, urls, l, getContext(), true) {
                                    @Override
                                    public void imagesClick(int position) {

                                    }
                                };
                        ImageView close = (ImageView) view.findViewById(R.id.action_pic_close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //关闭 放大图
                                mSheet.dismissWindow();
                            }
                        });
                    }
                };
                mSheet.showAsXYPoint(LayoutInflater.from(getContext()).
                                inflate(R.layout.fragment_shop_msg, null),
                        0, 0);
            }
        };
    }

    public void dataHasChanged(ShopDetail_Base data){
        mData = data;
        initPopWindow();
        setShopDetail();
        //刷新 相关推荐
        mRecyclerAdapter.upData(data.getRelated());
        setPager();
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
