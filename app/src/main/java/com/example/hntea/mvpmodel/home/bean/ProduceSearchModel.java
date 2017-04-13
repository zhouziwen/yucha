package com.example.hnTea.mvpmodel.home.bean;

import java.util.List;

/**
 * Created by jason_syf on 2017/3/14.
 * Email: jason_sunyf@163.com
 */

public class ProduceSearchModel {

    /**
     * goodsList : [{"goods_brief":"1","goods_name":"小米（MI）小米净化器2智能家用卧室空气净化器除
     * 甲醛雾霾PM2.5","goods_thumb":"http://192.168.1.199//cdn/img/detail/re_01.png","shop_price":"500.00","goods_id":"1"}]
     * num : 1
     * sId :
     * keywords : 小米
     */

    private String num;
    private String sId;
    private String keywords;
    private List<CategoryGoodsList.Goods> goodsList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<CategoryGoodsList.Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<CategoryGoodsList.Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
