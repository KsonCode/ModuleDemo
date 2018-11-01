package com.example.kson.lib_net.network;

public class BaseResponse<T> extends Response
{

    private T data;


    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        data = data;
    }
}
