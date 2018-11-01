package com.example.kson.lib_net.network.interceptors;

import android.os.Build;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     描述   : 请求头属性拦截器
 * </pre>
 */
public class HeaderInterceptor implements Interceptor {

    public static String ANDROID_VERSION = "Android_0.0.2";
    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数，添加到url 中
       /* HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());*/

        // 新的请求

        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }

        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }



    public static String getDeviceInfo() {
        String info = Build.BRAND + "  " + Build.MODEL;
        return info;

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

    public static class Builder {
        HeaderInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HeaderInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }


        public HeaderInterceptor build() {
            return mHttpCommonInterceptor;
        }
    }
}
