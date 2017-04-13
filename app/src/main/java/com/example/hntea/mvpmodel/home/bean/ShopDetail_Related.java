package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Related {
    //商品id
    private String goods_id;
    //商品在前台显示的微缩图片，如在分类筛选时显示的小图片
    private String goods_thumb;
    //goods_name
    private String goods_name;
    //基础价格
    private String shop_price;

    public ShopDetail_Related(String goods_id,
                              String goods_thumb,
                              String goods_name,
                              String shop_price) {
        this.goods_id = goods_id;
        this.goods_thumb = goods_thumb;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
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
}
