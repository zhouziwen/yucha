package com.example.hnTea.ui.home.shop;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.mvpmodel.home.bean.ShopYellowModel;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.mvppresenter.home.ShopOrderPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessHomeGoodsFragment extends BaseFragment {


    @BindView(R.id.tv_sales_num)
    TextView mTvSalesNum;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.business_all_goods_gridView)
    GridView mBusinessAllGoodsGridView;
    @BindView(R.id.sellNum_order_pic)
    ImageView mSellNumOrderPic;
    @BindView(R.id.price_order_pic)
    ImageView mPriceOrderPic;
    private int styleId;
    private ShopOrderPresenter mShopOrderPresenter;
    private CommonAdapter<ShopYellowModel.GoodsListBean> mCommonAdapter;
    private String supplier_id = "";
    private ShopFragment mShopFragment;
    private ShopYellowModel mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_home_goods, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        styleId = 4;
        mShopOrderPresenter = new ShopOrderPresenter(null);
    }

    @Override
    protected void setListener() {
        super.setListener();
        final int[] i = {0};
        mTvSalesNum.setOnClickListener(v -> {
            i[0]++;
            if (i[0] % 2 == 1) {
                styleId = 3;
                mSellNumOrderPic.setImageResource(R.mipmap.order_pic_high);
            } else {
                styleId = 4;
                mSellNumOrderPic.setImageResource(R.mipmap.order_pic_low);
            }
            ;
            getGridData(supplier_id, String.format("%s", styleId), "1");
        });
        mTvPrice.setOnClickListener(v -> {
            i[0]++;
            if (i[0] % 2 == 1) {
                styleId = 1;
                mPriceOrderPic.setImageResource(R.mipmap.order_pic_high);
            } else {
                styleId = 2;
                mPriceOrderPic.setImageResource(R.mipmap.order_pic_low);
            }
            getGridData(supplier_id, String.format("%s", styleId), "1");
        });
    }

    @Override
    protected void setData() {
        super.setData();
        getGridData(supplier_id, String.format("%s", styleId), "1");
        setGridViewData();
    }

    private void getGridData(String suplier_id, String styleId, String page) {
        mShopOrderPresenter.getShopYellow(suplier_id, styleId, page, new IViewHome<ShopYellowModel>() {
            @Override
            public void onSuccess(ShopYellowModel data) {
                mData =data;
                mCommonAdapter.update(data.getGoods_list());
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

    private void setGridViewData() {
        mCommonAdapter = new CommonAdapter<ShopYellowModel.GoodsListBean>(getContext(), null, R.layout.business_all_goods_grid_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.home_HListView_Layout);
            }

            @Override
            protected void setViewDimen(View convertView) {
            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, ShopYellowModel.GoodsListBean item) {
                holder.setText(R.id.home_HListView_title, item.getGoods_name())
                        .setText(R.id.home_HListView_price, String.format("%s%s", "¥", item.getShop_price()));
                ImageView imageView = holder.getView(R.id.home_HListView_image);
                Glide.with(getContext()).load(item.getGoods_thumb()).placeholder(R.mipmap.pic_banner_moren).into(imageView);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                pushShopDetail(mData.getGoods_list().get(position).getGoods_id());
            }
        };
        mBusinessAllGoodsGridView.setAdapter(mCommonAdapter);
    }

    private void pushShopDetail(String goods_id) {
        if (mShopFragment == null) {
            mShopFragment = new ShopFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("shop_id",goods_id);
        ShowFragmentUtils.showFragment(getActivity(),
                mShopFragment.getClass(),
                FragmentTags.FRAGMENT_SHOP_DETAIL,
                bundle, true);
    }
    public void setSupplier_id(String var) {
        supplier_id = var;
    }
}
