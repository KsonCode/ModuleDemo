package com.example.kson.lib_core.hotfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;

import java.io.IOException;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/08
 * Description:管理andfix所有api
 */
public class AndFixPatchManager {
    private static volatile AndFixPatchManager mInstance;

    private PatchManager patchManager;

    public static AndFixPatchManager getmInstance() {
        if (mInstance == null) {
            synchronized (AndFixPatchManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void initPatch(Context context) {
        patchManager = new PatchManager(context);
        patchManager.init(AppUtils.getAppVersionName());
        patchManager.loadPatch();
    }

    /**
     * 加载patch文件
     *
     * @param path
     */
    public void addPatch(String path) {

        try {
            if (patchManager != null) {
                patchManager.addPatch(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
