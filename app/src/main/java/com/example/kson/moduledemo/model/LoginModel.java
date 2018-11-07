package com.example.kson.moduledemo.model;

import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.kson.lib_net.network.PresenterCallback;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;
import com.example.kson.moduledemo.common.Constants;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.ContactsEntity;
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
    public void checkPhone(HashMap<String, Object> params, PresenterCallback<UserEntity> callback) {
        HttpRequestPresenter.getInstance().post(Constants.CHECK_URL, params, new ModelCallback<UserEntity>(false
        ,UserEntity.class) {
            @Override
            public void onErrorMsg(int code, String msg) {
                ToastUtils.showLong(msg);
            }

            @Override
            public void onSuccess(UserEntity userEntity) {

            }

            @Override
            public void onSuccessMsg(String status, String message) {

            }
        });
    }

    @Override
    public void infoRecommendList(HashMap<String, Object> params, final PresenterCallback<List<ContactsEntity>> callback) {
        HttpRequestPresenter.getInstance().get(Constants.NEWS_URL, params, new ModelCallback<List<ContactsEntity>>(true,ContactsEntity.class) {
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
            public void onSuccess(List<ContactsEntity> news) {
                callback.onSuccess(news);
                ToastUtils.showShort(news.size()+"");
            }

            @Override
            public void onSuccessMsg(String status, String message) {

//                ToastUtils.showLong(message+"");
                callback.onSuccessMsg(status,message);
            }




        });
    }

    @Override
    public void login(HashMap<String, Object> params, PresenterCallback<UserEntity> callback) {
            HttpRequestPresenter.getInstance().post(Constants.LOGIN_URL, params, new ModelCallback<UserEntity>(false,UserEntity.class) {
                @Override
                public void onErrorMsg(int code, String msg) {
                    ToastUtils.showLong(msg);
                }

                @Override
                public void onSuccess(UserEntity userEntity) {

//                    ToastUtils.showLong(userEntity.userId);
                }

                @Override
                public void onSuccessMsg(String status, String message) {
                    ToastUtils.showLong(message);
                }
            });
    }

    @Override
    public void reg(HashMap<String, Object> params, PresenterCallback<UserEntity> callback) {
        HttpRequestPresenter.getInstance().post(Constants.REG_URL, params, new ModelCallback<UserEntity>(false,UserEntity.class) {
            @Override
            public void onErrorMsg(int code, String msg) {
                ToastUtils.showLong(msg);
            }

            @Override
            public void onSuccess(UserEntity userEntity) {

//                    ToastUtils.showLong(userEntity.userId);
            }

            @Override
            public void onSuccessMsg(String status, String message) {
                ToastUtils.showLong(message);
            }
        });
    }
}
