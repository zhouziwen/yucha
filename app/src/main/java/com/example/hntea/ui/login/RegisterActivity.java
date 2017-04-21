package com.example.hnTea.ui.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.mvpmodel.user.bean.UserhelperModel;
import com.example.hnTea.mvppresenter.login.IViewLogin;
import com.example.hnTea.mvppresenter.login.LoginPresenter;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserHelperPresenter;
import com.example.hnTea.ui.BaseActivity;
import com.example.hnTea.utils.NetWorkUtil;

import java.util.Timer;
import java.util.TimerTask;

// *****************************改界面可以当 注册 和忘记密码界面
//
//  1 :注册界面
//  2 :忘记密码界面
//
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private int mPageType;
    private TextView mBckTv, mRegisterTv, mSmsTv, mUserConfigTv;
    private ImageView mUserConfigImg;
    private EditText mPhoneNum, mSms, mPsw, mResultPsw;
    private LoginPresenter mLoginPresenter;
    private String phone;
    private int smsNum;
    private UserHelperPresenter mUserHelperPresenter;
    private WebView mWebView;
    private Timer mTimer;
    private boolean SmsBool = false;
    private boolean isSelectorConfig = false;
    private int smsCode = 60;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (smsCode == 0) {
                mSmsTv.setText("获取");
                smsCode = 60;
                SmsBool = false;
                mTimer.cancel();
            } else {
                smsCode--;
                mSmsTv.setText("" + smsCode + "s");
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        mUserHelperPresenter = new UserHelperPresenter(null);
        mPageType = intent.getIntExtra("PageType", 1);
        mUserConfigTv = mFindViewUtils.findViewById(R.id.register_tv_userConfig);
        mUserConfigImg = mFindViewUtils.findViewById(R.id.register_img_userConfig);
        if (mPageType == 2) {
            // 忘记密码界面 隐藏布局
            LinearLayout linearLayout =mFindViewUtils.findViewById(R.id.register_userConfig);
            linearLayout.setVisibility(View.GONE);
        }
        mLoginPresenter = new LoginPresenter(null);
        mBckTv = (TextView) findViewById(R.id.register_top);
        mRegisterTv = (TextView) findViewById(R.id.login_loginTv);
        mSmsTv = (TextView) findViewById(R.id.register_getSms);
        mPhoneNum = (EditText) findViewById(R.id.register_userName);
        mSms = (EditText) findViewById(R.id.register_sms);
        mPsw = (EditText) findViewById(R.id.login_psw);
        mResultPsw = (EditText) findViewById(R.id.login_psw_right);
        toSetText();
    }

    @Override
    protected void setListener() {
        super.setListener();
        mBckTv.setOnClickListener(this);
        mRegisterTv.setOnClickListener(this);
        mSmsTv.setOnClickListener(this);
        if (mUserConfigTv != null) {
            mUserConfigTv.setOnClickListener(this);
        }
        if (mUserConfigImg != null) {
            mUserConfigImg.setOnClickListener(this);
        }
    }

    @Override
    protected void setData() {
        super.setData();
    }

    private void getRegisterXieyi() {
        mUserHelperPresenter.getUserHelperData("register", new IViewUser<UserhelperModel>() {
            @Override
            public void onSuccess(UserhelperModel response) {
                mWebView.loadDataWithBaseURL(BaseUrl.getBaseUrl(), response.getContent(), "text/html", "utf-8", null);
                mHandler.postDelayed(() -> hiddenLoading(), 500);
//                hiddenLoading();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_top:
                //返回按钮
                finish();
                break;
            case R.id.login_loginTv:
                //注册按钮
                if (mPageType == 1) {
                    //注册
                    requestRegister();
                } else {
                    //忘记密码
                    requestForget();
                }
                break;
            case R.id.register_getSms:
                //注册获取验证码、
                    if (!SmsBool) {
                        if (checkOutPhoneNum(mPhoneNum)) {
                            showAlertWithMsg("验证码已发送,请注意查收。");
                            Sms();
                            SmsBool = true;
                            phone = getText(mPhoneNum);
                            requestSmsCode();
                        }
                    }

                break;
            case R.id.register_tv_userConfig:

                //注册界面的用户协议
                View view = LayoutInflater.from(this)
                        .inflate(R.layout.dialog_user_config, null);
                mWebView = (WebView) view.findViewById(R.id.register_webView);
                WebSettings webSettings = mWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
                webSettings.setSupportZoom(true);
//        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                webSettings.setBuiltInZoomControls(false);
                mWebView.setWebViewClient(new WebViewClient());
                mWebView.setWebChromeClient(new WebChromeClient());
                mHandler.post(this::getRegisterXieyi);
                Dialog dialog = new AlertDialog.Builder(this)
                        .setTitle("豫茶用户注册协议")
                        .setView(view)
                        .setPositiveButton("确定", (dialog1, which) -> dialog1.dismiss())
                        .setCancelable(false)
                        .create();
                if (NetWorkUtil.isNetWorkConnected(this)) {
                    mHandler.postDelayed(dialog::show
                            , 500);
                }
//                dialog.show();
                break;
            case R.id.register_img_userConfig:
                //注册界面 用户协议旁边的图标
                if (!isSelectorConfig) {
                    mUserConfigImg.setImageResource(R.mipmap.register_user_config_nomal);
                    isSelectorConfig = true;
                } else {
                    mUserConfigImg.setImageResource(R.mipmap.register_user_config_selector);
                    isSelectorConfig = false;
                }
                break;
        }
    }

    private void requestForget() {
        if (checkOutOtherNum()) {
            if (checkOutOtherNum()) {
                mLoginPresenter.getForget(getText(mPhoneNum),
                        getText(mPsw),
                        getText(mResultPsw),
                        getText(mSms),
                        new IViewLogin<String>() {
                            @Override
                            public void onSuccess(String data) {
                                hiddenLoading();
                                showAlertWithMsg("重置密码成功");
                                mHandler.postDelayed(RegisterActivity.this::finish, 1500);
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
                            }
                        });
            }
        }
    }

    private void requestRegister() {
        if (checkOutPhoneNum(mPhoneNum)) {
            if (checkOutOtherNum()) {
                mLoginPresenter.getRegister(getText(mPhoneNum),
                        getText(mPsw),
                        getText(mResultPsw),
                        getText(mSms),
                        new IViewLogin<String>() {
                            @Override
                            public void onSuccess(String data) {
                                hiddenLoading();
                                showAlertWithMsg("注册成功");
                                mHandler.postDelayed(() -> finish(), 1500);
                            }

                            @Override
                            public void onPhpFail(String var) {
                                hiddenLoading();
                                showAlertWithMsg(var);
                            }

                            @Override
                            public void onSmsCode(int code) {
                                //没用 当时有点懒 不想再写个接口了 所以多了个回调
                            }

                            @Override
                            public void onStart(String var) {
                                showLoading();
                            }

                            @Override
                            public void onFail(VolleyError volleyError) {
                                hiddenLoading();
                                showAlertWithMsg("" + volleyError);
                            }
                        });
            }
        }
    }

    private void requestSmsCode() {
        if (checkOutPhoneNum(mPhoneNum)) {
            mLoginPresenter.getSmsCode(new IViewLogin<String>() {
                @Override
                public void onSuccess(String data) {

                }

                @Override
                public void onPhpFail(String var) {
                    showAlertWithMsg(var);
                }

                @Override
                public void onSmsCode(int code) {
                    smsNum = code;
                }

                @Override
                public void onStart(String var) {

                }

                @Override
                public void onFail(VolleyError volleyError) {

                }
            }, getText(mPhoneNum), mPageType);
        }
    }

    private boolean checkOutOtherNum() {
        if (TextUtils.isEmpty(getText(mSms))) {
            showAlertWithMsg("请输入验证码");
            return false;
        }
        if (TextUtils.isEmpty(getText(mPsw))) {
            showAlertWithMsg("请输入密码");
            return false;
        }
        if (getText(mPsw).length() < 6) {
            showAlertWithMsg("密码不能小于6位数");
            return false;
        }
        if (TextUtils.isEmpty(getText(mResultPsw))) {
            showAlertWithMsg("两次输入的密码不一致");
            return false;
        }
        if (!getText(mPsw).equals(getText(mResultPsw))) {
            showAlertWithMsg("两次输入的密码不一致");
            return false;
        }
        String s = "" + smsNum;
        if (!getText(mSms).equals(s)) {
            showAlertWithMsg("请输入正确的验证码");
            return false;
        }
        if (!getText(mPhoneNum).equals(phone)) {
            showAlertWithMsg("请用发送验证码的手机号注册");
            return false;
        }
        return true;
    }

    private void toSetText() {
        if (mPageType == 2) {
            //注册
            mBckTv.setText("重置密码");
            mPsw.setHint("新密码");
            mResultPsw.setHint("确认新密码");
            mRegisterTv.setText("重置密码");
        }
    }

    private void Sms() {
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask, 1000, 1000);
    }

}
