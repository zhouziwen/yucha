package com.example.hnTea.mvpmodel.partner.bean;

/**
 * Created by 太能 on 2017/1/7.
 */

public class PartnerNewsDetailModel {
    String id;
    String title;
    String time;
    String content;
    String author;
    String share_img;
    String share_content;
    String share_url;

    public PartnerNewsDetailModel(String id, String title, String time, String content, String author, String share_img, String share_content, String share_url) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.author = author;
        this.share_img = share_img;
        this.share_content = share_content;
        this.share_url = share_url;
    }

    public String getShare_img() {
        return share_img;
    }

    public void setShare_img(String share_img) {
        this.share_img = share_img;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
