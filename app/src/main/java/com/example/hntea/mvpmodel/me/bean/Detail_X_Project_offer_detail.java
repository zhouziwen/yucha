package com.example.hnTea.mvpmodel.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason_syf on 2017/2/5.
 * Email: jason_sunyf@163.com
 */

public class Detail_X_Project_offer_detail {
    @SerializedName("offer")
    private Detail_X_Project_offer_detail_header mOffer_detail_header;
    @SerializedName("product")
    private List<Detail_XProject_offer_product> mDetail_products;

    public Detail_X_Project_offer_detail(Detail_X_Project_offer_detail_header offer_detail_header,
                                         List<Detail_XProject_offer_product> detail_products) {
        mOffer_detail_header = offer_detail_header;
        mDetail_products = detail_products;
    }

    public Detail_X_Project_offer_detail_header getOffer_detail_header() {
        return mOffer_detail_header;
    }

    public void setOffer_detail_header(Detail_X_Project_offer_detail_header offer_detail_header) {
        mOffer_detail_header = offer_detail_header;
    }

    public List<Detail_XProject_offer_product> getDetail_products() {
        return mDetail_products;
    }

    public void setDetail_products(List<Detail_XProject_offer_product> detail_products) {
        mDetail_products = detail_products;
    }
}
