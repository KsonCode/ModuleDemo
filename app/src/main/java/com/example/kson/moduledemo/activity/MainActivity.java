package com.example.kson.moduledemo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.kson.lib_core.base.mvp.BaseMvpActivity;
import com.example.kson.lib_core.base.mvp.BasePresenter;
import com.example.kson.lib_net.ApiService;
import com.example.kson.lib_net.network.OkhttpHelper;
import com.example.kson.lib_net.network.http.HttpRequestPresenter;
import com.example.kson.lib_net.network.http.ModelCallback;
import com.example.kson.lib_net.network.http.RetrofitHttpRequest;
import com.example.kson.moduledemo.R;
import com.example.kson.moduledemo.UserApi;
import com.example.kson.moduledemo.common.Constants;
import com.example.kson.moduledemo.contract.LoginContract;
import com.example.kson.moduledemo.entity.UploadEntity;
import com.example.kson.moduledemo.presenter.LoginPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
//import com.example.kson.lib_net.network.RetrofitHelper;
//import com.example.kson.lib_core.constants.Apath;

public class MainActivity extends BaseMvpActivity<LoginContract.ILoginModel, LoginContract.LoginPresenter> implements LoginContract.ILoginView ,H.Callback {

    @Override
    protected void initView() {
        Button button = findViewById(R.id.fromg);
        button.setTextSize(sp2px(20));//单位是什么？
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

    public void success(View view) {
        SPUtils.getInstance().put("sessionId","888888");
    }


    /**
     * convert dp to its equivalent px
     */
    protected int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics());
    }

    /**
     * convert sp to its equivalent px
     */
    protected int sp2px(int sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,getResources().getDisplayMetrics());
    }

    /**
     * 上传呢文件
     * @param view
     */
    public void upload(View view) {

        String path = getExternalCacheDir().getAbsolutePath();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

      List<File> files = new ArrayList<>();
      File file = new File(path.concat("/1.jpg"));
      File file2 = new File(path.concat("/2.jpg"));
      File file3 = new File(path.concat("/3.jpg"));
      files.add(file);
      files.add(file2);
      files.add(file3);

      HashMap<String,String> content = new HashMap<>();
      content.put("content","我是段子内容");

      HttpRequestPresenter.getInstance().uploadFile("techApi/community/verify/v1/releasePost", content, filesToMultipartBodyParts(files), new ModelCallback<UploadEntity>(false,UploadEntity.class) {
          @Override
          public void onErrorMsg(int code, String msg) {

          }

          @Override
          public void onSuccess(UploadEntity uploadEntity) {

          }

          @Override
          public void onSuccessMsg(String status, String message) {
              System.out.println("success:"+message);
          }
      });






    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    @Override
    public void receive(int i) {

    }
}
