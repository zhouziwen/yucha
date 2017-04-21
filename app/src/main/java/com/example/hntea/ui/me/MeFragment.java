package com.example.hnTea.ui.me;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.hnTea.MyApplication;
import com.example.hnTea.adapter.BaseViewHolder;
import com.example.hnTea.adapter.DimenAdapter;
import com.example.hnTea.apcontains.FragmentTags;
import com.example.hnTea.https.BaseUrl;
import com.example.hnTea.manager.PreManager;
import com.example.hnTea.mvpmodel.me.bean.MeListModel;
import com.example.hnTea.mvppresenter.user.IViewUser;
import com.example.hnTea.mvppresenter.user.UserConfigPresenter;
import com.example.hnTea.rxjava.RxEventBus;
import com.example.hnTea.ui.BaseFragment;
import com.example.hnTea.R;
import com.example.hnTea.ui.login.LoginActivity;
import com.example.hnTea.ui.me.user.MyBillFragment;
import com.example.hnTea.ui.me.user.MyOrderFragment;
import com.example.hnTea.ui.me.user.UserContentFragment;
import com.example.hnTea.ui.me.user.UserInfoFragment;
import com.example.hnTea.rxjava.eventBean.EventLogout;
import com.example.hnTea.rxjava.eventBean.EventUserBean;
import com.example.hnTea.ui.me.user.helper.HelperFragment;
import com.example.hnTea.utils.ShowFragmentUtils;
import com.example.hnTea.utils.UMShareUtils;
import com.example.hnTea.widget.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

public class MeFragment extends BaseFragment {
    private UserConfigPresenter mUserConfigPresenter;
    private ListView mListView;
    private TextView mUserPhone;
    private View headerView;
    private DimenAdapter<MeListModel> mAdapter;
    private ImageView mUserIcon;
    private UserInfoFragment mUserInfoFragment;
    private UserContentFragment mUserContentFragment;
    private HistoryFragment mHistoryFragment;
    private MyOrderFragment mMyOrderFragment;
    private MyBillFragment mBillFragment;
    private HelperFragment mHelperFragment;
    private MyCollectFragment mMyCollectFragment;
    private MyInquiryFragment mMyInquiryFragment;
    private Subscription mSubscription;

