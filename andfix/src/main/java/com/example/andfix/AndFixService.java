package com.example.andfix;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.andfix.common.Constants;
import com.example.andfix.entity.PatchInfo;
import com.example.kson.lib_net.network.download.DownloadListener;
import com.example.kson.lib_net.network.download.DownloadUtils;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;

import java.io.File;
import java.util.HashMap;

public class AndFixService extends Service {
    private static final String TAG = AndFixService.class.getSimpleName();
    private static final String FILE_END = ".apatch";
    private static final int UPDATE_PATCH = 0x02;
    private static final int DOWNLOAD_PATCH = 0x01;

    private PatchInfo mPatchInfo;

    private String mPatchFileDir;
    private String mPatchFile;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PATCH:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_PATCH:
                    downloadPatch();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.sendEmptyMessage(UPDATE_PATCH);
        return START_NOT_STICKY;
    }

    //完成文件目录的构造
    private void init() {

        mPatchFileDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        LogUtils.e("patchDir:",mPatchFileDir);
        File patchDir = new File(mPatchFileDir);

        try {
            if (patchDir == null || !patchDir.exists()) {
                patchDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }
    }


    //检查服务器是否有patch文件
    private void checkPatchUpdate() {


        HttpRequestPresenter.getInstance().get("version/getVersion?type=0", new HashMap<String, Object>(), new ModelCallback<PatchInfo>(false,PatchInfo.class) {
            @Override
            public void onErrorMsg(int code, String msg) {
                stopSelf();
            }

            @Override
            public void onSuccess(PatchInfo patchInfo) {
                mPatchInfo = patchInfo;

                if (!TextUtils.isEmpty(patchInfo.apkUrl)) {
                    ToastUtils.showShort(patchInfo.apkUrl);

                    //下载patch文件
                    mHandler.sendEmptyMessage(DOWNLOAD_PATCH);
                } else {
//                    stopSelf();
                }
            }

            @Override
            public void onSuccessMsg(String status, String message) {

            }
        });
        

    }

    //完成patch文件的下载
    private void downloadPatch() {
        //初始化patch文件下载路径
        mPatchFile = mPatchFileDir.concat("new").
                concat(FILE_END);
        LogUtils.e("mpatchfile:"+mPatchFile);

        new DownloadUtils(Constants.BASE_URL, new DownloadListener() {
            @Override
            public void onStartDownload() {

            }

            @Override
            public void onProgress(int progress) {
                LogUtils.d(TAG, "current progedss: " + progress);
            }

            @Override
            public void onFinishDownload() {
//将我们下载好的patch文件添加到我们的andfix中
                LogUtils.e("addpatch:"+mPatchFile);
                AndFixPatchManager.getInstance().addPatch(mPatchFile);


            }

            @Override
            public void onFail(String errorInfo) {
                stopSelf();
            }
        }).download(mPatchInfo.apkUrl, mPatchFile);

    }
}
