package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/5.
 */

public class Me_J_SingleModel {
    private String inquiry_sn;
    private String product_name;
    private String quantity;
    private String unit;
    private String pendtime;
    private String brand;
    private String num;

    public Me_J_SingleModel(String inquiry_sn, String product_name, String quantity, String unit,
                            String pendtime, String brand, String num) {
        this.inquiry_sn = inquiry_sn;
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit = unit;
        this.pendtime = pendtime;
        this.brand = brand;
        this.num = num;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
