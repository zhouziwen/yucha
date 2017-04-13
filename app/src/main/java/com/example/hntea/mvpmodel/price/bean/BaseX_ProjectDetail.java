package com.example.hnTea.mvpmodel.price.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason on 2016/12/29.
 */
public class BaseX_ProjectDetail {
    @SerializedName("project")
    private List<X_ProjectDetailModel> mX_projectDetailModel;
    @SerializedName("item")
    private List<ProductParamItemModel> mItemModel;


    public BaseX_ProjectDetail( List<X_ProjectDetailModel> x_projectDetailModel, List<ProductParamItemModel> itemModel) {
        mX_projectDetailModel = x_projectDetailModel;
        mItemModel = itemModel;
    }

    public  List<X_ProjectDetailModel> getX_projectDetailModel() {
        return mX_projectDetailModel;
    }

    public void setX_projectDetailModel( List<X_ProjectDetailModel> x_projectDetailModel) {
        mX_projectDetailModel = x_projectDetailModel;
    }

    public List<ProductParamItemModel> getItemModel() {
        return mItemModel;
    }

    public void setItemModel(List<ProductParamItemModel> itemModel) {
        mItemModel = itemModel;
    }

}
