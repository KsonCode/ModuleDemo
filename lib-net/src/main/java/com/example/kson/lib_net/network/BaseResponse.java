package com.example.kson.lib_net.network;

public class BaseResponse<T> extends Response
{

    private T result;


    public T getData()
    {
        return result;
    }

    public void setData(T data)
    {
        this.result = data;
    }
}
