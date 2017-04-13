package com.example.hnTea.mvpmodel.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Num {
    //货品库存
    private String product_id;
    //货品id
    private String goods_id;
    //对应属性值
    @SerializedName("goods_attr")
    private List<String> mStrings;
    //货号
    private String product_sn;
    //货品库存
    private String product_number;
    //货品价格
    private String product_price;

    public ShopDetail_Num(String product_id,
                          String goods_id,
                          List<String> strings,
                          String product_sn,
                          String product_number,
                          String product_price) {
        this.product_id = product_id;
        this.goods_id = goods_id;
        mStrings = strings;
        this.product_sn = product_sn;

        this.product_number = product_number;
        this.product_price =product_price;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }

    public void setProduct_sn(String product_sn) {
        this.product_sn = product_sn;
    }

    public void setProduct_number(String product_number) {
        this.product_number = product_number;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public List<String> getStrings() {
        return mStrings;
    }

    public String getProduct_sn() {
        return product_sn;
    }

    public String getProduct_number() {
        return product_number;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
