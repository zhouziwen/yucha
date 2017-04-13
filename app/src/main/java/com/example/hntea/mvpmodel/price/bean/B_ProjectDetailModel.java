package com.example.hnTea.mvpmodel.price.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason on 2016/12/29.
 */
public class B_ProjectDetailModel {
    private String inquiry_sn;
    private String offer_sn;
    private String supply_cycle;//收货周期
    private String warranty;//质保期
    private String useful_time;
    private String end_time;
    private String project_name;
    private String project_type;
    private String stage;
    private String status;
    private String num;
    private String price;
    private String province;
    private String is_include_tax;//是否含税  1代表含税 0
    private String is_include_shipping;//是否含运费 1代表包含 0
    private String remark;
    @SerializedName("product_list")
    private List<B_ProductItemModel> mProductItemModels;

    public B_ProjectDetailModel(String inquiry_sn, String offer_sn, String supply_cycle,
                                String warranty, String useful_time, String end_time,
                                String project_name, String project_type, String stage,
                                String status, String num, String price, String province,
                                String is_include_tax, String is_include_shipping,
                                String remark, List<B_ProductItemModel> productItemModels) {
        this.inquiry_sn = inquiry_sn;
        this.offer_sn = offer_sn;
        this.supply_cycle = supply_cycle;
        this.warranty = warranty;
        this.useful_time = useful_time;
        this.end_time = end_time;
        this.project_name = project_name;
        this.project_type = project_type;
        this.stage = stage;
        this.status = status;
        this.num = num;
        this.price = price;
        this.province = province;
        this.is_include_tax = is_include_tax;
        this.is_include_shipping = is_include_shipping;
        this.remark = remark;
        mProductItemModels = productItemModels;
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

    public String getproject_name() {
        return project_name;
    }

    public void setproject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<B_ProductItemModel> getProductItemModels() {
        return mProductItemModels;
    }

    public void setProductItemModels(List<B_ProductItemModel> productItemModels) {
        mProductItemModels = productItemModels;
    }
}
