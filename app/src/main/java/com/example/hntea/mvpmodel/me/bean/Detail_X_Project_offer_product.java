package com.example.hnTea.mvpmodel.me.bean;

/**
 * Created by jason on 2017/1/3.
 */
public class Detail_X_Project_offer_product {
    private String product_name;
    private String brand;
    private String model;
    private String quantity;
    private String unit;
    private String price;

    public Detail_X_Project_offer_product(String product_name, String brand, String model,
                                          String quantity, String unit, String price) {
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "产品名称:" + product_name +
                "   产品品牌:"+ brand+
                "   产品型号:"+ model+
                "   数量:" + quantity+
                "   单位:" + unit+
                "   单价:" + price+"\n";
    }


}
