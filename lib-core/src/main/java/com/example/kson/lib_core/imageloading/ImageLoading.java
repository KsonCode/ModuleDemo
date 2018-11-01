package com.example.kson.lib_core.imageloading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public interface ImageLoading {

    ImageLoading build(Context context);

    ImageLoading build(Activity activity);

    ImageLoading build(Fragment fragment);

    ImageLoading load(String url);

    ImageLoading transition(int type);

    @SuppressLint("CheckResult")
    ImageLoading placeholder(int resourceId);

    @SuppressLint("CheckResult")
    ImageLoading error(int resourceId);

    ImageLoading transformCircular();

    ImageLoading apply(ImageView imageView);
}
