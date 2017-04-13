package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.mvppresenter.login.IViewLogin;
import com.example.hnTea.mvppresenter.login.LoginPresenter;
import com.example.hnTea.mvppresenter.me.MePricePresenter;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.CheckOutEdTxt;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserPhoneNumModifyFragment extends BaseFragment {
    private EditText mPastPhoneNumEdit,mPastPassWordEdit,mNewPhoneNumEdit,mSmsInputEdit;
    private TextView mGetSmsTxt;
    private boolean SmsBool = false;
    private TextView mSmsTv;
    private int smsNum;
    private Timer mTimer;
    private int smsCode = 60;
    private LoginPresenter mLoginPresenter;
    private MePricePresenter mMePricePresenter;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

                mSmsTv = mGetSmsTxt;
            if (smsCode == 0) {
                mSmsTv.setText(" 重新获取 ");
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

    public UserPhoneNumModifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_phone_num_modify, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPastPhoneNumEdit = mFindViewUtils.findViewById(R.id.phoneNum_past_edit);
        mPastPassWordEdit = mFindViewUtils.findViewById(R.id.passWord_past_edit);
        mNewPhoneNumEdit = mFindViewUtils.findViewById(R.id.phoneNum_new_edit);
        mSmsInputEdit = mFindViewUtils.findViewById(R.id.sms_input_edit);

        mGetSmsTxt = mFindViewUtils.findViewById(R.id.get_sms_txt);
        mLoginPresenter=new LoginPresenter(null);
        mMePricePresenter=new MePricePresenter(null);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mGetSmsTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_num = mPastPhoneNumEdit.getText().toString();
                String new_num = mNewPhoneNumEdit.getText().toString();
                if (!SmsBool) {
                    if (checkOutPhoneNum(old_num)) {
                        showAlertWithMsg("验证码已发送,请注意查收。");
                        Sms();
                        SmsBool = true;
                        requestSmsCode(new_num);
                    }
                }
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("修改联系方式");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("保存");
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkOutSmsNum()) {
                    modify_phoneNum();
                }
            }
        });
    }

    private void modify_phoneNum() {
        mMePricePresenter.modify_phoneNum(getText(mPastPhoneNumEdit), getText(mPastPassWordEdit),
                getText(mNewPhoneNumEdit), getText(mSmsInputEdit), new IViewUser<String>() {
            @Override
            public void onSuccess(String response) {
                hiddenLoading();
                showAlertWithMsg("修改手机号成功");
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

                    }
        });
    }
    private void requestSmsCode(String phoneNum) {
        if (checkOutPhoneNum(phoneNum)) {
            mLoginPresenter.getSmsCode(new IViewLogin<String>() {
                @Override
                public void onSuccess(String data) {
                    showAlertWithMsg(data);
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
                    Log.e("tag", volleyError+"" );
                }
            }, phoneNum, 2);
        }
    }
    protected boolean checkOutSmsNum(){
        String s =""+smsNum;
        if (!getText(mSmsInputEdit).equals(s)){
            showAlertWithMsg("请输入正确的验证码");
            return false;
        }
        return true;
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
    protected boolean checkOutPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            showAlertWithMsg("手机号不能为空");
            return false;
        }

        if (!CheckOutEdTxt.isPhoneNumber(phoneNum)) {
            showAlertWithMsg("请输入正确的手机号码");
            return false;
        }

        return true;
    }
}
