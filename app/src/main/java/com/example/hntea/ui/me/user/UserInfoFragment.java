package com.example.hnTea.ui.me.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.ui.login.LoginActivity;
import com.example.hnTea.rxjava.eventBean.EventLogout;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.toast.ApToast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment {
    private ListView mListView;
    private CommonAdapter<String> mAdapter;
    private View mFooterView;
    private TextView mLogOut;
    private FeedbackFragment mFeedbackFragment;
    private Switch mSwitch, mVoiceSwitch;
    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("我的设置");
        mListView = mFindViewUtils.findViewById(R.id.userInfo_listView);
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.user_info_foot_view, null);
        mLogOut = (TextView) mFooterView.findViewById(R.id.userInfo_logout);
        mListView.addFooterView(mFooterView);
        mSwitch = mFindViewUtils.findViewById(R.id.push_switch);
        mVoiceSwitch = mFindViewUtils.findViewById(R.id.voice_switch);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLogOut.setOnClickListener(v -> {
            //退出登录
            if (MyApplication.getInstance().isLogin()){
                //没登录
                showAlertWithMsg("您尚未登录");
            }else {
                showDialogWithMsg("是否退出登录");
                mDialog.setLeftButtonListener(() -> mDialog.dismiss());
                mDialog.setRightButtonListener(() -> {
                    mDialog.dismiss();
                    MyApplication.getInstance().logOut();
                    Intent intent =new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    RxEventBus.getDefault().postEvent(new EventLogout());
                });
            }
        });
        //设置推送 以及推送声音
//        mSwitch.setChecked(PreManager.instance().getBool("push"));
//        mSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                JPushInterface.clearAllNotifications(MyApplication.getContext());
//                JPushInterface.stopPush(MyApplication.getContext());
//                PreManager.instance().saveBool("push",true);
//                ApToast.showBottom("开启通知");
//            } else {
//                JPushInterface.resumePush(MyApplication.getContext());
//                PreManager.instance().saveBool("push",false);
//                ApToast.showBottom("关闭通知 +");
//            }
//        });
//        mVoiceSwitch.setChecked(PreManager.instance().getBool("pushVoice"));
//                JPushInterface.setSilenceTime(MyApplication.getCon, 30, 8, 30);
//                PreManager.instance().saveBool("pushVoi
//        mVoiceSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->{text(), 22
//            if (isChecked) {ce",true);
//                ApToast.showBottom("22：30—8：30为静音时段");
//            } else {
//                PreManager.instance().saveBool("pushVoice",false);
//                ApToast.showBottom("关闭静音");
//            }
//        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAdapter = new CommonAdapter<String>(getContext(), getData(), R.layout.user_info_list_item) {
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
                        showLoading();
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hiddenLoading();
                                showAlertWithMsg("已清理");
                            }
                        }, 1000);
                        break;
                    case 1:
                        if (mFeedbackFragment == null) {
                            mFeedbackFragment = new FeedbackFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mFeedbackFragment.getClass(),
                                "feedBack",
                                null, true);
                        break;
                }
            }
        };
        mListView.setAdapter(mAdapter);
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("清理缓存");
        list.add("意见反馈");
        return list;
    }
}
