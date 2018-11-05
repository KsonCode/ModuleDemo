package com.example.kson.lib_net.network;

public class Response {
    private String code;
    private String msg;

    public String getStatus() {
        return code;
    }

    public void setStatus(String status) {
        this.code = status;
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
