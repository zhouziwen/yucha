package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by 太能 on 2016/12/27.
 */
public class B_ProjectModel {

    String inquiry_sn;
    String offer_sn;
    String project_name;
    String project_type;
    String stage;
    String pendtime;
    String comany_name;
    String province;

    public B_ProjectModel(String inquiry_sn, String offer_sn, String project_name, String project_type, String stage, String pendtime, String comany_name, String province) {
        this.inquiry_sn = inquiry_sn;
        this.offer_sn = offer_sn;
        this.project_name = project_name;
        this.project_type = project_type;
        this.stage = stage;
        this.pendtime = pendtime;
        this.comany_name = comany_name;
        this.province = province;
    }

    public String getInquiry_sn() {
        return inquiry_sn;
    }

    public void setInquiry_sn(String inquiry_sn) {
        this.inquiry_sn = inquiry_sn;
    }

    public String getOffer_sn() {
        return offer_sn;
    }

    public void setOffer_sn(String offer_sn) {
        this.offer_sn = offer_sn;
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

    public String getPendtime() {
        return pendtime;
    }

    public void setPendtime(String pendtime) {
        this.pendtime = pendtime;
    }

    public String getComany_name() {
        return comany_name;
    }

    public void setComany_name(String comany_name) {
        this.comany_name = comany_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
