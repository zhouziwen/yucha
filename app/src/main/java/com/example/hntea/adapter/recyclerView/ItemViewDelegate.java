package com.example.hnTea.adapter.recyclerView;

/**
 * Created by 太能 on 2016/12/20.
 */
public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(BaseRecyclerViewHolder holder, T t, int position);
}
