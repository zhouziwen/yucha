package com.example.hnTea.ui.price.bean;

/**
 * Created by 太能 on 2016/12/8.
 */
public class PriceDetailModel {

    String title;
    String content;

    public PriceDetailModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
