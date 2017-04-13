package com.example.hnTea.mvpmodel.user.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class MyOrderModel {

    /**
     * order_id : 45
     * order_sn : 031511742737
     * order_status : 0
     * shipping_status : 0
     * pay_status : 0
     * consignee : 大大去
     * order_amount : 3800.00
     * goods_info : [{"goods_id":"1",
     * "goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5",
     * "goods_price":"1900.00","goods_number":"2"}]
     */

    private String order_id;
    private String order_sn;
    private String order_status;
    private String shipping_status;
    private String pay_status;
    private String consignee;
    private String order_amount;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private List<GoodsInfoBean> goods_info;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }

    public static class GoodsInfoBean {
        /**
         * goods_id : 1
         * goods_name : 小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5
         * goods_price : 1900.00
         * goods_number : 2
         */

        private String goods_id;
        private String goods_name;
        private String goods_price;
        private String goods_number;

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

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }
    }
}
