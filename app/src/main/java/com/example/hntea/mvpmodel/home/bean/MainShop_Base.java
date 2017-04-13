package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by 太能 on 2017/3/9.
 */

public class MainShop_Base {

    private List<MainShop_Banner> mBanners;

    private List<MainShop_Nav>  mNav;

    private List<MainShop_HotShop> mHotShops;

    private List<MainShop_ShangJia> mShnagJia;

    private List<MainShop_Category> mCategory;

    public MainShop_Base(List<MainShop_Banner> banners,
                         List<MainShop_Nav> nav,
                         List<MainShop_HotShop> hotShops,
                         List<MainShop_ShangJia> shnagJia,
                         List<MainShop_Category> category) {
        mBanners = banners;
        mNav = nav;
        mHotShops = hotShops;
        mShnagJia = shnagJia;
        mCategory = category;
    }

    public List<MainShop_Category> getCategory() {
        return mCategory;
    }

    public void setCategory(List<MainShop_Category> category) {
        mCategory = category;
    }

    public List<MainShop_Banner> getBanners() {
        return mBanners;
    }

    public void setBanners(List<MainShop_Banner> banners) {
        mBanners = banners;
    }

    public List<MainShop_Nav> getNav() {
        return mNav;
    }

    public void setNav(List<MainShop_Nav> nav) {
        mNav = nav;
    }

    public List<MainShop_HotShop> getHotShops() {
        return mHotShops;
    }

    public void setHotShops(List<MainShop_HotShop> hotShops) {
        mHotShops = hotShops;
    }

    public List<MainShop_ShangJia> getShnagJia() {
        return mShnagJia;
    }

    public void setShnagJia(List<MainShop_ShangJia> shnagJia) {
        mShnagJia = shnagJia;
    }
}
