package com.example.hnTea.adapter.recyclerView.shopCarAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.hnTea.R;
import com.example.hnTea.mvpmodel.home.bean.ShopCarListModel;
import com.example.hnTea.mvppresenter.home.HomePresenter;
import com.example.hnTea.mvppresenter.home.IViewHome;
import com.example.hnTea.utils.toast.ApToast;

import java.util.List;

import static com.example.hnTea.MyApplication.getContext;

/**
 * Created by jason_syf on 2017/2/8.
 * Email: jason_sunyf@163.com
 */

public class ShopCarChildAdapter extends RecyclerView.Adapter<ShopCarChildAdapter.MyHolder> {
    private HomePresenter mHomePresenter;
    private List<ShopCarListModel.GoodsBean> mGoodsBeanList;
    protected final Context mContext;

    public ShopCarChildAdapter(Context context, List<ShopCarListModel.GoodsBean> mGoodsBeanList) {
        this.mGoodsBeanList = mGoodsBeanList;
        mContext = context;
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox mCheckBox;
        private View itemsView;

        private ImageView mIconImageView;

        public ImageView getIconImageView() {
            return mIconImageView;
        }

        private TextView decreaseView, plusView, singlPriceView;
        private EditText countView;

        public TextView getSinglPriceView() {
            return singlPriceView;
        }

        public View getItemsView() {
            return itemsView;
        }

        public EditText getCountView() {
            return countView;
        }

        public CheckBox getCheckBox() {
            return mCheckBox;
        }

        public TextView getDecreaseView() {
            return decreaseView;
        }

        public TextView getPlusView() {
            return plusView;
        }

        public MyHolder(View itemView) {
            super(itemView);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.shop_car_cicle);
            itemsView = itemView.findViewById(R.id.item_view);
            decreaseView = (TextView) itemView.findViewById(R.id.goods_count_decrease);
            plusView = (TextView) itemView.findViewById(R.id.goods_count_plus);
            countView = (EditText) itemView.findViewById(R.id.goods_count);
            singlPriceView = (TextView) itemView.findViewById(R.id.shop_car_goods_price);
            mIconImageView = (ImageView) itemView.findViewById(R.id.shop_car_goods_icon);

        }

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_car_list_child_item, parent, false);//解决显示不全
        MyHolder holder = new MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.getCountView().setClickable(false);
        mHomePresenter = new HomePresenter(null);
        final ShopCarListModel.GoodsBean goodsBean = mGoodsBeanList.get(position);
        final int childposition = position;
        Glide.with(mContext).load(mGoodsBeanList.get(position).getGoods_img()).placeholder(R.mipmap.pic_banner_moren).into(holder.getIconImageView());
//        holder.getIconImageView().setImageResource( mGoodsBeanList.get(position).getPic());
        if (mGoodsBeanList.get(position).getStatus() == 1) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }
        holder.getCountView().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                modifyNum(mGoodsBeanList.get(position).getProduct_id(),holder.getCountView().getText().toString());
                mGoodsBeanList.get(position).setProduct_number(Integer.valueOf(holder.getCountView().getText().toString()));
            }
            return false;
        });
        final int[] i = {mGoodsBeanList.get(position).getProduct_number()};
        holder.getCountView().setText(String.format("%s", i[0]));
        holder.getSinglPriceView().setText("¥" + mGoodsBeanList.get(childposition).getPrice());
        // holder.getSinglPriceView().setText(String.format("%s%s", "¥",Integer.valueOf(mGoodsBeanList.get(childposition).getPrice()).doubleValue()));
        holder.getPlusView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++i[0];
                goodsBean.setProduct_number(i[0]);
                holder.countView.setText(String.format("%s", i[0]));
//                holder.singlPriceView.setText(String.format("%s%s", "¥", i[0] *Integer.valueOf(mGoodsBeanList.get(childposition).getPrice()).doubleValue()));
                modifyNum(mGoodsBeanList.get(childposition).getProduct_id(), String.format("%s",mGoodsBeanList.get(childposition).getProduct_number()));
                notifyDataSetChanged();
                if (mCallBack != null) {
                    mCallBack.OnClickListener(v, childposition);
                }
            }
        });
        holder.getDecreaseView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i[0] >= 1) {
                    --i[0];
                }
                goodsBean.setProduct_number(i[0]);
                holder.countView.setText(String.format("%s", i[0]));
//                holder.singlPriceView.setText(String.format("%s%s", "¥", i[0]*Integer.valueOf(mGoodsBeanList.get(childposition).getPrice()).doubleValue()));
                modifyNum(mGoodsBeanList.get(childposition).getProduct_id(), String.format("%s",mGoodsBeanList.get(childposition).getProduct_number()));
                notifyDataSetChanged();
                if (mCallBack != null) {
                    mCallBack.OnClickListener(v, childposition);
                }
            }
        });
        holder.getItemsView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.OnClickListener(v, childposition);
                }
            }
        });
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mCallBack != null) {
                    mCallBack.OnCheckListener(isChecked ? 1 : 0, childposition);
                }
                if (isChecked) {
                    goodsBean.setStatus(1);
                } else {
                    goodsBean.setStatus(0);
                }
            }
        });
        holder.itemView.setId(position);

    }


    @Override
    public int getItemCount() {
        if (mGoodsBeanList != null) {
            return mGoodsBeanList.size();
        }
        return 0;
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck {
        //回调函数 将店铺商品的圆圈的点击变化事件进行回调
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        void OnCheckListener(int isChecked, int childpostion);

        void OnClickListener(View view, int childpostion);
    }

    public void modifyNum(String rec_id,String num) {
        mHomePresenter.modifyGoodsNum(rec_id, num, new IViewHome<String>() {
            @Override
            public void onSuccess(String data) {
                ApToast.showBottom(data);
            }

            @Override
            public void onPhpFail(String var) {
                Toast.makeText(getContext(), var, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart(String var) {
            }

            @Override
            public void onFail(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

