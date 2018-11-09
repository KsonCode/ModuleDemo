package com.example.kson.module_home;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.kson.lib_net.NetApp;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.RetrofitHttpRequest;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

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
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this, options);

        ARouter.init(this);
        NetApp.init(this, "https://www.zhaoapi.cn/");
        HttpRequestPresenter.init(new RetrofitHttpRequest());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
