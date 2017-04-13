package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by 太能 on 2016/12/27.
 */
public class X_ProjectModel {
    String inquiry_sn;
    String project_name;
    String project_type;
    String time;
    String province;
    String stage;

    public X_ProjectModel(String inquiry_sn, String project_name, String project_type, String time, String province, String stage) {
        this.inquiry_sn = inquiry_sn;
        this.project_name = project_name;
        this.project_type = project_type;
        this.time = time;
        this.province = province;
        this.stage = stage;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
