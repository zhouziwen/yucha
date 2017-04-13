package com.example.hnTea.mvpmodel.login.bean;

/**
 * Created by 太能 on 2016/12/22.
 */
public class UserInfo {
    String uid;
    String username;
    String phone;
    String head_img;
    String email;
    String token;

    public UserInfo(String uid, String username, String phone, String head_img, String email, String token) {
        this.uid = uid;
        this.username = username;
        this.phone = phone;
        this.head_img = head_img;
        this.email = email;
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
