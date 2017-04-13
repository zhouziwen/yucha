package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by 太能 on 2016/12/30.
 */
public class Detail_X_Single_offer {
    String comany_name;
    String time;
    String warranty;
    String supply_cycle;
    String total_price;

    public Detail_X_Single_offer(String comany_name, String time, String warranty, String supply_cycle, String total_price) {
        this.comany_name = comany_name;
        this.time = time;
        this.warranty = warranty;
        this.supply_cycle = supply_cycle;
        this.total_price = total_price;
    }

    public String getComany_name() {
        return comany_name;
    }

    public void setComany_name(String comany_name) {
        this.comany_name = comany_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getSupply_cycle() {
        return supply_cycle;
    }

    public void setSupply_cycle(String supply_cycle) {
        this.supply_cycle = supply_cycle;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
