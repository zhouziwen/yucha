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
import com.example.hnTea.R;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerAdapter;
import com.example.hnTea.adapter.recyclerView.BaseRecyclerViewHolder;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserLookHistoryPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.me.bean.HistoryModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private BaseRecyclerAdapter<HistoryModel> mAdapter;
    private UserLookHistoryPresenter mUserLookHistoryPresenter;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_line, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserLookHistoryPresenter = new UserLookHistoryPresenter(null);
        mRecyclerView = mFindViewUtils.findViewById(R.id.me_history_recyclerView);
        mAppTitleBar.getTitle().setText("浏览记录");
    }

    @Override
    protected void setListener() {
        super.setListener();

    }

    @Override
    protected void setData() {
        super.setData();
        setListBody();
        setListData();
    }

    private void setListBody() {
        mAdapter = new BaseRecyclerAdapter<HistoryModel>(getContext(), null) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.home_listdetail_list_item;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, HistoryModel item) {
                holder.setText(R.id.home_listDetail_listItem_title, item.getGoods_name())
                        .setText(R.id.home_listDetail_listItem_type, item.getBrand_name())
                        .setText(R.id.home_listDetail_listItem_location, item.getSell_num())
                        .setText(R.id.home_listDetail_listItem_price, item.getShop_price());
                ImageView imageView = holder.getView(R.id.home_listDetail_listItem_image);
                Glide.with(getContext())
                        .load(BaseUrl.getBaseUrl()+item.getGoods_thumb())
                        .placeholder(R.mipmap.pic_banner_moren)
                        .into(imageView);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setListData() {
        mUserLookHistoryPresenter.getUserHistory(PreManager.instance().getString("goodsId"),
                new IViewUser<List<HistoryModel>>() {
                    @Override
                    public void onSuccess(List<HistoryModel> response) {
                        hiddenLoading();
                        mAdapter.upData(response);
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
                        showAlertWithMsg("暂无浏览记录");
                    }
                });
    }

}
