package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by 太能 on 2016/12/30.
 */
public class Detail_inquiry {
    String inquiry_sn;
    String receiver;
    String telephone;
    String address;
    String end_time;
    String receive_cycle;
    String warranty;
    String is_include_tax;
    String is_include_shipping;
    String remark;

    public Detail_inquiry(String inquiry_sn, String receiver, String telephone, String address, String end_time, String receive_cycle, String warranty, String is_include_tax, String is_include_shipping, String remark) {
        this.inquiry_sn = inquiry_sn;
        this.receiver = receiver;
        this.telephone = telephone;
        this.address = address;
        this.end_time = end_time;
        this.receive_cycle = receive_cycle;
        this.warranty = warranty;
        this.is_include_tax = is_include_tax;
        this.is_include_shipping = is_include_shipping;
        this.remark = remark;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
}
