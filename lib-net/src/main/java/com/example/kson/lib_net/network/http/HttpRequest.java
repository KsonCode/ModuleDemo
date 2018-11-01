package com.example.kson.lib_net.network.http;


import com.example.kson.lib_net.network.rx.RxManager;

import java.util.HashMap;

public interface HttpRequest {

    void post(String url, HashMap<String, Object> params, RxManager rxManager, ICallback callback);
}
