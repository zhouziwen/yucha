package com.example.hnTea.ui;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hnTea.BuildConfig;
import com.example.hnTea.R;
import com.example.hnTea.utils.CheckOutEdTxt;
import com.example.hnTea.utils.FindViewUtils;
import com.example.hnTea.utils.logger.LogLevel;
import com.example.hnTea.utils.logger.Logger;
import com.example.hnTea.utils.toast.ToastLoading;
import com.example.hnTea.widget.AppTitleBar;

public class BaseActivity extends FragmentActivity {
    protected InputMethodManager mInputMethodManager;
    protected FindViewUtils mFindViewUtils;
    protected AppTitleBar mAppTitleBar;
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
        mAppTitleBar = (AppTitleBar) findViewById(R.id.app_title_bar);
    }

    protected void setListener() {
        if (mAppTitleBar != null) {
            mAppTitleBar.getBack().setOnClickListener(v -> {
               finish();
            });
            mAppTitleBar.getAction().setOnClickListener(v -> {

            });
        }
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
    //透明啊状态栏的处理
    protected void setStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mAppTitleBar != null) {
                mAppTitleBar.getState().setHeight(getStatusHeight(this));
            } else {
                TextView textView = mFindViewUtils.findViewById(R.id.state_bar);
                if (textView != null) {
                    textView.setHeight(getStatusHeight(this));
                }
            }
        }
    }

    //计算状态栏的高度
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Fragment.InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
    public void hiddenLoading() {
        if (mAppLoading != null) {
            mAppLoading.close();
        }
    }
}
