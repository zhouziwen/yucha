package com.example.hnTea.mvppresenter;

/**
 * Created by 太能 on 2016/11/14.
 */
public interface IViewBase<Error> {
    void onStart(String var);

    void onFail(Error error);
}
