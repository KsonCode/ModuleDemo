package com.example.kson.moduledemo.contract;

import com.example.kson.lib_core.base.mvp.BasePresenter;
import com.example.kson.lib_core.base.mvp.IBaseModel;
import com.example.kson.lib_core.base.mvp.IBaseView;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.moduledemo.entity.UserEntity;
import com.example.kson.moduledemo.model.LoginModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/10/31
 * Description:
 */
public interface LoginContract {

    abstract class LoginPresenter extends BasePresenter<ILoginModel, ILoginView> {

        @Override
        public ILoginModel getmModel() {
            return new LoginModel();
        }

        public abstract void login(HashMap<String,Object> params);

    }

    /**
     * model层接口
     */
    interface ILoginModel extends IBaseModel {

        void login(HashMap<String,Object> params,RxManager rxManager);

    }

    /**
     * view层接口
     */
    interface ILoginView extends IBaseView {

        void success(UserEntity userEntity);

        void fail(String msg);

    }
}
