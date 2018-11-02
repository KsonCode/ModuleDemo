package com.example.kson.lib_net.network;

public class Response {
    private int status;
    private String message;
    public int getCode()
    {
        return status;
    }

    public void setCode(int code)
    {
        this.status = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
