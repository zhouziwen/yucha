package com.example.hnTea.mvpmodel.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Attr {
    private String name;
    @SerializedName("detial")
    private List<ShopDetail_Item> mItems;

    public ShopDetail_Attr(String name, List<ShopDetail_Item> items) {
        this.name = name;
        mItems = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopDetail_Item> getItems() {
        return mItems;
    }

    public void setItems(List<ShopDetail_Item> items) {
        mItems = items;
    }
}
