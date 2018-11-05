package com.example.kson.lib_core.base.mvp;

import com.example.kson.lib_net.network.rx.MyConsumer;
import com.example.kson.lib_net.network.rx.RxManager;

import java.lang.ref.WeakReference;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/04
 * Description:
 */
public abstract class BasePresenter<M, V> {
    public M mModel;
//    private RxManager rxManager;
    private WeakReference<V> mViews;

    public abstract M getmModel();

    /**
     * 绑定view
     *
     * @param mModel
     * @param mView
     */
    public void attach(M mModel, V mView) {
        this.mModel = mModel;
//        rxManager = new RxManager();
        mViews = new WeakReference<>(mView);
    }

    protected V getView() {
        return isViewAttached() ? mViews.get() : null;
    }

    private boolean isViewAttached() {
        return null != mViews && null != mViews.get();
    }

//    protected RxManager getRxManager() {
//        return rxManager;
//    }

    /**
     * 解绑
     */
    public void detach() {

        if (mViews != null) {
            mViews.clear();//清空若引用对象
            mViews = null;
        }

//        if (rxManager != null) {
//            rxManager.clear();
//        }


    }

//    @Override
//    public void accept(Object list, Object o) {
//        accepts(list,o);
//    }
//
//    protected abstract void accepts(Object list, Object o);
}
