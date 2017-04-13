package com.example.hnTea.mvpmodel.user.bean;

/**
 * Created by 太能 on 2017/4/10.
 */

public class HelperModel {
    private String id;
    private String title;
    private String content;
    private String update;

    public HelperModel(String id, String title, String content, String update) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.update = update;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
