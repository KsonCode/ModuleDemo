package com.example.kson.lib_net.network.interceptors;


import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by kson on 2017/11/7.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        String method = request.method();
        StringBuilder sb = new StringBuilder();
        RequestBody body = request.body();
        Response response = chain.proceed(request);
//        LogUtils.e("pwjrequest", request.headers() + "" + request.url() + "");
        long t2 = System.nanoTime();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一 个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        LogUtils.e("BeePay","请求头数据", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                request.url(), chain.connection(), request.headers(), response.toString()));
        return response;
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
//        if (NetWorkUtils.isAvailable(MyApplication.getContext())) {
//            ToastUtil.show("网络连接异常");
//            return null;
//        } else {
//
//        }
//        if (request.body() instanceof FormBody) {
//            FormBody body = (FormBody) request.body();
        //        for (int i = 0; i < body.contentLength(); i++) {
//                sb.append(body.(i) + "=" + body.encodedValue(i) + ",");
//            }
//            sb.delete(sb.length() - 1, sb.length());
//        LogUtils.e("pwjpost", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
//                request.url(), chain.connection(), request.headers(), body.toString()));
//        }
    }
}
