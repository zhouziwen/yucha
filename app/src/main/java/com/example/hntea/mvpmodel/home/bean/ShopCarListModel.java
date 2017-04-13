package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/10.
 * Email: jason_sunyf@163.com
 */

public class ShopCarListModel {



    /**
     * goods : [{"sum_price":"27900","price":"3100","supplier_id":"1",
     * "goods_attr":[{"attr_value":"黑色","attr_name":"颜色"},{"attr_value":"32g","attr_name":"内存"}
     * ,{"attr_value":"移动","attr_name":"网络"},{"attr_value":"深圳","attr_name":"产地"},
     * {"attr_value":"骁龙835","attr_name":"cpu"}],
     * "product_id":"2","goods_id":"1","max_num":12,
     * "goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5",
     * "product_number":9,"goods_img":"http://192.168.1.199//cdn/img/detail/re_01.png",
     * "supplier":"北京市小米科技有限公司"},{"sum_price":"3800","price":"1900","supplier_id":"1",
     * "goods_attr":[{"attr_value":"白色","attr_name":"颜色"},{"attr_value":"16g","attr_name":"内存"},
     * {"attr_value":"电信","attr_name":"网络"},{"attr_value":"郑州","attr_name":"产地"},
     * {"attr_value":"骁龙835","attr_name":"cpu"}],"product_id":"8","goods_id":"1","max_num":30,
     * "goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5","product_number":2,
     * "goods_img":"http://192.168.1.199//cdn/img/detail/re_01.png","supplier":"北京市小米科技有限公司"}]
     * supplier : 北京市小米科技有限公司
     */
    private String supplier_id;
    private String supplier;
    private int status;
    private List<GoodsBean> goods;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }
    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public static class GoodsBean {
        /**
         * sum_price : 27900
         * price : 3100
         * supplier_id : 1
         * goods_attr : [{"attr_value":"黑色","attr_name":"颜色"},
         * {"attr_value":"32g","attr_name":"内存"},{"attr_value":"移动","attr_name":"网络"},
         * {"attr_value":"深圳","attr_name":"产地"},{"attr_value":"骁龙835","attr_name":"cpu"}]
         * product_id : 2
         * goods_id : 1
         * max_num : 12
         * goods_name : 小米（MI）小米净化器2智能家用卧室空气净化器除 甲醛雾霾PM2.5
         * product_number : 9
         * goods_img : http://192.168.1.199//cdn/img/detail/re_01.png
         * supplier : 北京市小米科技有限公司
         */
        private String rec_id;
        private String sum_price;
        private String price;
        private String supplier_id;
        private String product_id;
        private String goods_id;
        private int max_num;
        private String goods_name;
        private int product_number;
        private String goods_img;
        private String supplier;
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        private List<GoodsAttrBean> goods_attr;

        public String getSum_price() {
            return sum_price;
        }

        public void setSum_price(String sum_price) {
            this.sum_price = sum_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getMax_num() {
            return max_num;
        }

        public void setMax_num(int max_num) {
            this.max_num = max_num;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getProduct_number() {
            return product_number;
        }

        public void setProduct_number(int product_number) {
            this.product_number = product_number;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public List<GoodsAttrBean> getGoods_attr() {
            return goods_attr;
        }

        public void setGoods_attr(List<GoodsAttrBean> goods_attr) {
            this.goods_attr = goods_attr;
        }

        public static class GoodsAttrBean {
            /**
             * attr_value : 黑色
             * attr_name : 颜色
             */

            private String attr_value;
            private String attr_name;

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }
        }
    }
}
