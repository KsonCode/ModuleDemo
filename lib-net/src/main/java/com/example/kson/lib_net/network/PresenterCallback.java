package com.example.kson.lib_net.network;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/05
 * Description：model回调接口
 */
public interface PresenterCallback<T> {
    public void onSuccess(T t);

    public void onSuccessMsg(String status, String message);

    public void onErrorMsg(int code, String msg);
}
