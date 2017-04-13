package com.example.hnTea.mvpmodel.price.bean;
/**
 * Created by jason on 2016/12/28.
 */
public class ProductParamItemModel {
    private String product_name;
    private String brand;
    private String model;
    private String quantity;
    private String unit;

    public ProductParamItemModel(String product_name, String brand, String model,
                                 String quantity, String unit) {
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Override
    public String toString() {
        return brand
                + model+product_name
                +  "x"
                + quantity
                + unit + "\n";
    }

}
