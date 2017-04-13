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
    private UserConfigPresenter mUserConfigPresenter;
    private List<HelperModel> mData;
    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private DlDFragment mDlDFragment;
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
        mUserConfigPresenter = new UserConfigPresenter(null);
        mAppTitleBar.getTitle().setText("帮助中心");
        mListView = mFindViewUtils.findViewById(R.id.helper_listView);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        setListViewBody();
        setListViewData();
    }

    private void setListViewBody() {
        mAdapter = new CommonAdapter<String>(getContext(), null, R.layout.user_info_list_item) {
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
                if (mDlDFragment == null) {
                    mDlDFragment = new DlDFragment();
                }
                Bundle bundle =new Bundle();
                bundle.putString("helper_title",mData.get(position).getTitle());
                bundle.putString("helper_html",mData.get(position).getContent());
                ShowFragmentUtils.showFragment(getActivity(),
                        mDlDFragment.getClass(),
                        "dld", bundle, true);
//                switch (position) {
//                    case 0:
//                        //常见问题
//                        if (mQuestionFragment == null) {
//                            mQuestionFragment = new QuestionFragment();
//                        }
//                        ShowFragmentUtils.showFragment(getActivity(),
//                                mQuestionFragment.getClass(),
//                                "question", null, true);
//                        break;
//                    case 1:
//                        //发票制度
//                        if (mBillQuestionFragment == null) {
//                            mBillQuestionFragment = new BillQuestionFragment();
//                        }
//                        ShowFragmentUtils.showFragment(getActivity(),
//                                mBillQuestionFragment.getClass(),
//                                "bill_question", null, true);
//                        break;
//                    case 2:
//                        //关于我们
//                        if (mDlDFragment == null) {
//                            mDlDFragment = new DlDFragment();
//                        }
//                        ShowFragmentUtils.showFragment(getActivity(),
//                                mDlDFragment.getClass(),
//                                "dld", null, true);
//                        break;
//                    case 3:
//                        //用户协议
//                        if (mMsgFragment == null) {
//                            mMsgFragment = new MsgFragment();
//                        }
//                        ShowFragmentUtils.showFragment(getActivity(),
//                                mMsgFragment.getClass(),
//                                "msg", null, true);
//                        break;
//                }
            }
        };
        mListView.setAdapter(mAdapter);
    }

    private void setListViewData() {
        mUserConfigPresenter.getHelperMsg(new IViewUser<List<HelperModel>>() {
            @Override
            public void onSuccess(List<HelperModel> response) {
                hiddenLoading();
                mData=response;
                List<String> titles =new ArrayList<String>();
                for (HelperModel model:response) {
                    titles.add(model.getTitle());
                }
                mAdapter.update(titles);
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
