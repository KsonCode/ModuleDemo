package com.example.kson.lib_core.base.mvp;


import com.example.kson.lib_core.base.BaseActivity;
import com.example.kson.lib_net.network.rx.RxManager;

public abstract class BaseMvpActivity<M extends IBaseModel,P extends BasePresenter> extends BaseActivity implements IBaseView{

    public M model;
    public P presenter;

    @Override
    protected void initData() {
        presenter = (P) initPresenter();
        if (presenter!=null){
            model = (M) presenter.getmModel();
            if (model!=null){
                presenter.attach(model,this);
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();//解除绑定，回收资源，释放内存，提高性能
        }
    }
}
