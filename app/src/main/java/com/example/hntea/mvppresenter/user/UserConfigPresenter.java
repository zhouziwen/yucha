package com.example.hnTea.mvppresenter.user;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.user.IActionUser;
import com.example.hnTea.mvpmodel.user.UserConfigData;
import com.example.hnTea.mvpmodel.user.bean.HelperModel;
import com.example.hnTea.mvpmodel.user.bean.MyCollectModel;
import com.example.hnTea.mvpmodel.user.bean.MyInfo;
import com.example.hnTea.mvpmodel.user.bean.MyOrderModel;
import com.example.hnTea.mvpmodel.user.bean.UserAddress;
import com.example.hnTea.mvpmodel.user.bean.VersionModel;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

import java.util.List;

/**
 * Created by 太能 on 2016/12/23.
 */
public class UserConfigPresenter extends BasePresenter {
    private UserConfigData mUserConfigData;

    public UserConfigPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mUserConfigData = new UserConfigData();
    }

        public void getUserInfo(final IViewUser<MyInfo> iActionUser) {
        mUserConfigData.getUserInfo(new IActionUser<MyInfo>() {
            @Override
            public void success(MyInfo response) {
                iActionUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iActionUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iActionUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iActionUser.onStart(var);
            }
        });
    }


    public void getChangeUserNickName(final String changeUserName,
                                      final IViewUser<String> iActionUser) {
        mUserConfigData.getChangeUserNickName(changeUserName, new IActionUser<String>() {
            @Override
            public void success(String response) {
                iActionUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iActionUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iActionUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iActionUser.onStart(var);
            }
        });
    }

    public void getChangeUserPhoneNumber(final String old_num,
                                         final String old_psw,
                                         final String new_num,
                                         final String sms,
                                         final IViewUser<String> iActionUser) {
        mUserConfigData.getChangeUserPhoneNumber(old_num,
                old_psw,
                new_num,
                sms,
                new IActionUser<String>() {
                    @Override
                    public void success(String response) {
                        iActionUser.onSuccess(response);
                    }

                    @Override
                    public void phpFail(String var) {
                        iActionUser.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        iActionUser.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        iActionUser.onStart(var);
                    }
                });
    }

    public void getChangeUserPsw(final String old_psw,
                                 final String new_psw,
                                 final String result_psw,
                                 final IViewUser<String> iActionUser) {
        mUserConfigData.getChangeUserPsw(old_psw,
                new_psw,
                result_psw,
                new IActionUser<String>() {
                    @Override
                    public void success(String response) {
                        iActionUser.onSuccess(response);
                    }

                    @Override
                    public void phpFail(String var) {
                        iActionUser.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        iActionUser.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        iActionUser.onStart(var);
                    }
                });
    }

    public void getAddAddress(final String receiver,
                              final String telephone,
                              final String address,
                              final String postcode,
                              final String defaultCode,
                              final IViewUser<String> IViewUser) {
        mUserConfigData.getAddAddress(receiver,
                telephone,
                address,
                postcode,
                defaultCode,
                new IActionUser<String>() {
                    @Override
                    public void success(String response) {
                        IViewUser.onSuccess(response);
                    }

                    @Override
                    public void phpFail(String var) {
                        IViewUser.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        IViewUser.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        IViewUser.onStart(var);
                    }
                });
    }


    public void getAddressList(final IViewUser<List<UserAddress>> IViewUser) {
        mUserConfigData.getAddressList(new IActionUser<List<UserAddress>>() {
            @Override
            public void success(List<UserAddress> response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }


    public void getEditAddress(final String id,
                               final String receiver,
                               final String telephone,
                               final String address,
                               final String postcode,
                               final String defaultCode,
                               final IViewUser<String> IViewUser) {
        mUserConfigData.getEditAddress(id,
                receiver,
                telephone,
                address,
                postcode,
                defaultCode,
                new IActionUser<String>() {
                    @Override
                    public void success(String response) {
                        IViewUser.onSuccess(response);
                    }

                    @Override
                    public void phpFail(String var) {
                        IViewUser.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        IViewUser.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        IViewUser.onStart(var);
                    }
                });
    }

    public void getSetDefaultAddress(final String id,
                                     final IViewUser<String> IViewUser) {
        mUserConfigData.getSetDefaultAddress(id, new IActionUser<String>() {
            @Override
            public void success(String response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }


    public void getDeleteAddress(final String id,
                                 final IViewUser<String> IViewUser) {
        mUserConfigData.getDeleteAddress(id, new IActionUser<String>() {
            @Override
            public void success(String response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }


    public void getFeedBack(final String content,
                            final IViewUser<String> IViewUser) {
        mUserConfigData.getFeedBack(content, new IActionUser<String>() {
            @Override
            public void success(String response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }

    public void checkToken(final IViewUser<String> IViewUser) {
        mUserConfigData.checkToken(new IActionUser<String>() {
            @Override
            public void success(String response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }

    public void checkVersion(final String version, final IViewUser<VersionModel> IViewUser) {
        mUserConfigData.checkVersion(version, new IActionUser<VersionModel>() {
            @Override
            public void success(VersionModel response) {
                IViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                IViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewUser.onStart(var);
            }
        });
    }

    public void getMyCollect(final String page, final IViewUser<MyCollectModel> iViewUser) {
        mUserConfigData.getMyCollect(page, new IActionUser<MyCollectModel>() {
            @Override
            public void success(MyCollectModel response) {
                iViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewUser.onStart(var);
            }
        });
    }

    public void getMyOrder(String type, String page, String limit, IViewUser<List<MyOrderModel>> iViewUser) {
        mUserConfigData.getMyOrder(type, page, limit, new IActionUser<List<MyOrderModel>>() {
            @Override
            public void success(List<MyOrderModel> response) {
                iViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewUser.onStart(var);
            }
        });
    }

    public void getHelperMsg(IViewUser<List<HelperModel>> iViewUser){
        mUserConfigData.getHelperMsg(new IActionUser<List<HelperModel>>() {
            @Override
            public void success(List<HelperModel> response) {
                iViewUser.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iViewUser.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewUser.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewUser.onStart(var);
            }
        });
    }
}
