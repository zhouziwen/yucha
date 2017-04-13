package com.example.hnTea.ui.me.user.bean;

/**
 * Created by 太能 on 2016/12/2.
 */
public class UserContentModel {
    String title;
    String var;

    public UserContentModel(String title, String var) {
        this.title = title;
        this.var = var;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
