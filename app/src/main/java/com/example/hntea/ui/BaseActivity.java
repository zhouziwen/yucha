package com.example.hnTea.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hnTea.BuildConfig;
import com.example.hnTea.utils.CheckOutEdTxt;
import com.example.hnTea.utils.FindViewUtils;
import com.example.hnTea.utils.logger.LogLevel;
import com.example.hnTea.utils.logger.Logger;
import com.example.hnTea.utils.toast.ToastLoading;

public class BaseActivity extends FragmentActivity {
    protected InputMethodManager mInputMethodManager;
    protected FindViewUtils mFindViewUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
//       setContentView(R.layout.activity_base);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mFindViewUtils = new FindViewUtils(view);
        initView();
        setListener();
        setData();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        initView();
//        setListener();
//        setData();
//    }

    protected void initView() {

    }

    protected void setListener() {

    }

    protected void setData() {

    }

    protected boolean checkOutPhoneNum(EditText editText) {
        if (TextUtils.isEmpty(getText(editText))) {
            showAlertWithMsg("手机号不能为空");
            return false;
        }

        if (!CheckOutEdTxt.isPhoneNumber(getText(editText))) {
            showAlertWithMsg("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    protected String getText(EditText editText) {
        return editText.getText().toString();
    }

    //隐藏键盘
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN && getCurrentFocus() != null) {
            mInputMethodManager.hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showAlertWithMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ToastLoading mAppLoading;

    public void showLoading() {
        if (mAppLoading == null) {
            mAppLoading = new ToastLoading(this);
        }
        mAppLoading.open();
    }

    public void showLoading(int textResId) {
        if (mAppLoading == null) {
            mAppLoading = new ToastLoading(this);
        }
        mAppLoading.open(textResId);
    }

    public void hiddenLoading() {
        if (mAppLoading != null) {
            mAppLoading.close();
        }
    }
}
