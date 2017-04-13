package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/3.
 */
public class Detail_X_Project_offer {
    private String comany_name;
    private String time;
    private String warranty;
    private String supply_cycle;
    private String total_price;
    private String offer_sn;
//    @SerializedName("offer_product")
//    private List<Detail_X_Project_offer_product> mOfferProductses;

    public Detail_X_Project_offer(String comany_name, String time, String warranty,
                                  String supply_cycle, String total_price, String offer_sn) {
        this.comany_name = comany_name;
        this.time = time;
        this.warranty = warranty;
        this.supply_cycle = supply_cycle;
        this.total_price = total_price;
        this.offer_sn = offer_sn;
//        mOfferProductses = offerProductses;
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

    public String getOffer_sn() {
        return offer_sn;
    }

    public void setOffer_sn(String offer_sn) {
        this.offer_sn = offer_sn;
    }

//    public List<Detail_X_Project_offer_product> getOfferProductses() {
//        return mOfferProductses;
//    }
//
//    public void setOfferProductses(List<Detail_X_Project_offer_product> offerProductses) {
//        mOfferProductses = offerProductses;
//    }
//
//    @Override
//    public String toString() {
//        return getOfferProductses().toString();
//    }
}
