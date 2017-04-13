package com.example.hnTea.mvppresenter.price;

import com.android.volley.VolleyError;
import com.example.hnTea.mvpmodel.price.IActionPrice;
import com.example.hnTea.mvpmodel.price.PriceData;
import com.example.hnTea.mvpmodel.price.bean.B_ProjectDetailModel;
import com.example.hnTea.mvpmodel.price.bean.B_ProjectModel;
import com.example.hnTea.mvpmodel.price.bean.B_SingleDetailModel;
import com.example.hnTea.mvpmodel.price.bean.B_SingleModel;
import com.example.hnTea.mvpmodel.price.bean.BaseX_ProjectDetail;
import com.example.hnTea.mvpmodel.price.bean.X_ProjectModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleDetailModel;
import com.example.hnTea.mvpmodel.price.bean.X_SingleModel;
import com.example.hnTea.mvppresenter.BasePresenter;
import com.example.hnTea.mvppresenter.IViewBase;

import java.util.List;

/**
 * Created by 太能 on 2016/11/15.
 */
public class PricePresenter extends BasePresenter {
    private PriceData mPriceData;


    public PricePresenter(IViewBase iViewBase) {
        super(iViewBase);
        mPriceData = new PriceData();
    }

    public void getX_SinglePriceListData(final String brand,
                                         final String time,
                                         final String product_name,
                                         final int page,
                                         final IViewPrice<List<X_SingleModel>> iViewPrice) {
        mPriceData.getX_SinglePriceListData(brand,
                time,
                product_name,
                page,
                new IActionPrice<List<X_SingleModel>>() {
                    @Override
                    public void success(List<X_SingleModel> data) {
                        iViewPrice.onSuccess(data);
                    }

                    @Override
                    public void phpFail(String var) {
                        iViewPrice.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        iViewPrice.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        iViewPrice.onStart(var);
                    }
                });
    }

    public void getX_SingleDetailListData(String inquiry, final IViewPrice<X_SingleDetailModel> iViewPrice) {
        mPriceData.getX_singleDetailListData(inquiry, new IActionPrice<X_SingleDetailModel>() {
            @Override
            public void success(X_SingleDetailModel data) {
                iViewPrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPrice.onStart(var);
            }
        });

    }

    public void getX_ProjectDetailListData(final String inquiry, final IViewPrice<BaseX_ProjectDetail> iViewPrice) {

        mPriceData.getX_ProjectDetailListData(inquiry, new IActionPrice<BaseX_ProjectDetail>() {
            @Override
            public void success(BaseX_ProjectDetail data) {
                iViewPrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPrice.onStart(var);
            }
        });
    }

    public void getB_SingleDetailListData(String offer_sn, final IViewPrice<B_SingleDetailModel> iViewPrice) {
        mPriceData.getB_SingleDetailListData(offer_sn, new IActionPrice<B_SingleDetailModel>() {
            @Override
            public void success(B_SingleDetailModel data) {
                iViewPrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPrice.onStart(var);
            }
        });
    }

    public void getB_ProjectDetailListData(String offer_sn, final IViewPrice<B_ProjectDetailModel> iViewPrice) {
        mPriceData.getB_ProjectDetailListData(offer_sn, new IActionPrice<B_ProjectDetailModel>() {
            @Override
            public void success(B_ProjectDetailModel data) {
                iViewPrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                iViewPrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                iViewPrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                iViewPrice.onStart(var);
            }
        });
    }

    public void getX_ProjectListData(final String province,
                                     final String time,
                                     final String project_type,
                                     final String project_name,
                                     final int page,
                                     final IViewPrice<List<X_ProjectModel>> IViewPrice){
        mPriceData.getX_ProjectListData(province,
                time,
                project_type,
                project_name,
                page,
                new IActionPrice<List<X_ProjectModel>>() {
                    @Override
                    public void success(List<X_ProjectModel> data) {
                        IViewPrice.onSuccess(data);
                    }

                    @Override
                    public void phpFail(String var) {
                        IViewPrice.onPhpFail(var);
                    }

                    @Override
                    public void fail(VolleyError volleyError) {
                        IViewPrice.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        IViewPrice.onStart(var);
                    }
                });
    }

    public void getB_SinglePriceListData(final int page,
                                         final String brand,
                                         final String start_time,
                                         final String product_name,
                                         final IViewPrice<List<B_SingleModel>> IViewPrice){
        mPriceData.getB_SinglePriceListData(page,
                brand,
                start_time,
                product_name,
                new IActionPrice<List<B_SingleModel>>() {
                    @Override
                    public void fail(VolleyError volleyError) {
                        IViewPrice.onFail(volleyError);
                    }

                    @Override
                    public void start(String var) {
                        IViewPrice.onStart(var);
                    }

                    @Override
                    public void success(List<B_SingleModel> data) {
                        IViewPrice.onSuccess(data);
                    }

                    @Override
                    public void phpFail(String var) {
                        IViewPrice.onPhpFail(var);
                    }
                });
    }

    public void getB_ProjectPriceListData(final int page,
                                          final IViewPrice<List<B_ProjectModel>> IViewPrice){
        mPriceData.getB_ProjectPriceListData(page, new IActionPrice<List<B_ProjectModel>>() {
            @Override
            public void success(List<B_ProjectModel> data) {
                IViewPrice.onSuccess(data);
            }

            @Override
            public void phpFail(String var) {
                IViewPrice.onPhpFail(var);
            }

            @Override
            public void fail(VolleyError volleyError) {
                IViewPrice.onFail(volleyError);
            }

            @Override
            public void start(String var) {
                IViewPrice.onStart(var);
            }
        });


    }

}
