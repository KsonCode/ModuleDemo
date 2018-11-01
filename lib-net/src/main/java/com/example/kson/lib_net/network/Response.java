package com.example.kson.lib_net.network;

public class Response {
    private int code;
    private String msg;
    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return msg;
    }

    public void setMessage(String message)
    {
        this.msg = message;
    }
}
