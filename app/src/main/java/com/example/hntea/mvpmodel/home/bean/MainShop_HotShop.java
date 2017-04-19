package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/9.
 */

public class MainShop_HotShop {


    public MainShop_HotShop(String goods_id, String goods_name, String shop_price, String goods_thumb) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
        this.goods_thumb = goods_thumb;
    }

    /**
     * goods_id : 1
     * goods_name : 小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5
     * shop_price : 500.00
     * goods_thumb : /cdn/img/detail/re_01.png
     */

    private String goods_id;
    private String goods_name;
    private String shop_price;
    private String goods_thumb;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }
}
