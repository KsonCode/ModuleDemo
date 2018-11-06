package com.example.kson.lib_net.network.http;

public interface ICallback{

    void onErrorMsg(int code, String msg);
    void onNext(String t);

}
