package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by 太能 on 2016/12/27.
 */
public class B_SingleModel {
    String inquiry_sn;
    String offer_sn;
    String product_name;
    String quantity;
    String unit;
    String pendtime;
    String brand;
    String num;

    public B_SingleModel(String inquiry_sn, String offer_sn, String product_name, String quantity, String unit, String pendtime, String comany_name, String num) {
        this.inquiry_sn = inquiry_sn;
        this.offer_sn = offer_sn;
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit = unit;
        this.pendtime = pendtime;
        this.brand = comany_name;
        this.num = num;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getOffer_sn() {
        return offer_sn;
    }

    public void setOffer_sn(String offer_sn) {
        this.offer_sn = offer_sn;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPendtime() {
        return pendtime;
    }

    public void setPendtime(String pendtime) {
        this.pendtime = pendtime;
    }

    public String getComany_name() {
        return brand;
    }

    public void setComany_name(String comany_name) {
        this.brand = comany_name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
