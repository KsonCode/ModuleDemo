package com.example.kson.moduledemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/30
 * Description:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog(); // 打印日志
        ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险) }
        ARouter.init(this);
    }
}
