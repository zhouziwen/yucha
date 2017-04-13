package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2016/11/17.
 */
public class HomeListDetailModel  {

    int img;
    String title;
    String location;
    String price;
    String type;

    public HomeListDetailModel(int img, String title, String location, String price, String type) {
        this.img = img;
        this.title = title;
        this.location = location;
        this.price = price;
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
