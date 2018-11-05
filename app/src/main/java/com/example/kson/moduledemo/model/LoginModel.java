package com.example.kson.moduledemo.model;

import com.blankj.utilcode.util.ToastUtils;
import com.example.kson.lib_net.network.PresenterCallback;
import com.example.kson.lib_net.network.BaseResponse;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;
import com.example.kson.moduledemo.common.Constants;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.News;
import com.example.kson.moduledemo.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/11/01
 * Description:
 */
public class LoginModel implements LoginContract.ILoginModel {



    @Override
    public void login(HashMap<String, Object> params, final PresenterCallback<UserEntity> callback) {
        HttpRequestPresenter.getInstance().get(Constants.NEWS_URL, params, new ModelCallback<UserEntity>(false,UserEntity.class) {
            @Override
            public void onErrorMsg(int code, String msg) {
                callback.onErrorMsg(code,msg);
            }
//            @Override
//            public void onSuccess(List<News> news) {
//                System.out.println("size:"+news.size());
//                callback.onSuccess(news);
//            }

            @Override
            public void onSuccess(UserEntity userEntity) {
                ToastUtils.showLong(userEntity.mobile+"");
            }

            @Override
            public void onSuccessMsg(String status, String message) {

//                ToastUtils.showLong(message+"");
                callback.onSuccessMsg(status,message);
            }



        });
    }
}
