package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by jason on 2016/12/28.
 */
public class X_ProjectDetailModel {
    private String inquiry_sn;
    private String project_name;
    private String project_type;
    private String stage;
    private String end_time;
    private String receive_cycle;//收货周期
    private String warranty;//质保期

    private String is_include_tax;//是否含税  1代表含税 0
    private String is_include_shipping;//是否含运费 1代表包含 0
    private String remark;
    private String province;

    public X_ProjectDetailModel(String inquiry_sn, String project_name, String project_type,
                                String stage, String end_time, String receive_cycle,
                                String warranty, String is_include_tax, String is_include_shipping,
                                String remark, String province) {
        this.inquiry_sn = inquiry_sn;
        this.project_name = project_name;
        this.project_type = project_type;
        this.stage = stage;
        this.end_time = end_time;
        this.receive_cycle = receive_cycle;
        this.warranty = warranty;
        this.is_include_tax = is_include_tax;
        this.is_include_shipping = is_include_shipping;
        this.remark = remark;
        this.province = province;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
