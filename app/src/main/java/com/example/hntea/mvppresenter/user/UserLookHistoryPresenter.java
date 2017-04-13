package com.example.hnTea.mvppresenter.user;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.user.IActionUser;
import com.example.hnTea.mvpmodel.user.UserLookHistoryData;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;
import com.example.hnTea.ui.me.bean.HistoryModel;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/15.
 * Email: jason_sunyf@163.com
 */

public class UserLookHistoryPresenter extends BasePresenter {
    private UserLookHistoryData mUserLookHistoryData;

    public UserLookHistoryPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mUserLookHistoryData = new UserLookHistoryData();
    }

    public void getUserHistory(final String goodsId, final IViewUser<List<HistoryModel>> iViewUser) {
        mUserLookHistoryData.getUserHistory(goodsId, new IActionUser<List<HistoryModel>>() {
            @Override
            public void success(List<HistoryModel> response) {
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
