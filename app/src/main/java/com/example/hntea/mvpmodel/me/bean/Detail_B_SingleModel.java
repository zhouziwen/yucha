package com.example.hnTea.mvpmodel.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason on 2017/1/4.
 */
public class Detail_B_SingleModel {
    @SerializedName("inquiry")
    private List<Detail_inquiry> mDetail_x_single_inquiries;

    @SerializedName("product")
    private List<Detail_product> mDetail__products;

    @SerializedName("offer")
    private List<Detail_B_offer> mDetail_b_single_offers;

    public Detail_B_SingleModel(List<Detail_inquiry> detail_x_project_inquiries,
                                List<Detail_product> detail_x_project_products,
                                List<Detail_B_offer> detail_offers,
                                List<Detail_inquiry> detail_x_single_inquiries,
                                List<Detail_product> detail__products,
                                List<Detail_B_offer> detail_b_single_offers) {
        mDetail_x_single_inquiries = detail_x_single_inquiries;
        mDetail__products = detail__products;
        mDetail_b_single_offers = detail_b_single_offers;
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

    public List<Detail_B_offer> getDetail_b_single_offers() {
        return mDetail_b_single_offers;
    }

    public void setDetail_b_single_offers(List<Detail_B_offer> detail_b_single_offers) {
        mDetail_b_single_offers = detail_b_single_offers;
    }
}
