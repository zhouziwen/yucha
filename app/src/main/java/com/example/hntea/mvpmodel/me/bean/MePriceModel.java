package com.example.hnTea.mvpmodel.me.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 太能 on 2016/11/15.
 */
public class MePriceModel implements Parcelable {

    String title;
    String time;
    String location;
    String type;
    String type1;

    public MePriceModel(String title, String time, String location, String type, String type1) {
        this.title = title;
        this.time = time;
        this.location = location;
        this.type = type;
        this.type1 = type1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    protected MePriceModel(Parcel in) {
        title = in.readString();
        time = in.readString();
        location = in.readString();
        type = in.readString();
    }

    public static final Creator<MePriceModel> CREATOR = new Creator<MePriceModel>() {
        @Override
        public MePriceModel createFromParcel(Parcel in) {
            return new MePriceModel(in);
        }

        @Override
        public MePriceModel[] newArray(int size) {
            return new MePriceModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeString(type);
    }
}
