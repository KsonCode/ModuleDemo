package com.example.kson.moduledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kson.lib_core.base.mvp.BaseMvpActivity;
import com.example.kson.lib_core.base.mvp.BasePresenter;
import com.example.kson.lib_core.utils.ToastUtils;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;
import com.example.kson.lib_net.network.rx.RxManager;
import com.example.kson.lib_net.utils.publickeytool.RsaCoder;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.UserEntity;
import com.example.kson.moduledemo.model.LoginModel;
import com.example.kson.moduledemo.presenter.LoginPresenter;

import java.util.HashMap;
//import com.example.kson.lib_net.network.RetrofitHelper;
//import com.example.kson.lib_core.constants.Apath;

public class MainActivity extends BaseMvpActivity<LoginContract.ILoginModel, LoginContract.LoginPresenter> implements LoginContract.ILoginView {


    @Override
    protected void initView() {
        Button button = findViewById(R.id.fromg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> params = new HashMap<>();
//                params.put("phone", "18612991023");
//                params.put("pwd", RsaCoder.encryptByPublicKey("111111"));
                params.put("phone", "18612991023");
                params.put("pwd", RsaCoder.encryptByPublicKey("111111"));

                presenter.login(params);


            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    public void home(View view) {

        //集成开发模式，可以调用
//        startActivity(new Intent(this, PushActivity.class));

    }

    @Override
    public void success(UserEntity userEntity) {

        com.blankj.utilcode.util.ToastUtils.showLong(userEntity.phone);

        System.out.println("userentity:" + userEntity.phone);

    }

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void fail(String msg) {

    }
}
