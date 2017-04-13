package com.example.hnTea.mvpmodel.home.bean;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/3.
 * Email: jason_sunyf@163.com
 */

public class CategoryGoodsList {
    private List<Category> categoryList;
    private List<Goods> goodsList;
    private String bId;
    private String cId;
    private String scId;

    public CategoryGoodsList(List<Category> categoryList, List<Goods> goodsList, String bId,
                             String cId, String scId) {
        this.categoryList = categoryList;
        this.goodsList = goodsList;
        this.bId = bId;
        this.cId = cId;
        this.scId = scId;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public static class Category {
        private String cat_id;
        private String cat_name;
        private String cat_desc;
         private List<SonCategory> son_cat;

         public String getCat_id() {
             return cat_id;
         }

         public void setCat_id(String cat_id) {
             this.cat_id = cat_id;
         }

         public String getCat_name() {
             return cat_name;
         }

         public void setCat_name(String cat_name) {
             this.cat_name = cat_name;
         }

         public String getCat_desc() {
             return cat_desc;
         }

         public void setCat_desc(String cat_desc) {
             this.cat_desc = cat_desc;
         }

         public List<SonCategory> getSon_cat() {
             return son_cat;
         }

         public void setSon_cat(List<SonCategory> son_cat) {
             this.son_cat = son_cat;
         }
     }


    public static class SonCategory {
        private String son_cat_id;
        private String son_cat_name;

        public String getSon_cat_id() {
            return son_cat_id;
        }

        public void setSon_cat_id(String son_cat_id) {
            this.son_cat_id = son_cat_id;
        }

        public String getSon_cat_name() {
            return son_cat_name;
        }

        public void setSon_cat_name(String son_cat_name) {
            this.son_cat_name = son_cat_name;
        }
    }

    public static class Goods  {
        private String goods_name;
        private String shop_price;
        private String goods_thumb;
        private String sell_num;
        private String company_name;
        private String goods_id;

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

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

    }


}
