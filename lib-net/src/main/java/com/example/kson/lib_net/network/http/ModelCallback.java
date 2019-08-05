package com.example.kson.lib_net.network.http;


import com.blankj.utilcode.util.LogUtils;
import com.example.kson.lib_net.NetConstants;
import com.example.kson.lib_net.network.BaseObserver;
import com.example.kson.lib_net.network.BaseResponse;
import com.example.kson.lib_net.network.ParameterizedTypeImpl;
import com.example.kson.lib_net.network.rx.exception.ApiException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public abstract class ModelCallback<T> implements ICallback {
    Class tClass;
    private boolean isList;

    /**
     *
     * @param islist
     * @param tClass
     */
    public ModelCallback(boolean islist,Class tClass){
        this.isList = islist;
        this.tClass = tClass;
    }
    /**
     *
     * @param tClass
     */
    public ModelCallback(Class tClass){
        this.tClass = tClass;
    }

    public static <T> BaseResponse<T> fromJsonObject(String reader, Class<T> clazz) {
        String result = "";
        if (reader.contains("result")) {
            result = reader;
        } else {
            result = reader.substring(0, reader.length() - 1) + ",\"result\":{}}";
        }
        Type type = new ParameterizedTypeImpl(BaseResponse.class, new Class[]{clazz});
        return new Gson().fromJson(result, type);
    }

    public static <T> BaseResponse<List<T>> fromJsonArray(String reader, Class<T> clazz) {
        String result = "";
        if (reader.contains("result")) {
            result = reader;
        } else {
            result = reader.substring(0, reader.length() - 1) + ",\"result\":[]}";
        }

        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(BaseResponse.class, new Type[]{listType});
        return new GsonBuilder().serializeNulls().create().fromJson(result, type);
    }

    @Override
    public void onNext(final String dataBean) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(dataBean);
            }
        }).map(new Function<String, T>() {
            @Override
            public T apply(String dataBean) throws Exception {
//                Class<? extends T> geneticClass = getGenericClass(this);
                BaseResponse<T> data = null;
//
                if (isList) {
                    data = fromJsonArray(dataBean, tClass);
                } else {
                    data = fromJsonObject(dataBean,tClass);
                }
                //只有当返回的code==success时才成功，其余情况全部抛出错误
                if (data.getStatus().equals(NetConstants.HTTP_SUCCESS)) {
                    onSuccessMsg(data.getStatus(),data.getMessage());
                    return data.getResult();
                } else {
                    //抛出异常,让rxjava捕获,便于统一处理
                    throw new ApiException.ServerException(data.getStatus(), data.getMessage());
                }
            }
        }).subscribe(new BaseObserver<T>() {
            @Override
            public void onError(int errorCode, String msg) {
                LogUtils.e("数据错误码：" + errorCode + "\n" + msg);
                onErrorMsg(errorCode, msg);
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T dataBean) {
                onSuccess(dataBean);
            }


        });
    }

    public abstract void onSuccess(T t);
    public abstract void onSuccessMsg(String status,String message);


}
