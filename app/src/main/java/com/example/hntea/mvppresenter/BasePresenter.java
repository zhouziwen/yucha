package com.example.hnTea.mvppresenter;

/**
 * Created by 太能 on 2016/11/14.
 */
public class BasePresenter {
    protected IViewBase mIViewBase;

    public BasePresenter(IViewBase iViewBase){
        this.mIViewBase=iViewBase;
    }


}
