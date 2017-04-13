package com.example.hnTea.ui.me.bean;

/**
 * Created by 太能 on 2016/12/5.
 */
public class HistoryModel {
    private String goods_name;
    private String sell_num;
    private String goods_thumb;
    private String shop_price;
    private String brand_name;
    private String goods_id;

    public HistoryModel(String goods_name,
                        String sell_num,
                        String goods_thumb,
                        String shop_price,
                        String brand_name,
                        String goods_id) {
        this.goods_name = goods_name;
        this.sell_num = sell_num;
        this.goods_thumb = goods_thumb;
        this.shop_price = shop_price;
        this.brand_name = brand_name;
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSell_num() {
        return sell_num;
    }

    public void setSell_num(String sell_num) {
        this.sell_num = sell_num;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}
