package com.example.hnTea.mvpmodel.home.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2017/2/16.
 */

public class HomeNormalBean {
    private String image;
    private List<String>  mStrings =new ArrayList<>();


    public HomeNormalBean(String image, List<String> strings ) {
        this.image = image;
        this.mStrings = strings;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getStrings() {
        return mStrings;
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }
}
