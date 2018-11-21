package com.example.kson.lib_net.network;

public class BaseResponse<T> extends Response
{

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
