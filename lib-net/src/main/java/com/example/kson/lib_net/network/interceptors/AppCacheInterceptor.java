package com.example.kson.lib_net.network.interceptors;



import com.example.kson.lib_net.utils.SystemUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     描述   : okHttp缓存拦截器
 * </pre>
 */
public class AppCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!SystemUtils.isNetworkConnected()) {
            //强制使用缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        int tryCount = 0;
        Response response = chain.proceed(request);
        while (!response.isSuccessful() && tryCount < 3) {
            tryCount++;
            // 重试
            response = chain.proceed(request);
        }
        return response;
    }
}
