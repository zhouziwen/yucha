package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by jason_syf on 2017/3/16.
 * Email: jason_sunyf@163.com
 */

public class ShopYellowPagesModel {

    /**
     * supplier_id : 1
     * introduce_desc : <img  src='https://www.dianlidian.com//cdn/img/yellow_page/intro.jpg' width="100%"/>
     <p>
     河南太能电气股份有限公司（股票代码：831536）业务涵盖从发电到用电
     * contact_desc : 					<img  src='https://www.dianlidian.com//cdn/img/yellow_page/contact.png' width="100%"/>
     <strong>中国大唐集团公司</strong>
     */

    private String supplier_id;
    private String introduce_desc;
    private String contact_desc;

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getIntroduce_desc() {
        return introduce_desc;
    }

    public void setIntroduce_desc(String introduce_desc) {
        this.introduce_desc = introduce_desc;
    }

    public String getContact_desc() {
        return contact_desc;
    }

    public void setContact_desc(String contact_desc) {
        this.contact_desc = contact_desc;
    }
}
