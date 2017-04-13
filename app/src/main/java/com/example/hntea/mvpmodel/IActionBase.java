package com.example.hnTea.mvpmodel;

/**
 * Created by 太能 on 2016/11/14.
 */
public interface IActionBase<Error>  {
    void fail(Error error);

    void start(String var);

}
