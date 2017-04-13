package com.example.hnTea.https;

/**
 * Created by 太能 on 2016/12/21.
 */
public class BaseUrl {

    //http://192.168.1.199/api.php
    //https://www.dianlidian.com/api.php

    //返回服务器baseUrl
    public static String getBaseUrl() {
        return "https://www.dianlidian.com/api.php";
    }

    public static String getBaseUrlNoEnd(){
        return "https://www.dianlidian.com";
    }


    public static String getUMShareApUrl(){
        return "https://www.dianlidian.com/site/downLoad.html";
    }

}
