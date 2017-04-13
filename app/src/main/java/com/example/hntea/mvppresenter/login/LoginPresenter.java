package com.example.hnTea.mvppresenter.login;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.login.IActionLogin;
import com.example.hnTea.mvpmodel.login.LoginData;
import com.example.hnTea.mvpmodel.login.bean.UserInfo;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by 太能 on 2016/12/21.
 */
public class LoginPresenter extends BasePresenter {
    private LoginData mLoginData;
    public LoginPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mLoginData =new LoginData();
    }

    public void getSmsCode(final IViewLogin<String> iViewLogin,String phoneNum,final int isLoginOrForget){
       mLoginData.getSmsCode(new IActionLogin<String>() {
           @Override
           public void success(String data) {
               iViewLogin.onSuccess(data);
           }

           @Override
           public void phpFail(String var) {
               iViewLogin.onPhpFail(var);
           }

           @Override
           public void smsCode(int code) {
                iViewLogin.onSmsCode(code);
           }

           @Override
           public void fail(VolleyError volleyError) {
                iViewLogin.onFail(volleyError);
           }

           @Override
           public void start(String var) {
                iViewLogin.onStart(var);
           }
       },phoneNum,isLoginOrForget);
    }

    public void getRegister(String phoneNum,
                            String psw,
                            String resultPsw,
                            String sms,
                            final IViewLogin<String> iViewLogin){
        mLoginData.register(phoneNum, psw, resultPsw, sms, new IActionLogin<String>() {
            @Override
            public void success(String data) {
                iViewLogin.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {

            }

            @Override
            public void smsCode(int code) {
                iViewLogin.onSmsCode(code);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewLogin.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewLogin.onStart(var);
            }
        });
    }

    public void getLogin(final String userName,
                         final String psw,
                         final IViewLogin<UserInfo> iViewLogin){
        mLoginData.login(userName, psw, new IActionLogin<UserInfo>() {
            @Override
            public void success(UserInfo data) {
                iViewLogin.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewLogin.onPhpFail(var);
            }

            @Override
            public void smsCode(int code) {
                iViewLogin.onSmsCode(code);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewLogin.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewLogin.onStart(var);
            }
        });
    }

    public void getForget(String phoneNum,
                          String psw,
                          String resultPsw,
                          String sms,
                          final IViewLogin<String> iViewLogin){
        mLoginData.forget(phoneNum, psw, resultPsw, sms, new IActionLogin<String>() {
            @Override
            public void success(String data) {
                iViewLogin.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewLogin.onPhpFail(var);
            }

            @Override
            public void smsCode(int code) {
                iViewLogin.onSmsCode(code);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewLogin.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewLogin.onStart(var);
            }
        });
    }
}
