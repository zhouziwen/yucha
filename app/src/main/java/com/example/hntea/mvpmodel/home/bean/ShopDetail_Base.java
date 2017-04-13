package com.example.hnTea.mvpmodel.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Base {

    @SerializedName("goodsInfo")
    private ShopDetail_Info mInfo;

    @SerializedName("goods_attr")
    private List<ShopDetail_Attr> mAttrs;

    //货品库存
    @SerializedName("product_number")
    private List<ShopDetail_Num>  mNums;

    //商品相册表
    @SerializedName("goods_gallery")
    private List<ShopDetail_Gallery> mGallery;

    //供货商
    @SerializedName("supplierInfo")
    private ShopDetail_Supplier mSupplier;

    //相关推荐
    @SerializedName("related_recommendation")
    private List<ShopDetail_Related> mRelated;

    //收藏
    private String collect_state_exists;


    public ShopDetail_Base(ShopDetail_Info info,
                           List<ShopDetail_Attr> attrs,
                           List<ShopDetail_Num> nums,
                           List<ShopDetail_Gallery> gallery,
                           ShopDetail_Supplier supplier,
                           List<ShopDetail_Related> related,
                           String collect_state_exists) {
        mInfo = info;
        mAttrs = attrs;
        mNums = nums;
        mGallery = gallery;
        mSupplier = supplier;
        mRelated = related;
        this.collect_state_exists = collect_state_exists;
    }

    public ShopDetail_Info getInfo() {
        return mInfo;
    }

    public void setInfo(ShopDetail_Info info) {
        mInfo = info;
    }

    public List<ShopDetail_Attr> getAttrs() {
        return mAttrs;
    }

    public void setAttrs(List<ShopDetail_Attr> attrs) {
        mAttrs = attrs;
    }

    public List<ShopDetail_Num> getNums() {
        return mNums;
    }

    public void setNums(List<ShopDetail_Num> nums) {
        mNums = nums;
    }

    public List<ShopDetail_Gallery> getGallery() {
        return mGallery;
    }

    public void setGallery(List<ShopDetail_Gallery> gallery) {
        mGallery = gallery;
    }

    public ShopDetail_Supplier getSupplier() {
        return mSupplier;
    }

    public void setSupplier(ShopDetail_Supplier supplier) {
        mSupplier = supplier;
    }

    public List<ShopDetail_Related> getRelated() {
        return mRelated;
    }

    public void setRelated(List<ShopDetail_Related> related) {
        mRelated = related;
    }

    public String getCollect_state_exists() {
        return collect_state_exists;
    }

    public void setCollect_state_exists(String collect_state_exists) {
        this.collect_state_exists = collect_state_exists;
    }
}
