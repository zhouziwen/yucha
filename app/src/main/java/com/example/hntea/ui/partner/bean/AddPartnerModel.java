package com.example.hnTea.ui.partner.bean;

/**
 * Created by 太能 on 2016/12/1.
 */
public class AddPartnerModel {

    String tv;
    String hint;
    String smsText;
    boolean isHidden;

    //edText的String值
    String var;

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public AddPartnerModel(String tv, String hint, String smsText, boolean isHidden) {
        this.tv = tv;
        this.hint = hint;
        this.smsText = smsText;
        this.isHidden = isHidden;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
