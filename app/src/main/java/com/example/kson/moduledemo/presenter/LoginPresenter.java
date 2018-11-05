package com.example.kson.moduledemo.presenter;

import com.example.kson.lib_core.utils.ToastUtils;
import com.example.kson.lib_net.network.BaseResponse;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/01
 * Description:
 */
public class LoginPresenter extends LoginContract.LoginPresenter{

    @Override
    public void login(HashMap<String, Object> params) {
        getRxManager().on("login",this);
        mModel.login(params,getRxManager());
    }

    @Override
    protected void accepts(Object list, Object o) {
        if (o instanceof BaseResponse){
            getView().success((BaseResponse<UserEntity>) o);
        }else if(o instanceof String){
            getView().fail((String) o);
        }

    }
}
