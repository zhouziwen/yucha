package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/17.
 */

public class CheckOrderModel_Address {

    private String id;
    private String receiver;
    private String address;
    private String is_default;
    private String telephone;
    private String postcode;

    public CheckOrderModel_Address(String id,
                   String receiver,
                   String address,
                   String is_default,
                   String telephone,
                   String postcode) {
        this.id = id;
        this.receiver = receiver;
        this.address = address;
        this.is_default = is_default;
        this.telephone = telephone;
        this.postcode = postcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
