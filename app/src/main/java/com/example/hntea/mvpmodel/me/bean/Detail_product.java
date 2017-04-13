package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by 太能 on 2016/12/30.
 */
public class Detail_product {
    String product_name;
    String brand;
    String product_series;
    String model;
    String param;
    String quantity;
    String unit;

    public Detail_product(String product_name, String brand, String product_series, String model, String param, String quantity, String unit) {
        this.product_name = product_name;
        this.brand = brand;
        this.product_series = product_series;
        this.model = model;
        this.param = param;
        this.quantity = quantity;
        this.unit = unit;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
