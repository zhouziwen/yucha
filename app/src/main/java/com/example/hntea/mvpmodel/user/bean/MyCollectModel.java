package com.example.hnTea.mvpmodel.user.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/14.
 * Email: jason_sunyf@163.com
 */

public class MyCollectModel {

    /**
     * num : 1
     * collectList : [{"rec_id":"157","user_id":"28","goods_id":"1","add_time":"1489046153",
     * "is_attention":"0","goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5",
     * "shop_price":"500.00","goods_thumb":"https://www.dianlidian.com/cdn/img/detail/re_01.png",
     * "goods_sn":"1"}]
     */

    private String num;
    private List<CollectListBean> collectList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<CollectListBean> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<CollectListBean> collectList) {
        this.collectList = collectList;
    }

    public static class CollectListBean {
        /**
         * rec_id : 157
         * user_id : 28
         * goods_id : 1
         * add_time : 1489046153
         * is_attention : 0
         * goods_name : 小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5
         * shop_price : 500.00
         * goods_thumb : https://www.dianlidian.com/cdn/img/detail/re_01.png
         * goods_sn : 1
         */

        private String rec_id;
        private String user_id;
        private String goods_id;
        private String add_time;
        private String goods_name;
        private String shop_price;
        private String goods_thumb;
        private String sell_num;
        private String company_name;

        public String getSell_num() {
            return sell_num;
        }

        public void setSell_num(String sell_num) {
            this.sell_num = sell_num;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
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
}
