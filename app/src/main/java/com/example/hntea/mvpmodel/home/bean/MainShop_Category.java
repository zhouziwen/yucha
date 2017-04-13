package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by 太能 on 2017/3/13.
 */

public class MainShop_Category {

    private String cat_id;
    private String cat_name;
    private String parent_id;
    private List<Children> children;
    private String image;

    public MainShop_Category(String cat_id, String cat_name, String parent_id, List<Children> childrens, String image) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.parent_id = parent_id;
        children = childrens;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<Children> getChildrens() {
        return children;
    }

    public void setChildrens(List<Children> childrens) {
        children = childrens;
    }

    public class Children{
        private String cat_id;
        private String cat_name;
        private String parent_id;

        public Children(String cat_id, String cat_name, String parent_id) {
            this.cat_id = cat_id;
            this.cat_name = cat_name;
            this.parent_id = parent_id;
        }

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

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
