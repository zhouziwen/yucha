package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Item {
    private String goods_attr_id;
    private String attr_value;
    private String attr_price;

    public ShopDetail_Item(String goods_attr_id,
                           String attr_value,
                           String attr_price) {
        this.goods_attr_id = goods_attr_id;
        this.attr_value = attr_value;
        this.attr_price = attr_price;
    }

    public String getGoods_attr_id() {
        return goods_attr_id;
    }

    public void setGoods_attr_id(String goods_attr_id) {
        this.goods_attr_id = goods_attr_id;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getAttr_price() {
        return attr_price;
    }

    public void setAttr_price(String attr_price) {
        this.attr_price = attr_price;
    }
}
