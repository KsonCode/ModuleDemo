package com.example.kson.lib_net.network;

import com.example.kson.lib_net.NetApp;
import com.example.kson.lib_net.network.rx.download.DownloadEntity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {
    //假设这个是默认的retrofit,因为一个App可能有多个baseUrl
    //所以可能有多个retrofit
    private static Retrofit retrofit;

    //如果有其他url
    private static Retrofit urlRetrofit;

    private static Retrofit.Builder builder;

    private static Retrofit.Builder getDefaultRetrofitBuilder() {
        if (builder == null) {
            builder = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        return builder;
    }

    //更灵活的获取retrofit方式
    public static Retrofit getURLRetrofit(OkHttpClient client, String baseUrl) {
        if (urlRetrofit == null) {
            urlRetrofit = getDefaultRetrofitBuilder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .build();
        }
        return urlRetrofit;
    }

    private static Retrofit getRetrofit(boolean isCache) {
        if (retrofit == null) {
            OkHttpClient client = OkhttpHelper.getDefaultClient(isCache);
            Retrofit.Builder builder = getDefaultRetrofitBuilder();
            retrofit = builder.client(client)
                    .baseUrl(NetApp.baseUrl)
                    .build();
        }
        return retrofit;
    }
    /**
     * 专门下载的retrofit,最好不要重用以防出错误。
     *
     * @return
     */
    public static Retrofit getDownloadRetrofit(DownloadEntity entity) {
        OkHttpClient client = OkhttpHelper.initDownloadClient(entity);
        Retrofit.Builder builder = getDefaultRetrofitBuilder();
        return builder
                .client(client).build();
    }

    public static <S> S createService(Class<S> serviceClass, boolean isCache) {
        return createService(serviceClass, getRetrofit(isCache));
    }

    public static <S> S createService(Class<S> serviceClass, Retrofit retrofit) {
        if (retrofit == null) {
            throw new NullPointerException("retrofit 不能为null");
        }
        return retrofit.create(serviceClass);
    }
}
