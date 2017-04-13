package com.example.hnTea.mvpmodel.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason on 2017/1/5.
 */

public class Detail_J_Model {
    @SerializedName("inquiry")
    private List<Detail_inquiry> mDetail_J_inquiries;

    @SerializedName("product")
    private List<Detail_product> mDetail_J_products;

    @SerializedName("offer")
    private List<Detail_J_offer> mDetail_J_offers;

    public Detail_J_Model(List<Detail_inquiry> detail_J_inquiries, List<Detail_product> detail_J_products, List<Detail_J_offer> detail_J_offers) {
        mDetail_J_inquiries = detail_J_inquiries;
        mDetail_J_products = detail_J_products;
        mDetail_J_offers = detail_J_offers;
    }

    public List<Detail_inquiry> getDetail_J_inquiries() {
        return mDetail_J_inquiries;
    }

    public void setDetail_J_inquiries(List<Detail_inquiry> detail_J_inquiries) {
        mDetail_J_inquiries = detail_J_inquiries;
    }

    public List<Detail_product> getDetail_J_products() {
        return mDetail_J_products;
    }

    public void setDetail_J_products(List<Detail_product> detail_J_products) {
        mDetail_J_products = detail_J_products;
    }

    public List<Detail_J_offer> getDetail_J_offers() {
        return mDetail_J_offers;
    }

    public void setDetail_J_offers(List<Detail_J_offer> detail_J_offers) {
        mDetail_J_offers = detail_J_offers;
    }
}
