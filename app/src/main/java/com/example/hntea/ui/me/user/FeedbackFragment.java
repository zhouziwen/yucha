package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.widget.AntiEmoJiEditText;
import com.example.hnTea.widget.BaseDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends BaseFragment{
    private UserConfigPresenter mUserConfigPresenter;
    private TextView mSave;
    private AntiEmoJiEditText mEditText;

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter =new UserConfigPresenter(null);
        mAppTitleBar.setTitle("意见反馈");
        mSave =mFindViewUtils.findViewById(R.id.save_feedBack);
        mEditText =mFindViewUtils.findViewById(R.id.feedBack_TextView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mAppTitleBar.getBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //监听返回键
                hideSoftKeyboard();
                if (!TextUtils.isEmpty(mEditText.getText().toString())){
                    showDialogWithMsg("确定退出此次编辑");
                    mDialog.setRightButtonListener(new BaseDialog.RightListener() {
                        @Override
                        public void onRightListener() {
                            mDialog.dismiss();
                            popSelf();
                        }
                    });
                    mDialog.setLeftButtonListener(new BaseDialog.LeftListener() {
                        @Override
                        public void onLeftListener() {
                            mDialog.dismiss();
                        }
                    });
                }else {
                    popSelf();
                }
            }
        });
        //提交意见反馈
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                if (TextUtils.isEmpty(mEditText.getText().toString())){
                    showAlertWithMsg("反馈意见不能为空");
                }else {
                    mUserConfigPresenter.getFeedBack(mEditText.getText().toString(),
                            new IViewUser<String>() {
                                @Override
                                public void onSuccess(String response) {
                                    hiddenLoading();
                                    showAlertWithMsg(response);
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            popSelf();
                                        }
                                    },1500);
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
        });
    }

}
