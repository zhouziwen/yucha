package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by 太能 on 2016/11/21.
 */
public class MeListModel {

    int image;
    String title;
    boolean isShow;

    public MeListModel(int image, String title, boolean isShow) {
        this.image = image;
        this.title = title;
        this.isShow = isShow;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
