package com.example.hnTea.mvpmodel.home.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2016/11/16.
 */
public class HomeListModel {
    int image;
    int leftImage;
    int rightImage;
    List<String>  leftList =new ArrayList<>();
    List<String>  fightList =new ArrayList<>();

    public HomeListModel(int image, int leftImage, int rightImage, List<String> leftList, List<String> fightList) {
        this.image = image;
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        this.leftList = leftList;
        this.fightList = fightList;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(int leftImage) {
        this.leftImage = leftImage;
    }

    public int getRightImage() {
        return rightImage;
    }

    public void setRightImage(int rightImage) {
        this.rightImage = rightImage;
    }

    public List<String> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<String> leftList) {
        this.leftList = leftList;
    }

    public List<String> getFightList() {
        return fightList;
    }

    public void setFightList(List<String> fightList) {
        this.fightList = fightList;
    }
}
