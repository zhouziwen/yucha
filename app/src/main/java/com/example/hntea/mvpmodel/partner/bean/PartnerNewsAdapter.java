package com.example.hnTea.mvpmodel.partner.bean;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.adapter.recyclerView.ItemViewDelegate;
import com.example.hnTea.adapter.recyclerView.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by 太能 on 2016/12/20.
 */
public abstract class PartnerNewsAdapter extends MultiItemTypeAdapter<PartnerNewsModel> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<PartnerNewsModel> mDatas;
    protected LayoutInflater mInflater;

    public PartnerNewsAdapter(final Context context,
                              final int layoutHeader,
                              final int layoutId,
                              final int layoutId1,
                              final int layoutId2,
                              List<PartnerNewsModel> datas) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<PartnerNewsModel>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutHeader;
            }

            @Override
            public boolean isForViewType(PartnerNewsModel item, int position) {
                if (item.getType() == 100) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, PartnerNewsModel partnerModel, int position) {
                headerConvert(holder, partnerModel, position);
            }
        });


        addItemViewDelegate(new ItemViewDelegate<PartnerNewsModel>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(PartnerNewsModel item, int position) {
                if (item.getType() == 0) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position) {
                PartnerNewsAdapter.this.convert(holder, t, position);
            }
        });

        addItemViewDelegate(new ItemViewDelegate<PartnerNewsModel>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId1;
            }

            @Override
            public boolean isForViewType(PartnerNewsModel item, int position) {
                if (item.getType() == 1) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position) {
                PartnerNewsAdapter.this.convert1(holder, t, position);
            }
        });

        addItemViewDelegate(new ItemViewDelegate<PartnerNewsModel>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId2;
            }

            @Override
            public boolean isForViewType(PartnerNewsModel item, int position) {
                if (item.getType() == 3) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void convert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position) {
                PartnerNewsAdapter.this.convert2(holder, t, position);
            }
        });
    }

    protected abstract void headerConvert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position);

    protected abstract void convert(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position);

    protected abstract void convert1(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position);

    protected abstract void convert2(BaseRecyclerViewHolder holder, PartnerNewsModel t, int position);

}
