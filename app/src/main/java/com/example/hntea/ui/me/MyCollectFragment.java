package com.example.hnTea.ui.me;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hnTea.R;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.mvpmodel.user.bean.MyCollectModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.custom.EmptyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectFragment extends BaseFragment {
    private UserConfigPresenter mPresenter;
    private EmptyRecyclerView mRecyclerView;
    private BaseRecyclerAdapter<MyCollectModel.CollectListBean> mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_collect, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPresenter = new UserConfigPresenter(null);
        mRecyclerView = mFindViewUtils.findViewById(R.id.myCollect_recyclerView);
        mAppTitleBar = mFindViewUtils.findViewById(R.id.app_title_bar);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.default_no_infomation);
        mRecyclerView.setEmptyView(imageView);
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("我的收藏");
        setListBody();
        setListData();
    }

    private void setListBody() {
        mAdapter = new BaseRecyclerAdapter<MyCollectModel.CollectListBean>(getContext(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.history_list_item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, MyCollectModel.CollectListBean item) {
                holder.setText(R.id.history_title, item.getGoods_name()==null?"":item.getGoods_name())
                        .setText(R.id.history_type, item.getCompany_name()==null?"":item.getCompany_name())
                        .setText(R.id.history_location,item.getSell_num()==null?"":"库存"+item.getSell_num() + "件")
                        .setText(R.id.history_price,item.getShop_price()==null?"": "￥" + item.getShop_price());
                ImageView imageView = holder.getView(R.id.history_image);
                if (item.getGoods_thumb()==null) return;
                Glide.with(getContext())
                        .load(item.getGoods_thumb())
                        .placeholder(R.mipmap.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(imageView);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setListData() {
        mPresenter.getMyCollect("1", new IViewUser<MyCollectModel>() {
            @Override
            public void onSuccess(MyCollectModel response) {
                hiddenLoading();
                mAdapter.upData(response.getCollectList());
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                showAlertWithMsg(var);
            }

            @Override
            public void onStart(String var) {
                showLoading();
            }

            @Override
            public void onFail(VolleyError volleyError) {
                hiddenLoading();
                showAlertWithMsg("请检查网络");
            }
        });
    }
}