    @Override
    public void onStart() {
        //接收到用户昵称的更新
        mSubscription = RxEventBus.getDefault().toObservable(EventUserBean.class)
                .subscribe(new Action1<EventUserBean>() {
                               @Override
                               public void call(EventUserBean userEvent) {
                                   mUserPhone.setText(userEvent.getNickName());
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                            }
                        });
        //接受到用户头像改变的通知
        mSubscription = RxEventBus.getDefault().toObservable(Bitmap.class)
                .subscribe(new Action1<Bitmap>() {
                               @Override
                               public void call(Bitmap bitmap) {
                                   mUserIcon.setImageBitmap(bitmap);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
        //退出登录的通知
        mSubscription = RxEventBus.getDefault().toObservable(EventLogout.class)
                .subscribe(new Action1<EventLogout>() {
                               @Override
                               public void call(EventLogout bitmap) {
                                   mUserPhone.setText("您尚未登录，点击登录");
                                   mUserIcon.setImageResource(R.mipmap.default_user);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MyApplication.getInstance().isLogin()) {
            if (TextUtils.isEmpty(MyApplication.getUserNickName())) {
                mUserPhone.setText(MyApplication.getUserName());
            } else {
                mUserPhone.setText(MyApplication.getUserNickName());
            }
            if (MyApplication.getInstance().getUserLocalHeader() != null) {
                mUserIcon.setImageBitmap(MyApplication.getInstance().getUserLocalHeader());
            } else {
                mUserIcon.setImageResource(R.mipmap.default_user);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mUserConfigPresenter = new UserConfigPresenter(null);
        mListView = mFindViewUtils.findViewById(R.id.me_listView);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        headerView = inflater.inflate(R.layout.me_list_header, null);
        mUserPhone = (TextView) headerView.findViewById(R.id.me_userPhone);
        mUserIcon = (ImageView) headerView.findViewById(R.id.me_listHeader_icon);
        mListView.addHeaderView(headerView);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击用户头像事件
                if (MyApplication.getInstance().isLogin()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    //我的信息
                    if (toLogin()) {
                        toCheckToken();
                        if (mUserContentFragment == null) {
                            mUserContentFragment = new UserContentFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mUserContentFragment.getClass(),
                                FragmentTags.FRAGMENT_USER_CONTENT,
                                null, true);
                    }
                }
            }
        });
    }

    @Override
    protected void setData() {
        super.setData();
        if (!TextUtils.isEmpty(PreManager.instance().getString("userName"))) {
            mUserPhone.setText(PreManager.instance().getString("userName"));
        }
        List<MeListModel> list = new ArrayList<>();
        list.add(new MeListModel(R.mipmap.me_listitem1, "我的订单", false));
        list.add(new MeListModel(R.mipmap.me_listitem2, "我的询价单", false));
        list.add(new MeListModel(R.mipmap.me_listitem3, "我的发票", true));

        list.add(new MeListModel(R.mipmap.me_listitem4, "我的收藏", false));
        list.add(new MeListModel(R.mipmap.me_listitem6, "浏览记录", true));

        list.add(new MeListModel(R.mipmap.me_listitem7, "帮助中心", false));
        list.add(new MeListModel(R.mipmap.me_user_setting, "我的设置", false));
        list.add(new MeListModel(R.mipmap.me_user_fenxiang, "软件分享", true));
        mAdapter = new DimenAdapter<MeListModel>(getContext(), list, R.layout.me_list_item) {
            @Override
            protected void setCellDiv(BaseViewHolder holder, MeListModel item) {
                View v = holder.getView(R.id.me_list_utilView2);
                View v1 = holder.getView(R.id.me_list_utilView);
                if (!item.isShow()) {
                    v.setVisibility(View.GONE);
                } else {
                    v1.setVisibility(View.GONE);
                }
            }

            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {
                holder.setOnClickListener(R.id.me_list_layout);
            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, MeListModel item) {
                holder.setText(R.id.me_list_title, item.getTitle())
                        .setImageRes(R.id.me_list_icon, item.getImage());
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {
                switch (position) {
                    case 0:
                        //我的订单
                        if (toLogin()) {
                            toCheckToken();
                            if (mMyOrderFragment == null) {
                                mMyOrderFragment = new MyOrderFragment();
                            }
                            ShowFragmentUtils.showFragment(getActivity(),
                                    mMyOrderFragment.getClass(),
                                    "", null, true);
                        }
                        break;
                    case 1:
                        //我的询价单
                        if (toLogin()) {
                            toCheckToken();
                            if (mMyInquiryFragment == null) {
                                mMyInquiryFragment = new MyInquiryFragment();
                            }
                            ShowFragmentUtils.showFragment(getActivity(),
                                    mMyInquiryFragment.getClass(),
                                    "   ",
                                    null, true);
                        }
                        break;
                    case 2:
                        //我的发票
                        if (toLogin()) {
                            toCheckToken();
                            if (mBillFragment == null) {
                                mBillFragment = new MyBillFragment();
                            }
                            ShowFragmentUtils.showFragment(getActivity(),
                                    mBillFragment.getClass(),
                                    FragmentTags.FRAGMENT_MY_BILL,
                                    null, true);
                        }
                        break;
                    case 3:
                        //我的收藏
                        if (toLogin()) {
                            toCheckToken();
                            if (mMyCollectFragment == null) {
                                mMyCollectFragment = new MyCollectFragment();
                            }
                            ShowFragmentUtils.showFragment(getActivity(),
                                    mMyCollectFragment.getClass(), "", null, true);
                        }
                        break;
                    case 4:
                        //浏览记录
                            if (mHistoryFragment == null) {
                                mHistoryFragment = new HistoryFragment();
                            }
                            ShowFragmentUtils.showFragment(getActivity(),
                                    mHistoryFragment.getClass(),
                                    FragmentTags.FRAGMENT_ME_HISTORY,
                                    null, true);
                        break;
                    case 5:
                        //帮助中心
                        if (mHelperFragment == null) {
                            mHelperFragment = new HelperFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mHelperFragment.getClass(),
                                "helper", null, true);
                        break;
                    case 6:
                        //我的设置
                       // toCheckToken();
                        if (mUserInfoFragment == null) {
                            mUserInfoFragment = new UserInfoFragment();
                        }
                        ShowFragmentUtils.showFragment(getActivity(),
                                mUserInfoFragment.getClass(),
                                FragmentTags.FRAGMENT_USER_INFO,
                                null, true);
                        break;
                    case 7:
                        //软件分享
                        UMShareUtils shareUtils = new UMShareUtils(getActivity(),
                                getContext(),
                                R.mipmap.ycicon,
                                BaseUrl.getUMShareApUrl(),
                                "豫茶APP下载",
                                "豫茶-茶叶行业询价交易平台,为您提供一站式采购服务。") {
                        };
                        shareUtils.openShareDialog();
                        break;
                }
            }
        };
        mListView.setAdapter(mAdapter);
    }

    private boolean toLogin() {
        if (MyApplication.getInstance().isLogin()) {
            showDialogWithMsg("您尚未登录是否前去登录?");
            mDialog.setLeftButtonListener(new BaseDialog.LeftListener() {
                @Override
                public void onLeftListener() {
                    mDialog.dismiss();
                }
            });
            mDialog.setRightButtonListener(new BaseDialog.RightListener() {
                @Override
                public void onRightListener() {
                    mDialog.dismiss();
                    //跳转登录
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            return false;
        }
        return true;
    }

    @Override
    public boolean onBackPressed() {
        popSelf();
        return super.onBackPressed();
    }

    //一定要在生命周期結束后 取消訂閱事件
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void toCheckToken() {
        mUserConfigPresenter.checkToken(new IViewUser<String>() {
            @Override
            public void onSuccess(String response) {
                hiddenLoading();
            }

            @Override
            public void onPhpFail(String var) {
                hiddenLoading();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                showAlertWithMsg(var);
                mUserPhone.setText("您尚未登录，点击登录");
                mUserIcon.setImageResource(R.mipmap.default_user);
                MyApplication.getInstance().logOut();
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
