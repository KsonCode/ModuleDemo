package com.example.kson.lib_net.network.rx;

import java.util.List;

import io.reactivex.functions.Consumer;

public abstract class MyConsumer<T> implements Consumer<T> {

    String tag;

    public MyConsumer setTag(String tag) {

        this.tag = tag;
        return this;
    }

    @Override
    public void accept(T o) {
        if (o instanceof List) {
            if (((List)o).size()<=0) {
                return;
            }
            Object item=((List) o).get(0);
            accept(o,(T)item);
        }else {
            accept(o,o);
        }

    }

    public abstract void accept(T list,T t);
}
