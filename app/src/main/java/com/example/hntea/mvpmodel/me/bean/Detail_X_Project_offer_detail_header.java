package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason_syf on 2017/2/5.
 * Email: jason_sunyf@163.com
 */

public class Detail_X_Project_offer_detail_header {
    private String offer_sn;
    private String supply_cycle;
    private String warranty;
    private String end_time;
    private String time;
    private String offer_num;
    private String offer_status;
    private String total_price;
    private String remark;
    private String contact_name;
    private String contact_phone;
    private String comany_name;

    public Detail_X_Project_offer_detail_header(String offer_sn, String supply_cycle,
                                                String warranty, String end_time, String time,
                                                String offer_num, String offer_status,
                                                String remark, String contact_name,
                                                String contact_phone, String comany_name,
                                                String total_price
    ) {

        this.offer_sn = offer_sn;
        this.supply_cycle = supply_cycle;
        this.warranty = warranty;
        this.end_time = end_time;
        this.time = time;
        this.offer_num = offer_num;
        this.offer_status = offer_status;
        this.total_price = total_price;
        this.remark = remark;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.comany_name = comany_name;
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

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOffer_num() {
        return offer_num;
    }

    public void setOffer_num(String offer_num) {
        this.offer_num = offer_num;
    }

    public String getOffer_status() {
        return offer_status;
    }

    public void setOffer_status(String offer_status) {
        this.offer_status = offer_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getComany_name() {
        return comany_name;
    }

    public void setComany_name(String comany_name) {
        this.comany_name = comany_name;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
