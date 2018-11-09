package com.example.kson.moduledemo.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kson.lib_core.base.mvp.BaseMvpActivity;
import com.example.kson.lib_core.base.mvp.BasePresenter;
import com.example.kson.lib_net.ApiService;
import com.example.kson.moduledemo.R;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.presenter.LoginPresenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
                params.put("uid","71");
//                params.put("password","222222");
//                params.put("phone", "18612991523");
//                params.put("nickName", "18612991523");
//                params.put("pwd", RsaCoder.encryptByPublicKey("111111"));
////                params.put("phone", "18612991523");
//                params.put("plateId", "12");
//                params.put("page", "1");
//                params.put("count", "5");
////                params.put("pwd", RsaCoder.encryptByPublicKey("111111"));
//
//                showToast("121212121212121");
                presenter.infoRecommendList(params);

            }
        });


        //
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //
        ApiService apiService = retrofit.create(ApiService.class);

        //
        retrofit2.Call<ResponseBody> test = apiService.test("");
        test.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private void okhttp() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.jianshu.com/u/b4e69e85aef6")
                .addHeader("user_agent","22222")
                .build();

         ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));

         executorService.allowCoreThreadTimeOut(true);


        Call call = okHttpClient.newCall(request);
        call.execute();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

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
