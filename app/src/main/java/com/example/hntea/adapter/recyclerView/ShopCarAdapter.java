package com.example.hnTea.adapter.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.hnTea.R;
import com.example.hnTea.adapter.recyclerView.shopCarAdapter.Cbean;
import com.example.hnTea.adapter.recyclerView.shopCarAdapter.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason_syf on 2017/2/10.
 * Email: jason_sunyf@163.com
 */

public class ShopCarAdapter extends BaseRecyclerAdapter<ShopCarBean<Cbean>>{
    private List<Integer> checkPos=new ArrayList<>();
    protected static final int SELECT_TRUE = 1, SELECT_FALSE = 0;

    public ShopCarAdapter(Context ctx, List<ShopCarBean<Cbean>> list) {
        super(ctx, list);
    }


    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.shop_car_list_item;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, ShopCarBean<Cbean> item) {
        checkPos.add(position, item.getStatus());
    }


}
