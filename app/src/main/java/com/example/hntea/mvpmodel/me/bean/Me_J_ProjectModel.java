package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/5.
 */

public class Me_J_ProjectModel {
    private String inquiry_sn;
    private String project_name;
    private String project_type;
    private String stage;
    private String province;
    private String pendtime;

    public Me_J_ProjectModel(String inquiry_sn, String project_name, String project_type,
                             String stage, String province, String pendtime) {
        this.inquiry_sn = inquiry_sn;
        this.project_name = project_name;
        this.project_type = project_type;
        this.stage = stage;
        this.province = province;
        this.pendtime = pendtime;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPendtime() {
        return pendtime;
    }

    public void setPendtime(String pendtime) {
        this.pendtime = pendtime;
    }
}
