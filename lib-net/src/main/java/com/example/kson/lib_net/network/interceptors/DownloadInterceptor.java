package com.example.kson.lib_net.network.interceptors;



import com.example.kson.lib_net.network.rx.download.DownloadEntity;
import com.example.kson.lib_net.network.rx.download.DownloadManager;
import com.example.kson.lib_net.network.rx.download.ProgressResponseBody;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 * </pre>
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadEntity downloadEntity;

    public DownloadInterceptor(DownloadEntity entity) {
        this.downloadEntity = entity;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long downloadedLength = DownloadManager.dSp.getLong(downloadEntity.getFileName(), 0);

        Response proceed = chain.proceed(request);
        DownloadManager.dSp.edit().putLong(downloadEntity.getFileName() + "content_length", proceed.body().contentLength()).apply();
        request = request.newBuilder()
                .header("RANGE", "bytes=" + downloadedLength + "-")
                .build();
        Response response = chain.proceed(request);
        response = response.newBuilder()
                .body(new ProgressResponseBody(response.body(), downloadEntity))
                .build();
        return response;
    }
}
