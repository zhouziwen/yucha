package com.example.hnTea.ui.home.shop.bean;

/**
 * Created by 太能 on 2016/11/29.
 */
public class ShopTypeModel {

    int  icon;
    String  type;
    boolean isChose;

    public ShopTypeModel(int icon, String type, boolean isChose) {
        this.icon = icon;
        this.type = type;
        this.isChose = isChose;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isChose() {
        return isChose;
    }

    public void setChose(boolean chose) {
        isChose = chose;
    }
}
