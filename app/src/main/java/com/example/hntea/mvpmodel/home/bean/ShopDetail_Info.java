package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Info {
    //
    private String goods_id;
    //商品名
    private String goods_name;
    //基础价格
    private String shop_price;
    //库存
    private String goods_number;
    //起订量
    private String minimum_order_quantity;
    //库存
    private String sell_num;
    //是否提供发票（0不提供，1提供，默认提供）
    private String is_invoice;
    //是否包邮（0不包，1包邮）
    private String is_shipping;
    //备注
    private String remark;
    //出货时间
    private String shipment_time;
    //订货出货时间
    private String order_shipment_time;
    //商品的实际大小图片，如进入该商品页时介绍商品属性所显示的大图片
    private String goods_thumb;
    //商品的唯一货号
    private String goods_sn;
    //品牌名
    private String brand_name;
    //商品的详细描述(这个地方是html代码，可以直接用)
    private String goods_desc;
    //分类名称
    private String cat_name;
    //类对应的自增id号
    private String cat_id;

    public ShopDetail_Info(String goods_id,
                           String goods_name,
                           String shop_price,
                           String goods_number,
                           String minimum_order_quantity,
                           String cell_number,
                           String is_invoice,
                           String is_shipping,
                           String remark,
                           String shipment_time,
                           String order_shipment_time,
                           String goods_img,
                           String goods_sn,
                           String brand_name,
                           String goods_desc,
                           String cat_name,
                           String cat_id) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.shop_price = shop_price;
        this.goods_number = goods_number;
        this.minimum_order_quantity = minimum_order_quantity;
        this.sell_num =cell_number;

        this.is_invoice = is_invoice;
        this.is_shipping = is_shipping;
        this.remark = remark;
        this.shipment_time = shipment_time;
        this.order_shipment_time = order_shipment_time;
        this.goods_thumb = goods_img;
        this.goods_sn = goods_sn;
        this.brand_name = brand_name;
        this.goods_desc = goods_desc;
        this.cat_name = cat_name;
        this.cat_id = cat_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getCell_number() {
        return sell_num;
    }

    public void setCell_number(String cell_number) {
        this.sell_num = cell_number;
    }

    public String getMinimum_order_quantity() {
        return minimum_order_quantity;
    }

    public void setMinimum_order_quantity(String minimum_order_quantity) {
        this.minimum_order_quantity = minimum_order_quantity;
    }

    public String getIs_invoice() {
        return is_invoice;
    }

    public void setIs_invoice(String is_invoice) {
        this.is_invoice = is_invoice;
    }

    public String getIs_shipping() {
        return is_shipping;
    }

    public void setIs_shipping(String is_shipping) {
        this.is_shipping = is_shipping;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShipment_time() {
        return shipment_time;
    }

    public void setShipment_time(String shipment_time) {
        this.shipment_time = shipment_time;
    }

    public String getOrder_shipment_time() {
        return order_shipment_time;
    }

    public void setOrder_shipment_time(String order_shipment_time) {
        this.order_shipment_time = order_shipment_time;
    }

    public String getGoods_img() {
        return goods_thumb;
    }

    public void setGoods_img(String goods_img) {
        this.goods_thumb = goods_img;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
