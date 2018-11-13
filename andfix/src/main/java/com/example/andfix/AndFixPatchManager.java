package com.example.andfix;

import android.app.Application;
import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.blankj.utilcode.util.AppUtils;
import com.example.andfix.common.Constants;
import com.example.kson.lib_net.NetApp;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.RetrofitHttpRequest;

public class AndFixPatchManager {
    private static volatile AndFixPatchManager mInstance = null;

    private static PatchManager mPatchManager = null;

    public static AndFixPatchManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixPatchManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    //初始化AndFix方法
    public void initPatch(Application context) {

        mPatchManager = new PatchManager(context);
        mPatchManager.init(AppUtils.getAppVersionName());
        mPatchManager.loadPatch();
    }

    //加载我们的patch文件
    public void addPatch(String path) {
        try {
            if (mPatchManager != null) {
                mPatchManager.addPatch(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
