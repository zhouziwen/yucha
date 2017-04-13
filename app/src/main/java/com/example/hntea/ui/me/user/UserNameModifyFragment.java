package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.rxjava.eventBean.EventUserBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserNameModifyFragment extends BaseFragment {
    private UserConfigPresenter mUserConfigPresenter;
    private EditText mNameModifyEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_name_modify, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter = new UserConfigPresenter(null);
        mNameModifyEdit = mFindViewUtils.findViewById(R.id.modify_userName_edit);
        mNameModifyEdit.setText(MyApplication.getUserNickName());
    }
    @Override
    protected void setListener() {
        super.setListener();
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //保存
                if (checkOutUserName()) {
                    mUserConfigPresenter.getChangeUserNickName(getText(mNameModifyEdit),
                            new IViewUser<String>() {
                                @Override
                                public void onSuccess(String response) {
                                    hiddenLoading();
                                    hideSoftKeyboard();
                                    showAlertWithMsg(response);
                                    PreManager.instance().saveString("userNickName", getText(mNameModifyEdit));
                                    //发送通知给之前的界面  用户昵称修改
                                    RxEventBus.getDefault().postEvent(
                                            new EventUserBean(
                                                    getText(mNameModifyEdit),
                                                    MyApplication.getUserDefaultAddress()));
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            popSelf();
                                        }
                                    }, 1000);
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
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("修改昵称");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("保存");
    }

    private boolean checkOutUserName() {
        if (TextUtils.isEmpty(getText(mNameModifyEdit))) {
            showAlertWithMsg("昵称不能为空");
            return false;
        }
        if (MyApplication.getUserNickName().equals(getText(mNameModifyEdit))) {
            showAlertWithMsg("两次输入的昵称一致");
            return false;
        }
        return true;
    }
}
