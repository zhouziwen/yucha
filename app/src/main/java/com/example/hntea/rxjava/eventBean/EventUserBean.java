package com.example.hnTea.rxjava.eventBean;

/**
 * Created by 太能 on 2016/12/26.
 */
public class EventUserBean {

    //user相关参数 需要eventBus 订阅事件来传值
    String nickName;
    String address;

    public EventUserBean(String nickName,String address) {
        this.nickName = nickName;
        this.address =address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
