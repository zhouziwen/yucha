package com.example.hnTea.ui.me.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.R;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.CommonAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvpmodel.user.bean.UserAddress;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.rxjava.eventBean.EventAddress;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.rxjava.eventBean.EventUserBean;
import com.example.hnTea.utils.ShowFragmentUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends BaseFragment {
    private UserConfigPresenter mUserConfigPresenter;
    private ListView mListView;
    private List<UserAddress> mData;
    private CommonAdapter<UserAddress> mAdapter;
    private TextView mAddAddressView;
    private AddAddressFragment mAddAddressFragment;
    private EditAddressFragment mEditAddressFragment;
    private List<UserAddress> mDataSource = new ArrayList<>();
    private Subscription mSubscription;
    private String type = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_address, container, false);
    }

    @Override
    public void onStart() {
        mSubscription = RxEventBus.getDefault().toObservable(UserAddress.class)
                .subscribe(userEvent -> {
                    for (UserAddress model : mDataSource) {
                        if (model.getId().equals(userEvent.getId())) {
                            requestAddressList();
                            return;
                        }
                    }
                    mDataSource.add(userEvent);
                    mData.add(userEvent);
                    mAdapter.update(mDataSource);
                }, throwable -> {
                });
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        setStateBar();
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        //type主要是看 收货地址是从哪进来的
        // type=1 代表从个人页面跳转过来的
        // type=2 代表确认订单 跳转过来的
        type = getArguments().getString("address_type");
        mUserConfigPresenter = new UserConfigPresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.manage_address_listView);
        mAddAddressView = mFindViewUtils.findViewById(R.id.add_new_address);
    }

    @Override
    protected void setListener() {
        mAddAddressView.setOnClickListener(v -> {
            if (mAddAddressFragment == null) {
                mAddAddressFragment = new AddAddressFragment();
            }
            ShowFragmentUtils.showFragment(getActivity(), mAddAddressFragment.getClass(),
                    FragmentTags.FRAGMENT_ADD_ADDRESS, null, true);
        });
        super.setListener();
    }

    @Override
    protected void setData() {
        super.setData();
        mAppTitleBar.getTitle().setText("管理收货地址");
        mAdapter = new CommonAdapter<UserAddress>(getContext(), null, R.layout.manage_address_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.manage_address_item_delete)
                        .setOnClickListener(R.id.manage_address_item_edit)
                        .setOnClickListener(R.id.layout_default)
                        .setOnClickListener(R.id.address_layout)
                ;
            }

            @Override
            protected void setViewDimen(View convertView) {
            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, UserAddress item) {
                holder.setText(R.id.manage_address_item_name, item.getReceiver())
                        .setText(R.id.manage_address_item_phoneNum, item.getTelephone())
                        .setText(R.id.manage_address_item_address, item.getAddress());
                if (item.getIs_default().equals("1")) {
                    holder.setImageRes(R.id.item_select, R.mipmap.buy_selector)
                            .setTextViewTextColor(R.id.item_select_txt, getResources().getColor(R.color.tipsColor));
                } else {
                    holder.setImageRes(R.id.item_select, R.mipmap.circle)
                            .setTextViewTextColor(R.id.item_select_txt, getResources().getColor(R.color.gray));
                }
            }

            @Override
            public void onClickBack(final int position, View view, BaseViewHolder holder) {
                switch (view.getId()) {
                    case R.id.manage_address_item_delete:
                        //删除地址
                        if (type.equals("2")) {
                            showAlertWithMsg("请前往我的地址页面删除选中地址");
                            return;
                        }
                        showDialogWithMsg("是否删除选中地址？");
                        mDialog.setLeftButtonListener(() -> mDialog.dismiss());
                        mDialog.setRightButtonListener(() -> {
                            //确定删除
                            mDialog.dismiss();
                            deleteAddress(position);
                        });
                        break;
                    case R.id.manage_address_item_edit:
                        if (type.equals("2")) {
                            showAlertWithMsg("请前往我的地址页面编辑选中地址");
                            return;
                        }
                        pushEditAddress(position);
                        break;
                    case R.id.layout_default:
                        //设置默认收获地址
                        if (type.equals("2")) {
                           postEvents(position);
                        }
                        setDefaultAddress(position);
                        break;
                    case R.id.address_layout:
                        if (type.equals("1")){
                            pushEditAddress(position);
                        }else {
                            postEvents(position);
                        }
                        break;
                }
            }
        };
        mListView.setAdapter(mAdapter);
        requestAddressList();
    }

    private void postEvents(int position){
        EventAddress eventAddress = new EventAddress(null, null, null, null, null, null);
        eventAddress.setId(mData.get(position).getId());
        eventAddress.setAddress(mData.get(position).getAddress());
        eventAddress.setIs_default(mData.get(position).getIs_default());
        eventAddress.setPostcode(mData.get(position).getPostcode());
        eventAddress.setReceiver(mData.get(position).getReceiver());
        eventAddress.setTelephone(mData.get(position).getTelephone());
        RxEventBus.getDefault().postEvent(eventAddress);
        popSelf();
    }

    private void requestAddressList() {
        mUserConfigPresenter.getAddressList(new IViewUser<List<UserAddress>>() {
            @Override
            public void onSuccess(List<UserAddress> response) {
                mData = response;
                hiddenLoading();
                mAdapter.update(response);
                mDataSource.addAll(response);
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

    private void setDefaultAddress(final int position) {
        mUserConfigPresenter.getSetDefaultAddress(mDataSource.get(position).getId(),
                new IViewUser<String>() {
                    @Override
                    public void onSuccess(String response) {
                        if (type.equals("1")) {
                            showAlertWithMsg("已设置");
                        }
                        PreManager.instance().saveString("address", mDataSource.get(position).getAddress());
                        RxEventBus.getDefault().postEvent(
                                new EventUserBean(
                                        MyApplication.getUserNickName(),
                                        mDataSource.get(position).getAddress()));
                        requestAddressList();
                    }

                    @Override
                    public void onPhpFail(String var) {
                        showAlertWithMsg(var);
                    }

                    @Override
                    public void onStart(String var) {
                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        showAlertWithMsg("请检查网络");
                    }
                });
    }

    private void deleteAddress(int position) {
        mUserConfigPresenter.getDeleteAddress(mDataSource.get(position).getId(),
                new IViewUser<String>() {
                    @Override
                    public void onSuccess(String response) {
                        showAlertWithMsg("已删除");
                        requestAddressList();
                    }

                    @Override
                    public void onPhpFail(String var) {
                        showAlertWithMsg(var);
                    }

                    @Override
                    public void onStart(String var) {

                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        showAlertWithMsg("请检查网络");
                    }
                });
    }

    private void pushEditAddress(int position) {
        if (mEditAddressFragment == null) {
            mEditAddressFragment = new EditAddressFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailAddress", mDataSource.get(position));
        ShowFragmentUtils.showFragment(getActivity(), mEditAddressFragment.getClass(),
                FragmentTags.FRAGMENT_EDIT_ADDRESS, bundle, true);
    }

    //一定要在生命周期結束后 取消订阅事件
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
