package com.example.hnTea.mvpmodel.me.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason on 2017/1/3.
 */
public class Detail_X_ProjectModel {
    @SerializedName("inquiry")
    private List<Detail_inquiry> mDetail_x_project_inquiries;

    @SerializedName("product")
    private List<Detail_product> mDetail_x_project_products;

    @SerializedName("offer")
    private List<Detail_X_Project_offer> mDetail_x_project_offers;

    public Detail_X_ProjectModel(List<Detail_inquiry> detail_x_project_inquiries,
                                 List<Detail_product> detail_x_project_products,
                                 List<Detail_X_Project_offer> detail_x_project_offers) {
        mDetail_x_project_inquiries = detail_x_project_inquiries;
        mDetail_x_project_products = detail_x_project_products;
        mDetail_x_project_offers = detail_x_project_offers;
    }

    public List<Detail_inquiry> getDetail_x_project_inquiries() {
        return mDetail_x_project_inquiries;
    }

    public void setDetail_x_project_inquiries(List<Detail_inquiry> detail_x_project_inquiries) {
        mDetail_x_project_inquiries = detail_x_project_inquiries;
    }

    public List<Detail_product> getDetail_x_project_products() {
        return mDetail_x_project_products;
    }

    public void setDetail_x_project_products(List<Detail_product> detail_x_project_products) {
        mDetail_x_project_products = detail_x_project_products;
    }

    public List<Detail_X_Project_offer> getDetail_x_project_offers() {
        return mDetail_x_project_offers;
    }

    public void setDetail_x_project_offers(List<Detail_X_Project_offer> detail_x_project_offers) {
        mDetail_x_project_offers = detail_x_project_offers;
    }
}
