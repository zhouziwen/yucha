package com.example.hnTea.ui.me.user.bean;

/**
 * Created by 太能 on 2016/12/7.
 */
public class BillModel {

   boolean isHasBill;

    public BillModel(boolean isHasBill) {
        this.isHasBill = isHasBill;
    }

    public boolean isHasBill() {
        return isHasBill;
    }

    public void setHasBill(boolean hasBill) {
        isHasBill = hasBill;
    }
}
