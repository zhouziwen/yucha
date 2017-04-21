package com.example.hnTea.ui.me.user.helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.mvpmodel.user.bean.HelperModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelperFragment extends BaseFragment {
    private List<String> mData;
    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private HnTeaFragment mDlDFragment;
    private QuestionFragment mQuestionFragment;
    private MsgFragment mMsgFragment;
    private BillQuestionFragment mBillQuestionFragment;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_helper, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("帮助中心");
        mListView = mFindViewUtils.findViewById(R.id.helper_listView);
        mData=new ArrayList<>();
        mData.add("常见问题");
        mData.add("发票制度");
        mData.add("关于我们");
        mData.add("用户协议");
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        setListViewBody();
    }

    private void setListViewBody() {
        mAdapter = new CommonAdapter<String>(getContext(), mData, R.layout.user_info_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.userInfo_layout);
            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, String item) {
                holder.setText(R.id.userInfo_list_title, item);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                switch (position) {
                    case 0:
                        //常见问题
                        if (mQuestionFragment == null) {
                            mQuestionFragment = new QuestionFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mQuestionFragment.getClass(),
                                "question", null, true);
                        break;
                    case 1:
                        //发票制度
                        if (mBillQuestionFragment == null) {
                            mBillQuestionFragment = new BillQuestionFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mBillQuestionFragment.getClass(),
                                "bill_question", null, true);
                        break;
                    case 2:
                        //关于我们
                        if (mDlDFragment == null) {
                            mDlDFragment = new HnTeaFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mDlDFragment.getClass(),
                                "dld", null, true);
                        break;
                    case 3:
                        //用户协议
                        if (mMsgFragment == null) {
                            mMsgFragment = new MsgFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mMsgFragment.getClass(),
                                "msg", null, true);
                        break;
                }
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
