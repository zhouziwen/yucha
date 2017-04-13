package com.example.hnTea.mvpmodel.price.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jason on 2016/12/28.
 */
public class X_SingleDetailModel {
    private String inquiry_sn;
    private String receiver;
    private String phoneNum;
    private String address;
    private String end_time;
    private String receive_cycle;//收货周期
    private String warranty;//质保期
    private String is_include_tax;//是否含税  1代表含税 0
    private String is_include_shipping;//是否含运费 1代表包含 0
    private String remark;
    private String param;
    private String brand;
    private String product_name;
    @SerializedName("time")
    private String publishTime;
    private String product_series;//产品系列
   @SerializedName("model")
    private String product_model; //产品模型
    private String unit; //单位
    private String quantity;//数量

    public X_SingleDetailModel(String inquiry_sn, String receiver, String phoneNum, String address,
                               String end_time, String receive_cycle, String warranty,
                               String is_include_tax, String is_include_shipping, String remark,
                               String param, String brand, String product_name, String publishTime,
                               String product_series, String product_model, String unit,
                               String quantity) {
        this.inquiry_sn = inquiry_sn;
        this.receiver = receiver;
        this.phoneNum = phoneNum;
        this.address = address;
        this.end_time = end_time;
        this.receive_cycle = receive_cycle;
        this.warranty = warranty;
        this.is_include_tax = is_include_tax;
        this.is_include_shipping = is_include_shipping;
        this.remark = remark;
        this.param = param;
        this.brand = brand;
        this.product_name = product_name;
        this.publishTime = publishTime;
        this.product_series = product_series;
        this.product_model = product_model;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getReceive_cycle() {
        return receive_cycle;
    }

    public void setReceive_cycle(String receive_cycle) {
        this.receive_cycle = receive_cycle;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getIs_include_tax() {
        return is_include_tax;
    }

    public void setIs_include_tax(String is_include_tax) {
        this.is_include_tax = is_include_tax;
    }

    public String getIs_include_shipping() {
        return is_include_shipping;
    }

    public void setIs_include_shipping(String is_include_shipping) {
        this.is_include_shipping = is_include_shipping;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getProduct_series() {
        return product_series;
    }

    public void setProduct_series(String product_series) {
        this.product_series = product_series;
    }

    public String getProduct_model() {
        return product_model;
    }

    public void setProduct_model(String product_model) {
        this.product_model = product_model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
