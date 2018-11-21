package com.example.kson.moduledemo.activity;

public class H {


    Callback callback;
    public void receive(){
        int i = 1;

        if (callback!=null){
            callback.receive(i);
        }


    }


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback{
        void receive(int i);
    }

}
