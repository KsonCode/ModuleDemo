package com.example.kson.lib_core.base;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kson.lib_core.utils.KeyboardUtil;

import java.util.List;

/**
 * Created by kson on 2017/12/14.
 *
 *
 */

public class BaseAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    private Activity activity;

    public BaseAdapter(Activity activity, int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        this.activity=activity;
    }

    public Activity getActivity(){
        return activity;
    }

    @Override
    protected void convert(K helper, T item) {

    }

    public void forward(Class<?> cls) {
        KeyboardUtil.closeKeyboard(activity);
        activity.startActivity(new Intent(activity,cls));
//        activity.overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);

    }

    public void forward(String intent) {
        KeyboardUtil.closeKeyboard(activity);
        activity.startActivity(new Intent(intent));
//        activity.overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);

    }

    public void forward(Intent intent) {
        KeyboardUtil.closeKeyboard(activity);
        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
    }


    public void backward() {
        KeyboardUtil.closeKeyboard(activity);
        activity.finish();
//        activity.overridePendingTransition(R.anim.activity_backward_enter, R.anim.activity_backward_exit);

    }

    public void forward(Intent intent, int i) {
        KeyboardUtil.closeKeyboard(activity);
        activity.startActivityForResult(intent,i);
//        activity.overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
    }

}
