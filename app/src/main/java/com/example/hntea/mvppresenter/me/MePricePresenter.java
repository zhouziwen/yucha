package com.example.hnTea.mvppresenter.me;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.me.IActionMePrice;
import com.example.hnTea.mvpmodel.me.MePriceData;
import com.example.hnTea.mvpmodel.me.bean.Detail_B_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_J_Model;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_Project_offer_detail;
import com.example.hnTea.mvpmodel.me.bean.Detail_X_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_B_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_B_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_J_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_J_SingleModel;
import com.example.hnTea.mvpmodel.me.bean.Me_X_ProjectModel;
import com.example.hnTea.mvpmodel.me.bean.Me_X_SingleModel;
import com.example.hnTea.mvpmodel.price.IActionPrice;
import com.example.hnTea.mvpmodel.user.IActionUser;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;
import com.example.hnTea.mvppresenter.user.IViewUser;

import java.util.List;

/**
 * Created by 太能 on 2016/11/15.
 */
public class MePricePresenter extends BasePresenter {
    private MePriceData mMePriceData;

    public MePricePresenter(IViewBase iViewBase) {
        super(iViewBase);
        mMePriceData = new MePriceData();
    }

    //修改手机号
    public void modify_phoneNum(final String old_userphone, final String password, final String new_userphone,
                                final String sms, final IViewUser<String> iViewModifyNum) {
        mMePriceData.modify_phoneNum(old_userphone, password, new_userphone, sms, new IActionUser<String>() {
            @Override
            public void success(String response) {
                iViewModifyNum.onSuccess(response);
            }

            @Override
            public void phpFail(String var) {
                iViewModifyNum.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewModifyNum.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewModifyNum.onStart(var);
            }
        });

    }

    //我的产品询价单
    public void getMe_X_SingleListData(final int page,
                                       final IViewMePrice<List<Me_X_SingleModel>> IViewMePrice) {
        mMePriceData.getMe_X_SingleListData(page, new IActionPrice<List<Me_X_SingleModel>>() {
            @Override
            public void success(List<Me_X_SingleModel> data) {
                IViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewMePrice.onStart(var);
            }
        });
    }

    //我的项目询价单
    public void getMe_X_ProjectListData(final int page,
                                        final IViewMePrice<List<Me_X_ProjectModel>> IViewMePrice) {
        mMePriceData.getMe_X_ProjectListData(page, new IActionPrice<List<Me_X_ProjectModel>>() {
            @Override
            public void success(List<Me_X_ProjectModel> data) {
                IViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewMePrice.onStart(var);
            }
        });
    }

    //我的项目询价单  报价详情
    public void getMeDetail_X_Project_Offer_detail(String offer_sn, final IViewMePrice<Detail_X_Project_offer_detail> iViewMePrice) {
        mMePriceData.getMeDetail_X_Project_Offer_detail(offer_sn, new IActionMePrice<Detail_X_Project_offer_detail>() {
            @Override
            public void success(Detail_X_Project_offer_detail data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    //我的产品报价单
    public void getMe_B_Single(final int page,
                               final IViewMePrice<List<Me_B_SingleModel>> iViewMePrice) {
        mMePriceData.getMe_B_Single(page, new IActionMePrice<List<Me_B_SingleModel>>() {
            @Override
            public void success(List<Me_B_SingleModel> data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });

    }

    //我的项目报价单
    public void getMe_B_Project(final int page,
                                final IViewMePrice<List<Me_B_ProjectModel>> iViewMePrice) {
        mMePriceData.getMe_B_Project(page, new IActionMePrice<List<Me_B_ProjectModel>>() {
            @Override
            public void success(List<Me_B_ProjectModel> data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });

    }

    //我的产品竟价单
    public void getMe_J_Single(int page, int limit,
                               final IViewMePrice<List<Me_J_SingleModel>> iViewMePrice) {
        mMePriceData.getMe_J_Single(page, limit, new IActionMePrice<List<Me_J_SingleModel>>() {
            @Override
            public void success(List<Me_J_SingleModel> data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    //我的项目竟价单
    public void getMe_J_Project(int page, int limit,
                                final IViewMePrice<List<Me_J_ProjectModel>> iViewMePrice) {
        mMePriceData.getMe_J_Project(page, limit, new IActionMePrice<List<Me_J_ProjectModel>>() {
            @Override
            public void success(List<Me_J_ProjectModel> data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    //我的产品询价单详情
    public void getMeDetail_X_Single(final String inquiry_sn,
                                     final IViewMePrice<Detail_X_SingleModel> IViewMePrice) {
        mMePriceData.getMeDetail_X_Single(inquiry_sn, new IActionMePrice<Detail_X_SingleModel>() {
            @Override
            public void success(Detail_X_SingleModel data) {
                IViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewMePrice.onStart(var);
            }
        });
    }

    //我的项目询价单详情
    public void getMeDetail__X_Project(final String inquiry_sn,
                                       final IViewMePrice<Detail_X_ProjectModel> iViewMePrice) {
        mMePriceData.getMeDetail_X_Project(inquiry_sn, new IActionMePrice<Detail_X_ProjectModel>() {
            @Override
            public void success(Detail_X_ProjectModel data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    public void getMeDetail_B_Single(final String inquiry_sn, final IViewMePrice<Detail_B_SingleModel> iViewMePrice) {
        mMePriceData.getMeDetail_B_Single(inquiry_sn, new IActionMePrice<Detail_B_SingleModel>() {
            @Override
            public void success(Detail_B_SingleModel data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    public void getMeDetail_B_Project(final String inquiry_sn, final IViewMePrice<Detail_B_SingleModel> iViewMePrice) {
        mMePriceData.getMeDetail_B_Project(inquiry_sn, new IActionMePrice<Detail_B_SingleModel>() {
            @Override
            public void success(Detail_B_SingleModel data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }

    public void getMeDetail_J_Data(final String inquiry_sn, String method, final IViewMePrice<Detail_J_Model> iViewMePrice) {
        mMePriceData.getMeDetail_J(inquiry_sn, method, new IActionMePrice<Detail_J_Model>() {
            @Override
            public void success(Detail_J_Model data) {
                iViewMePrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewMePrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewMePrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewMePrice.onStart(var);
            }
        });
    }
}
