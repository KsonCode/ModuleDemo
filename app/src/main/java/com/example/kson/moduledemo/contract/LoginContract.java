package com.example.kson.moduledemo.contract;

import com.example.kson.lib_core.base.mvp.BasePresenter;
import com.example.kson.lib_core.base.mvp.IBaseModel;
import com.example.kson.lib_core.base.mvp.IBaseView;
import com.example.kson.lib_net.network.PresenterCallback;
import com.example.kson.lib_net.network.BaseResponse;
import com.example.kson.moduledemo.entity.News;
import com.example.kson.moduledemo.entity.UserEntity;
import com.example.kson.moduledemo.model.LoginModel;

import java.util.HashMap;
import java.util.List;

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
        public abstract void checkPhone(HashMap<String,Object> params);
        public abstract void infoRecommendList(HashMap<String,Object> params);

    }

    /**
     * model层接口
     */
    interface ILoginModel extends IBaseModel {

        void checkPhone(HashMap<String,Object> params, PresenterCallback<UserEntity> callback);
        void infoRecommendList(HashMap<String,Object> params, PresenterCallback<List<News>> callback);
        void login(HashMap<String,Object> params, PresenterCallback<UserEntity> callback);

    }

    /**
     * view层接口
     */
    interface ILoginView extends IBaseView {

        //


    }
}
