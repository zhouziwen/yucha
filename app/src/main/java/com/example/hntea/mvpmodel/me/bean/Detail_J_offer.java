package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/5.
 */

public class Detail_J_offer extends Detail_X_Single_offer {
    private String usefultime;



    public Detail_J_offer(String comany_name, String time, String warranty, String supply_cycle, String total_price, String usefultime) {
        super(comany_name, time, warranty, supply_cycle, total_price);
        this.usefultime = usefultime;
    }

    public String getUsefultime() {
        return usefultime;
    }

    public void setUsefultime(String usefultime) {
        this.usefultime = usefultime;
    }
}
