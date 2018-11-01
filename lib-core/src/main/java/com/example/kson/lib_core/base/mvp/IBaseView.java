package com.example.kson.lib_core.base.mvp;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public interface IBaseView {

    BasePresenter initPresenter();

    void showLoading();
    void hideLoading();

    void fail(String msg);//请求失败


}
