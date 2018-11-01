package com.example.kson.lib_core.imageloading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideLoad implements ImageLoading {
    private RequestOptions cropOptions;
    private RequestBuilder build;
    private RequestManager manager;

    @Override
    public GlideLoad build(Context context) {
        manager = Glide.with(context);
        return this;
    }

    @Override
    public GlideLoad build(Activity activity) {
        manager = Glide.with(activity);
        return this;
    }

    @Override
    public GlideLoad build(Fragment fragment) {
        manager = Glide.with(fragment);
        return this;
    }

    @Override
    public GlideLoad load(String url) {
        build = manager.load(url).transition(withCrossFade());
        cropOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        return this;
    }

    @SuppressLint("CheckResult")
    @Override
    public ImageLoading transition(int type) {
        if (type== ImagePresenter.ANIMATION_NONE) {
            build.transition(new DrawableTransitionOptions().crossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true)));
        }else if(type== ImagePresenter.ANIMATION_CROSSFADE){
            build.transition(withCrossFade());
        }
        return this;
    }

    @Override
    @SuppressLint("CheckResult")
    public GlideLoad placeholder(int resourceId) {
        cropOptions.placeholder(resourceId);
        return this;
    }

    @Override
    @SuppressLint("CheckResult")
    public GlideLoad error(int resourceId) {
        cropOptions.error(resourceId);
        return this;
    }

    @SuppressLint("CheckResult")
    @Override
    public ImageLoading transformCircular() {
        cropOptions.transform(new RoundedCorners(12));
        return this;
    }

    @Override
    public GlideLoad apply(ImageView imageView) {
        build.apply(cropOptions)
                .into(imageView);
        return this;
    }


}
