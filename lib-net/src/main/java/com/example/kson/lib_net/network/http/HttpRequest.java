package com.example.kson.lib_net.network.http;


import java.util.HashMap;

public interface HttpRequest {
    void post(String url, HashMap<String, Object> params, ICallback callback);
    void get(String url, HashMap<String, Object> params, ICallback callback);
}
