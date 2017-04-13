package com.example.hnTea.mvpmodel.price.bean;

/**
 * Created by jason_syf on 2017/1/11.
 * Email: jason_sunyf@163.com
 */

public class B_ProductItemModel {
    private String id;
    private String offer_sn;
    private String product_name;
    private String brand;
    private String product_series;
    private String model;
    private String replace_model;
    private String param;
    private String quantity;
    private String price;
    private String unit;

    public B_ProductItemModel(String id, String offer_sn, String product_name, String brand,
                              String product_series, String model, String replace_model,
                              String param, String quantity, String price, String unit) {
        this.id = id;
        this.offer_sn = offer_sn;
        this.product_name = product_name;
        this.brand = brand;
        this.product_series = product_series;
        this.model = model;
        this.replace_model = replace_model;
        this.param = param;
        this.quantity = quantity;
        this.price = price;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOffer_sn() {
        return offer_sn;
    }

    public void setOffer_sn(String offer_sn) {
        this.offer_sn = offer_sn;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct_series() {
        return product_series;
    }

    public void setProduct_series(String product_series) {
        this.product_series = product_series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReplace_model() {
        return replace_model;
    }

    public void setReplace_model(String replace_model) {
        this.replace_model = replace_model;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    @Override
    public String toString() {
        return brand
                + model+product_name
                +  "x"
                + quantity
                + unit + "\n";
    }

}
