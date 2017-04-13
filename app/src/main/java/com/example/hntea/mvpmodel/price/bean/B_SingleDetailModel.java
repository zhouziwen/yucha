package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by jason on 2016/12/28.
 */
public class B_SingleDetailModel {
    private String offer_sn;
    private String product_name;
    private String brand;
    private String inquiry_sn;
    private String supply_cycle;//收货周期
    private String warranty;//质保期
    private String num;
    private String useful_time;
    private String end_time;
    private String price;

    private String is_include_tax;//是否含税  1代表含税 0
    private String is_include_shipping;//是否含运费 1代表包含 0
    private String remark;

    public B_SingleDetailModel(String offer_sn, String product_name, String brand,
                               String inquiry_sn, String supply_cycle, String warranty, String num,
                               String useful_time, String end_time, String price,
                               String is_include_tax, String is_include_shipping, String remark) {
        this.offer_sn = offer_sn;
        this.product_name = product_name;
        this.brand = brand;
        this.inquiry_sn = inquiry_sn;
        this.supply_cycle = supply_cycle;
        this.warranty = warranty;
        this.num = num;
        this.useful_time = useful_time;
        this.end_time = end_time;
        this.price = price;
        this.is_include_tax = is_include_tax;
        this.is_include_shipping = is_include_shipping;
        this.remark = remark;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getSupply_cycle() {
        return supply_cycle;
    }

    public void setSupply_cycle(String supply_cycle) {
        this.supply_cycle = supply_cycle;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUseful_time() {
        return useful_time;
    }

    public void setUseful_time(String useful_time) {
        this.useful_time = useful_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
