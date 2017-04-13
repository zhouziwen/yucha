package com.example.hnTea.ui.me.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
public class EditAddressFragment extends BaseFragment {
    private UserConfigPresenter mUserConfigPresenter;
    private EditText mUserName, mUserPhone, mUserPostCode, mUserDetailAddress;
    private UserAddress mUserAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_address, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter = new UserConfigPresenter(null);
        mUserName = mFindViewUtils.findViewById(R.id.receiver_address_edit);
        mUserPhone = mFindViewUtils.findViewById(R.id.receiver_phoneNum_edit);
        mUserPostCode = mFindViewUtils.findViewById(R.id.postNum_edit);
        mUserDetailAddress = mFindViewUtils.findViewById(R.id.address_detail_edit);
    }   

    @Override
    protected void setListener() {
        super.setListener();
        mAppTitleBar.getAction().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存
                saveDetailAddress();
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("编辑地址");
        mAppTitleBar.getAction().setVisibility(View.VISIBLE);
        mAppTitleBar.getAction().setText("保存");
        Bundle bundle = getArguments();
        mUserAddress = (UserAddress) bundle.get("detailAddress");
        mUserName.setText(mUserAddress.getReceiver());
        mUserPhone.setText(mUserAddress.getTelephone());
        mUserPostCode.setText(mUserAddress.getPostcode());
        mUserDetailAddress.setText(mUserAddress.getAddress());

    }

    private void saveDetailAddress() {
        mUserConfigPresenter.getEditAddress(mUserAddress.getId(),
                getText(mUserName),
                getText(mUserPhone),
                getText(mUserDetailAddress),
                getText(mUserPostCode),
                mUserAddress.getIs_default(),
                new IViewUser<String>() {
                    @Override
                    public void onSuccess(String response) {
                        hiddenLoading();
                        hideSoftKeyboard();
                        showAlertWithMsg("已保存");
                        RxEventBus.getDefault().postEvent(new UserAddress(mUserAddress.getId(),
                                getText(mUserName),
                                getText(mUserPhone),
                                getText(mUserDetailAddress),
                                getText(mUserPostCode),
                                mUserAddress.getIs_default()));
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

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }
}
