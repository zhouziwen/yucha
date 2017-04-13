package com.example.hnTea.adapter.recyclerView.shopCarAdapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hnTea.R;
import com.example.hnTea.mvpmodel.home.bean.ShopCarListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason_syf on 2017/2/8.
 * Email: jason_sunyf@163.com
 */

public class ShopCarListAdapter extends RecyclerView.Adapter<ShopCarListAdapter.MyHolder> {
    private List<ShopCarListModel> mList;
    private List<ShopCarListModel.GoodsBean> mGoodsBeanList;
    protected final Context mContext;

    public ShopCarListAdapter(Context context,List<ShopCarListModel> list, List<ShopCarListModel.GoodsBean> goodsBeanList) {
        mList = list;
        mGoodsBeanList = goodsBeanList;
        mContext = context;
    }

    public ShopCarListAdapter(Context context, List<ShopCarListModel> list) {
        mList = list;
        mContext = context;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView businessName;
        //        private CheckBox checkBox;
        private ShopCarChildAdapter adapter;
        private RecyclerView.LayoutManager manager;
        public double price = 0;

        public double getPrice() {
            return price;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }

        public TextView getBusinessName() {
            return businessName;
        }

        public MyHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.list_items_recyclerView);
            businessName = (TextView) itemView.findViewById(R.id.shop_car_buisness_name);
            manager = new LinearLayoutManager(itemView.getContext());
            recyclerView.setLayoutManager(manager);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_car_list_item, null);//解决嵌套显示条目不全
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder,  int position) {
        final int parentposition = position;
        holder.adapter = new ShopCarChildAdapter(mContext, mList.get(position).getGoods());
//        holder.adapter.upData(mGoodsBeanList);
        holder.recyclerView.setAdapter(holder.adapter);
        holder.getBusinessName().setText(mList.get(position).getSupplier());
        //实现第二层RecyclerView的回调接口
        holder.adapter.setCallBack(new ShopCarChildAdapter.allCheck() {
            @Override
            public void OnCheckListener(int isChecked, int childpostion) {
                //将店铺商品的checkbox的点击变化事件进行回调
                if (mCallBack != null) {
                    mCallBack.OnItemCheckListener(isChecked, parentposition, childpostion);
                }
            }

            @Override
            public void OnClickListener(View view, int childpostion) {
                if (mCallBack != null) {
                    mCallBack.OnItemClickListener(view, parentposition, childpostion);
                }
            }
        });
        holder.itemView.setTag(mList.get(parentposition));

    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck {
        //回调函数 将店铺的圆圈的点击变化事件进行回调
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调   1 ischeck 0 nocheck
         void OnItemCheckListener(int isSelected, int parentposition, int chaildposition);

         void OnItemClickListener(View view, int parentposition, int childposition);

//        //回调函数 将店铺的checkbox的点击变化事件进行回调
//        public void OnCheckListener(boolean isSelected, int position);
    }
    public void upData(List<ShopCarListModel> data){
        if (data!=null){
            mList=new ArrayList<>();
            mList.addAll(data);
        }
        notifyDataSetChanged();
    }
}
