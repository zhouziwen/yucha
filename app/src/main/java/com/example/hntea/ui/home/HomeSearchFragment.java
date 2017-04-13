package com.example.hnTea.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSearchFragment extends BaseFragment {
    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private TextView backTv;
    private HomeListDetail_Fg homeListDetail_fg;

    @Override
    public void onResume() {
        super.onResume();
       setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mListView =mFindViewUtils.findViewById(R.id.home_search_listView);
        backTv =mFindViewUtils.findViewById(R.id.title_back);
    }

    @Override
    protected void setListener() {
        super.setListener();
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回键
                popSelf();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        final List<String> list =new ArrayList<>();
        list.add("变压器");
        list.add("断路器");
        list.add("变频器");
        mAdapter =new CommonAdapter<String>(getContext(),list,R.layout.home_search_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.homeSearch_list_item_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, String item) {
                holder.setText(R.id.homeSearch_list_item_tv,item);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                pushDetail(list.get(position));
            }
        };
        mListView.setAdapter(mAdapter);
    }

    private void pushDetail(String var) {
        if (homeListDetail_fg == null) {
            homeListDetail_fg = new HomeListDetail_Fg();
        }
        Bundle bundle = new Bundle();
        bundle.putString("title",var);
        ShowFragmentUtils.showFragment(getActivity(),
                homeListDetail_fg.getClass(),
                FragmentTags.FRAGMENT_HOME_LIST_DETAIL,
                bundle, true);
    }
}
