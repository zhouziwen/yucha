package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.android.volley.VolleyError;
import com.example.hnTea.R;
import com.example.hnTea.mvpmodel.user.bean.UserAddress;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAddressFragment extends BaseFragment {
    private EditText mReceiverAddressEdit,mReceiverPhoneNumEdit,mPostNumEdit,mAddressDetailEdit;
    private Switch mSwitch;
    private UserConfigPresenter mUserConfigPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_address, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter =new UserConfigPresenter(null);
        mReceiverAddressEdit = mFindViewUtils.findViewById(R.id.receiver_address_edit);
        mReceiverPhoneNumEdit = mFindViewUtils.findViewById(R.id.receiver_phoneNum_edit);
        mPostNumEdit = mFindViewUtils.findViewById(R.id.postNum_edit);
        mAddressDetailEdit = mFindViewUtils.findViewById(R.id.address_detail_edit);
        mSwitch = mFindViewUtils.findViewById(R.id.add_new_address_switch);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("新增地址");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("保存");
    }

    //保存 添加收货地址
    private void addAddress(){
        if (checkOutEdTxt()){
            String isDefault="";
            if (mSwitch.isChecked()){
                isDefault="1";
            }else {
                isDefault="0";
            }
            final String finalIsDefault = isDefault;
            mUserConfigPresenter.getAddAddress(getText(mReceiverAddressEdit),
                    getText(mReceiverPhoneNumEdit),
                    getText(mAddressDetailEdit),
                    getText(mPostNumEdit),
                    finalIsDefault,
                    new IViewUser<String>() {
                        @Override
                        public void onSuccess(String response) {
                            hiddenLoading();
                            hideSoftKeyboard();
                            showAlertWithMsg(response);
                            RxEventBus.getDefault().postEvent(new UserAddress("",
                                    getText(mReceiverPhoneNumEdit),
                                    getText(mReceiverAddressEdit),
                                    getText(mAddressDetailEdit),
                                    getText(mPostNumEdit),
                                    finalIsDefault
                                    ));
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    popSelf();
                                }
                            },1000);
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

    private boolean checkOutEdTxt(){
        if (TextUtils.isEmpty(getText(mReceiverAddressEdit))){
            showAlertWithMsg("收货人不能为空");
            return false;
        }

        if (TextUtils.isEmpty(getText(mReceiverPhoneNumEdit))){
            showAlertWithMsg("收货人手机号码不能为空");
            return false;
        }

        if (TextUtils.isEmpty(getText(mAddressDetailEdit))){
            showAlertWithMsg("请输入详细地址");
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }
}
