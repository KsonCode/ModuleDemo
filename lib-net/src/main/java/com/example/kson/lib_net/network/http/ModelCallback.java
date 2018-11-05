package com.example.kson.lib_net.network.http;


import com.blankj.utilcode.util.LogUtils;
import com.example.kson.lib_net.NetConstants;
import com.example.kson.lib_net.network.BaseObserver;
import com.example.kson.lib_net.network.BaseResponse;
import com.example.kson.lib_net.network.ParameterizedTypeImpl;
import com.example.kson.lib_net.network.Response;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.lib_net.network.rx.exception.ApiException;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

public abstract class ModelCallback<T> implements ICallback {

    Class beanClass;
    private boolean isList;

    public ModelCallback(boolean isList, Class beanClass) {
        this.isList = isList;
        this.beanClass = beanClass;
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
            result = reader.substring(0, reader.length() - 1) + ",\"result\":{}}";
        }

        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(BaseResponse.class, new Type[]{listType});
        return new Gson().fromJson(result, type);
    }

    @Override
    public void onNext(final String dataBean, RxManager rxManager) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(dataBean);
            }
        }).map(new Function<String, BaseResponse<T>>() {
            @Override
            public BaseResponse<T> apply(String dataBean) throws Exception {
//                Class<? extends T> geneticClass = getGenericClass(this);
                BaseResponse<T> data;
                if (isList) {
                    data = fromJsonArray(dataBean, beanClass);
                } else {
                    data = fromJsonObject(dataBean, beanClass);
                }
                //只有当返回的code==success时才成功，其余情况全部抛出错误
                if (data.getStatus().equals(NetConstants.HTTP_SUCCESS)) {

                    return data;
                } else {
                    //抛出异常,让rxjava捕获,便于统一处理
                    throw new ApiException.ServerException(data.getStatus(), data.getMessage());
                }
            }
        }).subscribe(new BaseObserver<BaseResponse<T>>(rxManager) {

            @Override
            public void onError(int errorCode, String msg) {
                LogUtils.e("数据错误码：" + errorCode + "\n" + msg);
                onErrorMsg(errorCode, msg);
            }

            @Override
            public void onNext(BaseResponse<T> dataBean) {
                onSuccess(dataBean);
            }
        });
    }

    private Class<? extends T> getGenericClass(Object object) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

    public abstract void onSuccess(BaseResponse<T> t);


}
