package com.example.hnTea.adapter.pager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2016/11/10.
 */
public class ViewPagerAdapterForView extends PagerAdapter {

    private List<View> listViews = new ArrayList<View>();

    public ViewPagerAdapterForView(List<View> listViews) {
        this.listViews = listViews;
    }

    @Override
    public int getCount() {
        return listViews.size();
    }

    @Override
    public Object instantiateItem(View container, int position) {
        try {
            if (listViews.get(position).getParent() == null)
                ((ViewPager) container).addView(listViews.get(position), 0);
            else {
                ((ViewGroup) listViews.get(position).getParent()).removeView(listViews.get(position));
                ((ViewPager) container).addView(listViews.get(position), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listViews.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    public void upData(List<View> data) {
        if (data != null) {
            listViews.addAll(data);
        }
        notifyDataSetChanged();
    }
}
