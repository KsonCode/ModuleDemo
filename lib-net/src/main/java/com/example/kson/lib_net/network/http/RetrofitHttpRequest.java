package com.example.kson.lib_net.network.http;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.kson.lib_net.ApiService;
import com.example.kson.lib_net.network.BaseObserver;
import com.example.kson.lib_net.network.RetrofitHelper;
import com.example.kson.lib_net.network.rx.HttpResultFunc;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.lib_net.network.rx.RxSchedulers;
import com.example.kson.lib_net.utils.EncryBody;
import com.example.kson.lib_net.utils.KeyHelper;

import java.util.HashMap;
import java.util.Locale;

import io.reactivex.disposables.Disposable;

public class RetrofitHttpRequest implements HttpRequest {
    private static String ANDROID_VERSION = "Android_0.0.2";
    private ApiService apiService;

    public RetrofitHttpRequest() {
        apiService = RetrofitHelper.createService(ApiService.class, false);
    }

    //获取当前的系统语言
    public static String getLocalLanguage() {
        String language = "";
        String locale = Locale.getDefault().toString();
        if (locale.contains("zh")) {
            language = "zh_CN";
        } else {
            language = "en_US";
        }
        return language;
    }

    @Override
    public void post(String url, HashMap<String, Object> params, final ICallback callback) {
//        params.put("text", "Android");
//        String data = EncryBody.encryBody(params);
//
//        HashMap<String, String> Header = new HashMap<>();
//        Header.put("pk", KeyHelper.getPrivateKey());
//        Header.put("tk", KeyHelper.getHeadParams());
//        Header.put("lang", getLocalLanguage());
//        Header.put("ct", SPUtils.getInstance().getString("CT", "1"));
        apiService.getData(url, params)
                .map(new HttpResultFunc())
                .compose(RxSchedulers.<String>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    public void onError(int errorCode, String msg) {
                        LogUtils.e("网络错误码：" + errorCode + "\n" + msg);

                        callback.onErrorMsg(errorCode, msg);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String dataBean) {
                        callback.onNext(dataBean);
                    }
                });

    }

    @Override
    public void get(String url, HashMap<String, Object> params, final ICallback callback) {
        apiService.doGetData(url, params)
                .map(new HttpResultFunc())
                .compose(RxSchedulers.<String>io_main())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    public void onError(int errorCode, String msg) {
                        LogUtils.e("网络错误码：" + errorCode + "\n" + msg);

                        callback.onErrorMsg(errorCode, msg);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String dataBean) {
                        callback.onNext(dataBean);
                    }
                });
    }
}
