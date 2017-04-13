package com.example.hnTea.mvpmodel.home.bean;

/**
 * Created by 太能 on 2017/3/2.
 */

public class ShopDetail_Supplier {
    //供货商id
    private String supplier_id;
    //logo图片
    private String logo_img;
    //公司名称
    private String company_name;
    //联系人
    private String contacts_name;
    //电子邮件
    private String email;
    //联系人电话
    private String contacts_phone;
    //公司详细地址
    private String address;

    public ShopDetail_Supplier(String supplier_id,
                               String logo_img,
                               String company_name,
                               String contacts_name,
                               String email,
                               String contacts_phone,
                               String address) {
        this.supplier_id = supplier_id;
        this.logo_img = logo_img;
        this.company_name = company_name;
        this.contacts_name = contacts_name;
        this.email = email;
        this.contacts_phone = contacts_phone;
        this.address = address;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(String logo_img) {
        this.logo_img = logo_img;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
