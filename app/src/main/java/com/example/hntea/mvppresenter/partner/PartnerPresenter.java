package com.example.hnTea.mvppresenter.partner;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.partner.IActionPartner;
import com.example.hnTea.mvpmodel.partner.PartnerData;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsDetailModel;
import com.example.hnTea.mvpmodel.partner.bean.PartnerNewsModel;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

import java.util.List;

/**
 * Created by 太能 on 2016/11/14.
 */
public class PartnerPresenter extends BasePresenter {
    private PartnerData mPartnerData;

    public PartnerPresenter(IViewBase iViewBase) {
        super(iViewBase);
        mPartnerData = new PartnerData();
    }

    public void getPartnerNewsData(final int page,
                                   final IViewPartner<List<PartnerNewsModel>> IViewPartner) {
        mPartnerData.getPartnerNewsData(page, new IActionPartner<List<PartnerNewsModel>>() {
            @Override
            public void success(List<PartnerNewsModel> data) {
                IViewPartner.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewPartner.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewPartner.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewPartner.onStart(var);
            }
        });
    }

    public void getPartnerEnterData(final String name, String phoneNum, String sms, final IViewPartner<String> iViewPartner) {
        mPartnerData.mPartnerEnterData(name, phoneNum, sms, new IActionPartner<String>() {
            @Override
            public void success(String data) {
                iViewPartner.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPartner.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPartner.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPartner.onStart(var);
            }
        });
    }

    public void getBusinessPartnerData(String name, String phoneNum, String address, final IViewPartner<String> iViewPartner) {
        mPartnerData.mBusinessPartnerData(name, phoneNum, address, new IActionPartner<String>() {
            @Override
            public void success(String data) {
                iViewPartner.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPartner.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPartner.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPartner.onStart(var);
            }
        });
    }

    public void getPartnerNewsDetail(final String id,
                                     final IViewPartner<PartnerNewsDetailModel> IViewPartner){
        mPartnerData.getPartnerNewsDetail(id, new IActionPartner<PartnerNewsDetailModel>() {
            @Override
            public void success(PartnerNewsDetailModel data) {
                IViewPartner.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewPartner.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewPartner.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewPartner.onStart(var);
            }
        });
    }
}

