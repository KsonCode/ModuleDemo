package com.example.kson.lib_net.network.http;


import com.example.kson.lib_net.network.rx.RxManager;

import java.util.HashMap;

public class HttpRequestPresenter<T> implements HttpRequest {

    private HttpRequest httpRequest;
    private static volatile HttpRequestPresenter instance;

    public HttpRequestPresenter(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public static void init(HttpRequest httpRequest){
        if (instance==null) {
            synchronized (HttpRequestPresenter.class){
                if (instance==null) {
                    instance = new HttpRequestPresenter(httpRequest);
                }
            }
        }
    }

    public static HttpRequestPresenter getInstance(){
        return instance;
    }

    @Override
    public void post(String url, HashMap<String,Object> params, RxManager rxManager, ICallback callback) {
        httpRequest.post(url,params,rxManager,callback);
    }
}
