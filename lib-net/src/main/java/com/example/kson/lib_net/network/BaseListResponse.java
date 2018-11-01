package com.example.kson.lib_net.network;

import java.util.List;

public class BaseListResponse<T> extends Response {
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        data = data;
    }


}
