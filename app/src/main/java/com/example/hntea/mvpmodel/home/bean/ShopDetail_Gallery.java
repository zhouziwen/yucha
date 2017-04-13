package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Gallery {
    //商品相册ID
    private String img_id;
    //图片属性商品的id
    private String goods_id;
    //实际图片url
    private String img_url;
    //图片说明信息
    private String img_desc;
    //微缩图片url
    private String thumb_url;

    public ShopDetail_Gallery(String img_id,
                              String goods_id,
                              String img_url,
                              String img_desc,
                              String thumb_url) {
        this.img_id = img_id;
        this.goods_id = goods_id;
        this.img_url = img_url;
        this.img_desc = img_desc;
        this.thumb_url = thumb_url;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_desc() {
        return img_desc;
    }

    public void setImg_desc(String img_desc) {
        this.img_desc = img_desc;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }
}
