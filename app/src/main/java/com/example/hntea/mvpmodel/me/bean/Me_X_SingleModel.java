package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by 太能 on 2016/12/29.
 */
public class Me_X_SingleModel {

    String inquiry_sn;
    String product_name;
    String quantity;
    String time;
    String brand;
    String unit;

    public Me_X_SingleModel(String inquiry_sn, String time, String product_name, String brand, String quantity, String unit) {
        this.inquiry_sn = inquiry_sn;
        this.time = time;
        this.product_name = product_name;
        this.brand = brand;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
}
