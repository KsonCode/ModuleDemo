package com.example.kson.lib_net.network;


import com.example.kson.lib_net.NetConstants;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.lib_net.network.rx.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 */
public abstract class BaseObserver<T> implements Observer<T> {
//    protected RxManager rxManager;

//    public BaseObserver(RxManager rxManager) {
//        this.rxManager = rxManager;
//    }
    public BaseObserver() {
    }
//    @Override
//    public void onSubscribe(Disposable d) {
////        rxManager.add(d);
//    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        //统一处理错误
        String msg = ApiException.handlerException(e).getMsg();
        int errorCode = ApiException.handlerException(e).getErrorCode();
//        if (msg.length() > 64) {
//            msg = msg.substring(0, 64);
//        }

        if (errorCode == NetConstants.EXPIRED_TOKEN){
            //跳转至登录页面
           /* Intent intent = new Intent(NetApp.getAppContext(), LoginActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
            NetApp.getAppContext().startActivity(intent);*/
        }
        onError(errorCode,msg);
    }

    /**
     * 返回错误字符串
     *
     * @param msg
     */
    public abstract void onError(int errorCode,String msg);

    @Override
    public abstract void onNext(T t);

}
