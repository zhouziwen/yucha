package com.example.hnTea.adapter.pager;

import android.support.v4.app.FragmentManager;

import com.example.hnTea.ui.BaseFragment;

import java.util.List;

/**
 * Created by jason_syf on 2017/2/21.
 * Email: jason_sunyf@163.com
 */

public class TablayoutPagerAdapter extends ViewPagerAdapterForFg {
    private List<String> mList;

    public TablayoutPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> list) {
        super(fm, fragments);
        mList = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);//页卡标题
    }

}
