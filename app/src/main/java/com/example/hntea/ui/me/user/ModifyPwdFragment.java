package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyPwdFragment extends BaseFragment {
    private EditText mPwdPastEdit, mPwdNewEdit, mAgainPwdNewEdit;
    private UserConfigPresenter mUserConfigPresenter;
    public ModifyPwdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_pwd, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter=new UserConfigPresenter(null);
        mAppTitleBar.getTitle().setText("修改密码");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("确认");
        mPwdPastEdit = mFindViewUtils.findViewById(R.id.pwd_past_edit);
        mPwdNewEdit = mFindViewUtils.findViewById(R.id.pwd_new_edit);
        mAgainPwdNewEdit = mFindViewUtils.findViewById(R.id.pwd_new_again_edit);

    }

    @Override
    protected void setListener() {
        super.setListener();
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getText(mPwdNewEdit).equals(getText(mAgainPwdNewEdit))) {
                    modifyPwd();
                } else {
                    showAlertWithMsg("您输入的密码不一致");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    private void modifyPwd() {
        mUserConfigPresenter.getChangeUserPsw(getText(mPwdPastEdit), getText(mPwdNewEdit),
                getText(mAgainPwdNewEdit), new IViewUser<String>() {
            @Override
            public void onSuccess(String response) {
                hiddenLoading();
                showAlertWithMsg("修改密码成功");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popSelf();
                    }
                }, 1500);
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
                showAlertWithMsg(volleyError.toString());
            }
        });

    }

    @Override
    protected void setData() {
        super.setData();
    }
}
