package com.example.hnTea.mvpmodel.partner.bean;

/**
 * Created by jason on 2016/12/26.
 */
public class PartnerEnterModel {
    private String name;
    private String phoneNum;
    private String sms;
    private String address;

    public PartnerEnterModel(String name, String phoneNum, String sms) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.sms = sms;
    }

    public PartnerEnterModel(String phoneNum, String name, String address,  String sms) {
        this.phoneNum = phoneNum;
        this.name = name;
        this.address = address;
        this.sms = sms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
