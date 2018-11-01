package com.example.kson.lib_net.network.http;


import com.example.kson.lib_net.network.rx.RxManager;

public interface ICallback{

    void onErrorMsg(int code, String msg);

    void onNext(String t, RxManager rxManager);
}
