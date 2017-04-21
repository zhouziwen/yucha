package com.example.hnTea.mvppresenter.user;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.user.IActionUser;
import com.example.hnTea.mvpmodel.user.UserHelperData;
import com.example.hnTea.mvpmodel.user.bean.MyInfo;
import com.example.hnTea.mvpmodel.user.bean.UserhelperModel;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

/**
 * Created by jason_syf on 2017/4/20.
 * Email: jason_sunyf@163.com
 */

public class UserHelperPresenter extends BasePresenter {
    private UserHelperData mUserHelperData;

    public UserHelperPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mUserHelperData=new UserHelperData();
    }

    public void getUserHelperData(String tag,final IViewUser<UserhelperModel> iActionUser) {
        mUserHelperData.getUserHelperData(tag, new IActionUser<UserhelperModel>() {
            @Override
            public void success(UserhelperModel response) {
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
}
