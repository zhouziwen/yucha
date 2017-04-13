package com.example.hnTea.ui.partner;


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
import com.example.hnTea.mvppresenter.partner.IViewPartner;
import com.example.hnTea.mvppresenter.partner.PartnerPresenter;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.utils.CheckOutEdTxt;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPartnerFragment extends BaseFragment implements View.OnClickListener {
    private TextView mSingle, mTeam;
    private TextView addPartner;
    private int type = 1;
    private PartnerPresenter mPartnerPresenter;
    private LoginPresenter mLoginPresenter;
    private int smsNum;
    private TextView mSmsTv;
    private View mPersonView, mConpanyView;
    private EditText mPersonNameEdit,mPersonPhoneNumEdit, mPersonSmsEdit;
    private EditText mConpanyNameEdit,mCompanyContactStyleEdit,mCompanyAddressEdit;
    private TextView mPersonSmsText ;
    private Timer mTimer;
    private boolean SmsBool = false;
    private int smsCode = 60;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (type == 1) {
                mSmsTv = mPersonSmsText;
            } else {

            }
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

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_partner, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAppTitleBar.getTitle().setText("合伙人报名");
        //合伙人 姓名  手机 验证码 editText
        mPersonNameEdit = mFindViewUtils.findViewById(R.id.include_partner_name_edit);
        mPersonPhoneNumEdit = mFindViewUtils.findViewById(R.id.include_partner_phoneNum_edit);
        mPersonSmsEdit = mFindViewUtils.findViewById(R.id.include_partner_smsNum_edit);
        mPersonSmsText = mFindViewUtils.findViewById(R.id.include_partner_get_sms_txt);
        mPersonView = mFindViewUtils.findViewById(R.id.layout1);
        //合做伙伴 公司名称   公司地址  联系方式   editText
        mConpanyNameEdit = mFindViewUtils.findViewById(R.id.include_businessPartner_name_edit);
        mCompanyContactStyleEdit = mFindViewUtils.findViewById(R.id.include_businessPartner_phoneNum_edit);
        mCompanyAddressEdit = mFindViewUtils.findViewById(R.id.include_businessPartner_address_edit);
        mConpanyView = mFindViewUtils.findViewById(R.id.layout2);
        addPartner = mFindViewUtils.findViewById(R.id.add_partner_add);
        mSingle = mFindViewUtils.findViewById(R.id.addPartner_single);
        mTeam = mFindViewUtils.findViewById(R.id.addPartner_team);
        mPartnerPresenter = new PartnerPresenter(null);
        mLoginPresenter = new LoginPresenter(null);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSingle.setOnClickListener(this);
        mTeam.setOnClickListener(this);
        addPartner.setOnClickListener(this);
        mPersonSmsText.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        super.setData();


    }

    private void requestSmsCode(String phoneNum) {
        if (checkOutPhoneNum(phoneNum)) {
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
                    Log.e("tag", volleyError+"" );
                }
            }, phoneNum, 2);
        }
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
   protected boolean checkOutSmsNum(){
       String s =""+smsNum;
       if (getText(mPersonSmsEdit).equals(s)){
           showAlertWithMsg("请输入正确的验证码");
           return false;
       }
       return true;
   }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_partner_get_sms_txt:
                String phoneNum = mPersonPhoneNumEdit.getText().toString();
                if (!SmsBool) {
                    if (checkOutPhoneNum(phoneNum)) {
                    showAlertWithMsg("验证码已发送,请注意查收。");
                    Sms();
                    SmsBool = true;
                    requestSmsCode(phoneNum);
                    }
                }
                break;
            case R.id.addPartner_single:
                //合伙人
                mPersonView.setVisibility(View.VISIBLE);
                mConpanyView.setVisibility(View.GONE);
                changeTvColor(1);
                type = 1;
                break;
            case R.id.addPartner_team:
                //合作伙伴
                mPersonView.setVisibility(View.GONE);
                mConpanyView.setVisibility(View.VISIBLE);
                changeTvColor(2);
                type = 2;
                break;
            case R.id.add_partner_add:
                checkOutNum();
                break;
        }
    }

    private void partnerJoin() {
        if (type == 1) {
            mPartnerPresenter.getPartnerEnterData(getText(mPersonNameEdit),
                    getText(mPersonPhoneNumEdit),
                    getText(mPersonSmsEdit),
                    new IViewPartner<String>() {
                @Override
                public void onSuccess(String data) {
                        hiddenLoading();
                        showAlertWithMsg("加入成功");
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
        } else {
            mPartnerPresenter.getBusinessPartnerData(getText(mConpanyNameEdit),
                    getText(mCompanyContactStyleEdit),
                    getText(mCompanyAddressEdit),
                    new IViewPartner<String>() {
                @Override
                public void onSuccess(String data) {
                        hiddenLoading();
                        showAlertWithMsg("加入成功");
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
    }

    private void checkOutNum() {
        switch (type) {
            case 1:
                //single
                if ( checkOutOtherNum(mPersonNameEdit, mPersonPhoneNumEdit,null)) {
                    if (checkOutSmsNum()) {
                        partnerJoin();
                    }
                }
                break;
            case 2:
                //team
                if (checkOutOtherNum(mConpanyNameEdit,mCompanyAddressEdit,mCompanyContactStyleEdit)) {
                    if (checkOutSmsNum()) {
                        partnerJoin();
                    }
                }
                break;
        }
    }
    private boolean checkOutOtherNum(EditText editText,EditText editText1,EditText editText2){

        if (TextUtils.isEmpty(getText(editText))){
            showAlertWithMsg(editText.getHint().toString());
            return false;
        }
        if (TextUtils.isEmpty(getText(editText1))) {
            showAlertWithMsg(editText1.getHint().toString());
            return false;
        }
        if (editText2 != null) {
            if (TextUtils.isEmpty(getText(editText2))) {
                showAlertWithMsg(editText2.getHint().toString());
            }
        }
        return true;
    }

    private void changeTvColor(int position) {
        if (position == 1) {
            mSingle.setTextColor(getResources().getColor(R.color.shopColor));
            mTeam.setTextColor(getResources().getColor(R.color.price_textColor_pressed));
        } else {
            mSingle.setTextColor(getResources().getColor(R.color.price_textColor_pressed));
            mTeam.setTextColor(getResources().getColor(R.color.shopColor));
        }
    }    //根据索引值返回不同的数据

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
