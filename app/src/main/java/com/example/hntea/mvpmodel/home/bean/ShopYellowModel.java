package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class ShopYellowModel {

    /**
     * sId : 1
     * supplier_id : 1
     * goods_list : [{"goods_id":"2","goods_name":"零","shop_price":"0.00",
     * "goods_thumb":"https://www.dianlidian.com//cdn/img/detail/re_01.png","sell_num":"3"}]
     */

    private String sId;
    private int supplier_id;
    private List<GoodsListBean> goods_list;

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * goods_id : 2
         * goods_name : 零
         * shop_price : 0.00
         * goods_thumb : https://www.dianlidian.com//cdn/img/detail/re_01.png
         * sell_num : 3
         */

        private String goods_id;
        private String goods_name;
        private String shop_price;
        private String goods_thumb;
        private String sell_num;

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

        public String getSell_num() {
            return sell_num;
        }

        public void setSell_num(String sell_num) {
            this.sell_num = sell_num;
        }
    }
}
