package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/4.
 */
public class Detail_B_offer {
    private String time;
    private String warranty;
    private String supply_cycle;
    private String total_price;

    public Detail_B_offer(String time, String warranty, String supply_cycle, String total_price) {
        this.time = time;
        this.warranty = warranty;
        this.supply_cycle = supply_cycle;
        this.total_price = total_price;
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
