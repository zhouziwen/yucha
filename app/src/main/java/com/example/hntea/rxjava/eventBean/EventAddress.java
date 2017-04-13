package com.example.hnTea.rxjava.eventBean;

/**
 * Created by jason_syf on 2017/3/20.
 * Email: jason_sunyf@163.com
 */

public class EventAddress {
    private String id;
    private String telephone;
    private String receiver;
    private String address;
    private String is_default;
    private String postcode;

    public EventAddress(String id,
                        String telephone,
                        String receiver,
                        String address,
                        String is_default,
                        String postcode) {
        this.id = id;
        this.telephone = telephone;
        this.receiver = receiver;
        this.address = address;
        this.is_default = is_default;
        this.postcode = postcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
