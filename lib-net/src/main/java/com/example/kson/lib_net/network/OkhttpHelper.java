package com.example.kson.lib_net.network;

import android.os.Build;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.kson.lib_net.BuildConfig;
import com.example.kson.lib_net.NetApp;
import com.example.kson.lib_net.NetConstants;
import com.example.kson.lib_net.R;
import com.example.kson.lib_net.network.interceptors.AppCacheInterceptor;
import com.example.kson.lib_net.network.interceptors.DownloadInterceptor;
import com.example.kson.lib_net.network.interceptors.HeaderInterceptor;
import com.example.kson.lib_net.network.interceptors.LoggingInterceptor;
import com.example.kson.lib_net.network.rx.download.DownloadEntity;
import com.example.kson.lib_net.utils.GetMD5;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * <pre>
 *     描述   : okhttp配置
 * </pre>
 */
public class OkhttpHelper {
    private static int CONNECT_TIME = 10;
    private static int READ_TIME = 20;
    private static int WRITE_TIME = 20;
    private static OkHttpClient.Builder builder;
    private static OkHttpClient client;
    public static String ANDROID_VERSION = "Android_0.0.2";

    /**
     * 实现了 X509TrustManager
     * 通过此类中的 checkServerTrusted 方法来确认服务器证书是否正确
     */
    static class MyTrustManager implements X509TrustManager {
        X509Certificate cert;

        MyTrustManager(X509Certificate cert) {
            this.cert = cert;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 我们在客户端只做服务器端证书校验。
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 确认服务器端证书和代码中 hard code 的 CRT 证书相同。
            if (chain[0].equals(this.cert)) {
                Log.i("Jin", "checkServerTrusted Certificate from server is valid!");
                return;// found match
            }
            throw new CertificateException("checkServerTrusted No trusted server cert found!");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


    private static OkHttpClient.Builder getDefaultBuilder(boolean isCache) {
        builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            ChuckInterceptor chuckInterceptor = new ChuckInterceptor(NetApp.getAppContext());
            loggingInterceptor.setLevel(BODY);
            //打印拦截器
            builder.addInterceptor(loggingInterceptor);
            //调试拦截器
            builder.addInterceptor(new StethoInterceptor());
            //http拦截器
            builder.addInterceptor(chuckInterceptor);
        }
        File cacheFile = new File(NetConstants.PATH_CACHE);
        //最大50M，
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        if (isCache) {
            builder.addInterceptor(new AppCacheInterceptor())
                    .cache(cache);
        }

        HeaderInterceptor commonInterceptor = new HeaderInterceptor.Builder()
                .addHeaderParams("ak", "0110010010000")
        .addHeaderParams("sessionId", SPUtils.getInstance().getString("sessionId","154159234203439"))
                .addHeaderParams("userId", SPUtils.getInstance().getString("userId","39"))
//                .addHeaderParams("Content-Type", "application/x-www-form-urlencoded")
                .build();

        String md5= GetMD5.getFileInputMD5(NetApp.getAppContext().getResources().openRawResource(R.raw.server));
        assert md5 != null;
        LogUtils.e(md5);
        if (md5 != null && (!("34c59ea4ea973913bb4685192f1730b5".equals(md5) ))) {
            throw new IllegalStateException("KEY ERROR");
        }
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(NetApp.getAppContext().getResources().openRawResource(R.raw.server));

            SSLContext sc = SSLContext.getInstance("TLS");
            //信任证书管理,这个是由我们自己生成的,信任我们自己的服务器证书
            TrustManager tm = new MyTrustManager(certificate);
            sc.init(null, new TrustManager[]{
                    tm
            }, null);
            builder.sslSocketFactory(sc.getSocketFactory(), (X509TrustManager) tm);
            builder.hostnameVerifier(new TrustAllHostnameVerifier());

        }catch (Exception e){
            LogUtils.e("SSL设置错误");
        }

        //下面3个超时,不设置默认就是10s
        builder.addInterceptor(commonInterceptor)

                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(new LoggingInterceptor())
                //失败重试
                .retryOnConnectionFailure(true);

        return builder;
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
    public static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            System.out.println("hostname:"+hostname.trim());
            if (hostname.trim().equals("172.17.8.100")) {
                return true;
            }else{
                return false;
            }
        }
    }


    public static OkHttpClient getOkhttpClient(boolean isCache, Interceptor... interceptors) {
        if (builder == null) {
            builder = getDefaultBuilder(isCache);
        }
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    public static OkHttpClient getDefaultClient(boolean isCache) {
        OkHttpClient.Builder builder = getDefaultBuilder(isCache);
        if (client == null) {
            client = builder.build();
        }
        return client;
    }

    public static OkHttpClient initDownloadClient(DownloadEntity entity) {
        DownloadInterceptor downloadInterceptor = new DownloadInterceptor(entity);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(BODY);
            //打印拦截器
            builder.addInterceptor(loggingInterceptor);
            //调试拦截器
            builder.addInterceptor(new StethoInterceptor());
        }
        builder.addInterceptor(downloadInterceptor)
                //下面3个超时,不设置默认就是10s
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                //失败重试
                .retryOnConnectionFailure(true);
        return builder.build();
    }

}
