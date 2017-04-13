package com.example.hnTea.mvpmodel.user.bean;

/**
 * Created by 太能 on 2016/12/23.
 */
public class MyInfo {
    String head_img;
    String username;
    String user_rank;
    String phone;
    String address;

    public MyInfo(String head_img, String username, String user_rank, String phone, String address) {
        this.head_img = head_img;
        this.username = username;
        this.user_rank = user_rank;
        this.phone = phone;
        this.address = address;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(String user_rank) {
        this.user_rank = user_rank;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
