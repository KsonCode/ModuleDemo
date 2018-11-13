/**
 * Copyright (C), 2015-2018, 维度科技有限公司
 * FileName: DownloadInterceptor
 * Author: kson
 * Date: 2018/11/10 下午7:28
 * Description: 下载拦截器
 * History:
 */
package com.example.kson.lib_net.network.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @ClassName: DownloadInterceptor
 * @Description: java类作用描述
 * @Author: kson
 * @Date: 2018/11/10 下午7:28
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadListener downloadListener;

    public DownloadInterceptor(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownloadResponseBody(response.body(), downloadListener)).build();
    }
}