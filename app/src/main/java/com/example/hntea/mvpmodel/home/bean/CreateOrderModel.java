package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class CreateOrderModel {


    /**
     * goods : [{"goods_sn":"1","goods_attr":"1|3|5|8|12","goods_price":1900.8,"market_price":"1.00","product_id":"8","is_real":"1","goods_number":1,"goods_id":"1","goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5","order_id":76,"send_number":0}]
     * order : {"postscript":"","consignee":"按时大厦 ","tel":"13721458488","zipcode":"0","froms":"app","order_status":0,"goods_amount":1900.8,"pay_status":0,"order_amount":1900.8,"address":"是的发电发送到是的大厦","shipping_status":0,"user_id":"85","order_sn":"0320168571414","add_time":1489999171,"pay_id":"0","mobile":"13721458488","pay_name":"支付宝"}
     */

    private OrderBean order;
    private List<GoodsBean> goods;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class OrderBean {
        /**
         * postscript :
         * consignee : 按时大厦
         * tel : 13721458488
         * zipcode : 0
         * froms : app
         * order_status : 0
         * goods_amount : 1900.8
         * pay_status : 0
         * order_amount : 1900.8
         * address : 是的发电发送到是的大厦
         * shipping_status : 0
         * user_id : 85
         * order_sn : 0320168571414
         * add_time : 1489999171
         * pay_id : 0
         * mobile : 13721458488
         * pay_name : 支付宝
         */

        private String postscript;
        private String consignee;
        private String tel;
        private String zipcode;
        private String froms;
        private int order_status;
        private double goods_amount;
        private int pay_status;
        private double order_amount;
        private String address;
        private int shipping_status;
        private String user_id;
        private String order_sn;
        private int add_time;
        private String pay_id;
        private String mobile;
        private String pay_name;

        public String getPostscript() {
            return postscript;
        }

        public void setPostscript(String postscript) {
            this.postscript = postscript;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getFroms() {
            return froms;
        }

        public void setFroms(String froms) {
            this.froms = froms;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public double getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(double goods_amount) {
            this.goods_amount = goods_amount;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public double getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(double order_amount) {
            this.order_amount = order_amount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(int shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }
    }

    public static class GoodsBean {
        /**
         * goods_sn : 1
         * goods_attr : 1|3|5|8|12
         * goods_price : 1900.8
         * market_price : 1.00
         * product_id : 8
         * is_real : 1
         * goods_number : 1
         * goods_id : 1
         * goods_name : 小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5
         * order_id : 76
         * send_number : 0
         */

        private String goods_sn;
        private String goods_attr;
        private double goods_price;
        private String market_price;
        private String product_id;
        private String is_real;
        private int goods_number;
        private String goods_id;
        private String goods_name;
        private int order_id;
        private int send_number;

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(String goods_attr) {
            this.goods_attr = goods_attr;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getIs_real() {
            return is_real;
        }

        public void setIs_real(String is_real) {
            this.is_real = is_real;
        }

        public int getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(int goods_number) {
            this.goods_number = goods_number;
        }

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

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getSend_number() {
            return send_number;
        }

        public void setSend_number(int send_number) {
            this.send_number = send_number;
        }
    }
}
