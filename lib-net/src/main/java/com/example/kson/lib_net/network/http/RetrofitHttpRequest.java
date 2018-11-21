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
import java.util.List;
import java.util.Locale;

import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

public class RetrofitHttpRequest implements HttpRequest {
    private static String ANDROID_VERSION = "Android_0.0.2";
    private ApiService apiService;

    public RetrofitHttpRequest(boolean iscache) {
        apiService = RetrofitHelper.createService(ApiService.class, iscache);
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
        HashMap<String, String> Header = new HashMap<>();
        Header.put("ak", "0110010010000");
        Header.put("sessionId", "154159234203439");
        Header.put("userId", SPUtils.getInstance().getString("userId","39"));
//                .addHeaderParams("Content-Type", "application/x-www-form-urlencoded")

        apiService.getData(Header,url, params)
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

        HashMap<String, String> Header = new HashMap<>();
        Header.put("ak", "0110010010000");
        Header.put("sessionId", "154159234203439");
        Header.put("userId", SPUtils.getInstance().getString("userId","39"));
//                .addHeaderParams("Content-Type", "application/x-www-form-urlencoded")

        apiService.doGetData(Header,url, params)
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
    public void uploadFile(String fileUrl, HashMap<String, String> content, List<MultipartBody.Part> parts, final ICallback callback) {
        HashMap<String, String> Header = new HashMap<>();
        Header.put("ak", "0110010010000");
        Header.put("sessionId", "154241779942688");
        Header.put("userId", SPUtils.getInstance().getString("userId","88"));
        apiService.uploadFile(Header,fileUrl,content, parts)
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
