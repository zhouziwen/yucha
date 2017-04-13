package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2016/11/16.
 */
public class HomeHListViewModel {
    int  img;
    String title;
    String price;

    public HomeHListViewModel(int img, String title, String price) {
        this.img = img;
        this.title = title;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
