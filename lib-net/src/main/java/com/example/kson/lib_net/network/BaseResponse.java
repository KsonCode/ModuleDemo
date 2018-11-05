package com.example.kson.lib_net.network;

public class BaseResponse<T> extends Response
{

    private T data;

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }
}
