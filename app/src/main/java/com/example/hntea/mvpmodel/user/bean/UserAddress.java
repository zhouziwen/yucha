package com.example.hnTea.mvpmodel.user.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 太能 on 2016/12/24.
 */
public class UserAddress implements Parcelable {

    String id;
    String telephone;
    String receiver;
    String address;
    String is_default;
    String postcode;

    public UserAddress(String id, String telephone, String receiver, String address, String is_default, String postcode) {
        this.id = id;
        this.telephone = telephone;
        this.receiver = receiver;
        this.address = address;
        this.is_default = is_default;
        this.postcode = postcode;
    }

    protected UserAddress(Parcel in) {
        id = in.readString();
        telephone = in.readString();
        receiver = in.readString();
        address = in.readString();
        is_default = in.readString();
        postcode = in.readString();
    }

    public static final Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
        @Override
        public UserAddress createFromParcel(Parcel in) {
            return new UserAddress(in);
        }

        @Override
        public UserAddress[] newArray(int size) {
            return new UserAddress[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(telephone);
        dest.writeString(receiver);
        dest.writeString(address);
        dest.writeString(is_default);
        dest.writeString(postcode);
    }
}
