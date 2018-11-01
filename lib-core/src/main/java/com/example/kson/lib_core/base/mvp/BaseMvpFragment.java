package com.example.kson.lib_core.base.mvp;

import com.example.kson.lib_core.base.BaseFragment;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/06
 * Description:todo
 */
public abstract class BaseMvpFragment<M extends IBaseModel,P extends BasePresenter> extends BaseFragment implements IBaseView {
    public M model;
    public P presenter;
    @Override
    public void initData() {
        presenter = (P) initPresenter();
        if (presenter!=null){
            model = (M) presenter.getmModel();
            if (model!=null){
                presenter.attach(model,this);
            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();//解除绑定，回收资源，释放内存，提高性能
        }
    }
}
