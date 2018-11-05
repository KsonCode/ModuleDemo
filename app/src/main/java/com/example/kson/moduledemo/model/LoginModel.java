package com.example.kson.moduledemo.model;

import com.blankj.utilcode.util.ToastUtils;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.moduledemo.common.Constants;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.UserEntity;

import java.util.HashMap;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/01
 * Description:
 */
public class LoginModel implements LoginContract.ILoginModel {

    @Override
    public void login(HashMap<String, Object> params, final RxManager rxManager) {

        HttpRequestPresenter.getInstance().post(Constants.CHECK_URL, params, rxManager, new ModelCallback<UserEntity>(false,UserEntity.class) {
            @Override
            public void onErrorMsg(int code, String msg) {
                ToastUtils.showLong(msg);
//                rxManager.post("login",code +""+msg);
            }

            @Override
            public void onSuccess(UserEntity userEntity) {
                rxManager.post("login",userEntity);

            }
        });
    }
}
