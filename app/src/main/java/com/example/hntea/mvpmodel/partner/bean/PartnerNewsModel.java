package com.example.hnTea.mvpmodel.partner.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 太能 on 2016/12/26.
 */
public class PartnerNewsModel {

    int type;
    String id;
    String title;
    String time;
    List<HashMap<String,String>> img=new ArrayList();

    public PartnerNewsModel(String id, String title, String time, List<HashMap<String,String>> list) {
//        this.type = type;
        this.id = id;
        this.title = title;
        this.time = time;
        img = list;
    }

    public PartnerNewsModel(int type, String id, String title, String time, List<HashMap<String, String>> img) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.time = time;
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public List<HashMap<String,String>> getList() {
        return img;
    }

    public void setList(List<HashMap<String,String>> list) {
        img = list;
    }
}
