package com.example.hnTea.mvpmodel.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 太能 on 2016/12/30.
 */
public class Detail_X_SingleModel {

    @SerializedName("inquiry")
    private List<Detail_inquiry> mDetail_x_single_inquiries;

    @SerializedName("product")
    private List<Detail_product> mDetail__products;

    @SerializedName("offer")
    private List<Detail_X_Single_offer> mDetail_x_single_offers;

    public Detail_X_SingleModel(List<Detail_inquiry> detail_x_single_inquiries, List<Detail_product> detail__products, List<Detail_X_Single_offer> detail_x_single_offers) {
        mDetail_x_single_inquiries = detail_x_single_inquiries;
        mDetail__products = detail__products;
        mDetail_x_single_offers = detail_x_single_offers;
    }

    public List<Detail_inquiry> getDetail_x_single_inquiries() {
        return mDetail_x_single_inquiries;
    }

    public void setDetail_x_single_inquiries(List<Detail_inquiry> detail_x_single_inquiries) {
        mDetail_x_single_inquiries = detail_x_single_inquiries;
    }

    public List<Detail_product> getDetail__products() {
        return mDetail__products;
    }

    public void setDetail__products(List<Detail_product> detail__products) {
        mDetail__products = detail__products;
    }

    public List<Detail_X_Single_offer> getDetail_x_single_offers() {
        return mDetail_x_single_offers;
    }

    public void setDetail_x_single_offers(List<Detail_X_Single_offer> detail_x_single_offers) {
        mDetail_x_single_offers = detail_x_single_offers;
    }
}
