package com.example.kson.module_push;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/30
 * Description:
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
