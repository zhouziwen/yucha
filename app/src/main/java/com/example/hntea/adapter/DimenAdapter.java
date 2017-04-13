package com.example.hnTea.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.hnTea.apcontains.OnClickBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2016/11/21.
 */
public abstract class DimenAdapter<T> extends BaseAdapter implements OnClickBack {
    private List<T> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private int mLayoutId;

    public DimenAdapter(Context context, List<T> data, int layoutId) {
        if (data != null) {
            mData.addAll(data);
        }
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        T t = null;
        if (position >= 0 && position < getCount()) {
            t = mData.get(position);
        }
        return t;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;
        if (convertView != null) {
            holder = (BaseViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            holder = new BaseViewHolder(convertView,this);
            setListeners(holder,convertView,position);
        }
        holder.setPosition(position);
        setViewData(position,holder,getItem(position));
        setCellDiv(holder,getItem(position));
        return convertView;
    }

    public void update(List<T> data) {
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void append(T response){
        if (response!=null){
            mData.add(response);
        }
        notifyDataSetChanged();
    }

    //给item设置高度
    protected abstract void setCellDiv(BaseViewHolder holder,T item);

    protected abstract void setListeners(BaseViewHolder holder,View view,int position);

    protected abstract void setViewData(int position, BaseViewHolder holder, T item);

}
