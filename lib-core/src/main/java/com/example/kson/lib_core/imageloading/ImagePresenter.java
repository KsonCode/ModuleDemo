package com.example.kson.lib_core.imageloading;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

public class ImagePresenter implements ImageLoading {

    public static final int ANIMATION_NONE=1;
    public static final int ANIMATION_CROSSFADE=2;
    private static volatile ImagePresenter instance;
    private ImageLoading imageLoading;

    public ImagePresenter(ImageLoading imageLoading) {
        this.imageLoading = imageLoading;
    }

    public static void init(ImageLoading imageLoading) {
        if (instance == null) {
            synchronized (ImagePresenter.class) {
                if (instance == null) {
                    instance = new ImagePresenter(imageLoading);
                }
            }
        }
    }

    public static ImagePresenter getInstance() {
        return instance;
    }


    @Override
    public ImageLoading build(Context context) {
        imageLoading.build(context);
        return imageLoading;
    }

    @Override
    public ImageLoading build(Activity activity) {
        imageLoading.build(activity);
        return imageLoading;
    }

    @Override
    public ImageLoading build(Fragment fragment) {
        imageLoading.build(fragment);
        return imageLoading;
    }

    @Override
    public ImageLoading load(String url) {
        imageLoading.load(url);
        return imageLoading;
    }

    @Override
    public ImageLoading transition(int type) {
        imageLoading.transition(type);
        return imageLoading;
    }

    @Override
    public ImageLoading placeholder(int resourceId) {
        imageLoading.placeholder(resourceId);
        return imageLoading;
    }

    @Override
    public ImageLoading error(int resourceId) {
        imageLoading.error(resourceId);
        return imageLoading;
    }

    @Override
    public ImageLoading transformCircular() {
        imageLoading.transformCircular();
        return imageLoading;
    }

    @Override
    public ImageLoading apply(ImageView imageView) {
        imageLoading.apply(imageView);
        return imageLoading;
    }
}
