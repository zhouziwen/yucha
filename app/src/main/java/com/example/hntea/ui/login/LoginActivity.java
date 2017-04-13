package com.example.hnTea.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvpmodel.login.bean.UserInfo;
import com.example.hnTea.mvpmodel.user.bean.MyInfo;
import com.example.hnTea.mvppresenter.login.IViewLogin;
import com.example.hnTea.mvppresenter.login.LoginPresenter;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.ui.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView mLoginTv, mRegisterTv, mForgetTv, mBackTv;
    private EditText mUserNameEdTxt, mPswEdTxt;
    private LoginPresenter mLoginPresenter;
    private UserConfigPresenter mUserConfigPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        super.initView();
        mLoginPresenter = new LoginPresenter(null);
        mUserConfigPresenter = new UserConfigPresenter(null);
        mLoginTv = (TextView) findViewById(R.id.login_loginTv);
        mForgetTv = (TextView) findViewById(R.id.login_forget);
        mRegisterTv = (TextView) findViewById(R.id.login_register);
        mBackTv = (TextView) findViewById(R.id.login_top);
        mUserNameEdTxt = (EditText) findViewById(R.id.login_userName);
        mPswEdTxt = (EditText) findViewById(R.id.login_psw);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mLoginTv.setOnClickListener(this);
        mForgetTv.setOnClickListener(this);
        mRegisterTv.setOnClickListener(this);
        mBackTv.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_loginTv:
                //登录按钮
                requestLogin();
                break;
            case R.id.login_forget:
                //忘记密码
                pushRegister(2);
                break;
            case R.id.login_register:
                //注册
                pushRegister(1);
                break;
            case R.id.login_top:
                //返回按钮
                finish();
                break;
        }
    }

    private void requestLogin() {
        if (checkOutPhoneNum(mUserNameEdTxt)) {
            if (checkOutPsw()) {
                mLoginPresenter.getLogin(getText(mUserNameEdTxt),
                        getText(mPswEdTxt),
                        new IViewLogin<UserInfo>() {
                            @Override
                            public void onSuccess(UserInfo data) {
                                hiddenLoading();
                                showAlertWithMsg("登录成功");
                                PreManager.instance().saveString("userName", data.getPhone());
                                PreManager.instance().saveString("token", data.getToken());
                                PreManager.instance().saveString("uid", data.getUid());
                                myUserInfoSaveLocal();
                            }

                            @Override
                            public void onPhpFail(String var) {
                                hiddenLoading();
                                showAlertWithMsg(var);
                            }

                            @Override
                            public void onSmsCode(int code) {

                            }

                            @Override
                            public void onStart(String var) {
                                showLoading();
                            }

                            @Override
                            public void onFail(VolleyError volleyError) {
                                hiddenLoading();
                                showAlertWithMsg("网络无连接");
                            }
                        });
            }
        }
    }

    private boolean checkOutPsw() {
        if (TextUtils.isEmpty(getText(mPswEdTxt))) {
            showAlertWithMsg("密码不能为空");
            return false;
        }
        if (getText(mPswEdTxt).length() < 6) {
            showAlertWithMsg("密码不能小于6位");
            return false;
        }
        return true;
    }

    private void pushRegister(int type) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra("PageType", type);
        startActivity(intent);
        overridePendingTransition(R.anim.fragment_right_in, R.anim.fragment_right_out);
    }

    //从服务器下拉用户信息
    private void myUserInfoSaveLocal() {
            mUserConfigPresenter.getUserInfo(new IViewUser<MyInfo>() {
                @Override
                public void onSuccess(MyInfo response) {
                    PreManager.instance().saveString("headImg", response.getHead_img());
                    PreManager.instance().saveString("userRank", response.getUser_rank());
                    PreManager.instance().saveString("userNickName", response.getUsername());
                    PreManager.instance().saveString("userName", response.getPhone());
                    PreManager.instance().saveString("address", response.getAddress());
                    mHandler.postDelayed(() -> finish(), 1000);
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
