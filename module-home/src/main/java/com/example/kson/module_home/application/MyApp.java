package com.example.kson.module_home.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.kson.lib_core.base.BaseApp;
import com.example.kson.lib_net.NetApp;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.RetrofitHttpRequest;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/30
 * Description:
 */
public class MyApp extends BaseApp {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
