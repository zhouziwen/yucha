package com.example.hnTea.mvpmodel.home.bean;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class CheckOrderModel {

    //购物车product_id
    private List<goods> goods;
    //商品总价格
    private String product_id;
    //运费价格
    private String total_price;
    //收货地址信息
    private String freight;
    //地址
    @Nullable
    private CheckOrderModel_Address mAddress;

    public CheckOrderModel(List<CheckOrderModel.goods> goods,
                           String product_id,
                           String total_price,
                           String freight) {
        this.goods = goods;
        this.product_id = product_id;
        this.total_price = total_price;
        this.freight = freight;
    }

    public List<CheckOrderModel.goods> getGoods() {
        return goods;
    }

    public void setGoods(List<CheckOrderModel.goods> goods) {
        this.goods = goods;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public CheckOrderModel_Address getAddress() {
        return mAddress;
    }

    public void setAddress(CheckOrderModel_Address address) {
        this.mAddress = address;
    }


    //商品信息
    public static class goods{
        //商品名称
        private String goods_name;
        //商品单价
        private String unit_price;
        //商品简介
        private String goods_brief;
        //商品图片
        private String goods_thumb;
        //购买数量
        private String num;
        //库存是否足够1,足够,0不足
        private String is_stock;

        public goods(String goods_name,
                     String unit_price,
                     String goods_brief,
                     String goods_thumb,
                     String num,
                     String is_stock) {
            this.goods_name = goods_name;
            this.unit_price = unit_price;
            this.goods_brief = goods_brief;
            this.goods_thumb = goods_thumb;
            this.num = num;
            this.is_stock = is_stock;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getGoods_brief() {
            return goods_brief;
        }

        public void setGoods_brief(String goods_brief) {
            this.goods_brief = goods_brief;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getIs_stock() {
            return is_stock;
        }

        public void setIs_stock(String is_stock) {
            this.is_stock = is_stock;
        }
    }

}
