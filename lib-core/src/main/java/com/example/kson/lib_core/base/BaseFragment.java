package com.example.kson.lib_core.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kson.lib_core.R;
import com.example.kson.lib_core.utils.KeyboardUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/03/15
 * Description:
 */
public abstract class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;
//    Toolbar toolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    /**
     * 绑定布局控件
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this,view);
        initData();


    }

    /**
     * 初始化数据
     */
    public abstract void initData() ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


//    public BaseFragment initToolBarAsHome(String title, View view) {
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if (toolbar != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                toolbar.setElevation(9.0f);
//            }
//            activity.setSupportActionBar(toolbar);
//            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//            toolbaTitle.setText(title);
//        }
//        ActionBar actionBar = activity.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        return this;
//    }

//    public BaseFragment showTitleLeftImg() {
//        ImageView im = ((ImageView) toolbar.findViewById(R.id.title_left_img));
//        im.setVisibility(View.VISIBLE);
//        return this;
//    }

//    public BaseFragment showRightText(String text) {
//        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
//        tv.setVisibility(View.VISIBLE);
//        tv.setText(text);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickRightText(tv);
//            }
//        });
//        return this;
//    }

//    public BaseFragment hideRightText(String text) {
//        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
//        tv.setVisibility(View.VISIBLE);
//        tv.setText(text);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickRightText(tv);
//            }
//        });
//        return this;
//    }

//    public BaseFragment showRightText(String text, int color) {
//        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
//        tv.setVisibility(View.VISIBLE);
//        tv.setTextColor(getResources().getColor(color));
//        tv.setText(text);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickRightText(tv);
//            }
//        });
//        return this;
//    }

//    public BaseFragment showLeftImg1(int img) {
//        ImageView tv = ((ImageView) toolbar.findViewById(R.id.tv_LeftImag1));
//        tv.setVisibility(View.VISIBLE);
//        tv.setImageResource(img);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickLeftImg();
//            }
//        });
//        return this;
//    }

//    public BaseFragment showRightImg1(int img) {
//        ImageView im = ((ImageView) toolbar.findViewById(R.id.tv_RightImg1));
//        im.setVisibility(View.VISIBLE);
//        im.setImageResource(img);
//        im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickRightImg1();
//            }
//        });
//        return this;
//    }

//    public BaseFragment showRightImg2(int img) {
//        ImageView im = ((ImageView) toolbar.findViewById(R.id.tv_RightImg2));
//        im.setVisibility(View.VISIBLE);
//        im.setImageResource(img);
//        im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickRightImg2(v);
//            }
//        });
//        return this;
//    }

    public void clickRightImg1() {
    }

    public void clickRightImg2(View v) {
    }

    public void clickRightText(TextView tv) {
    }

    public void clickLeftImg() {
    }

    /**
     * 跳转到下一个Activity，不销毁当前Activity
     *
     * @param cls 下一个Activity的Class
     */
    public void forward(Class<?> cls) {
        KeyboardUtil.closeKeyboard(getActivity());
        startActivity(new Intent(getActivity(), cls));
        executeForwardAnim();
    }

    public void forward(Intent intent) {
        KeyboardUtil.closeKeyboard(getActivity());
        startActivity(intent);
        executeForwardAnim();
    }

    public void forward(Intent intent, int i) {
        KeyboardUtil.closeKeyboard(getActivity());
        startActivityForResult(intent, i);
        executeForwardAnim();
    }

    public void forwardForActivity(Intent intent, int i) {
        KeyboardUtil.closeKeyboard(getActivity());
        getActivity().startActivityForResult(intent, i);
        executeForwardAnim();
    }

    /**
     * 执行跳转到下一个Activity的动画
     */
    public void executeForwardAnim() {
//        getActivity().overridePendingTransition(R.anim.activity_forward_enter, R.anim.activity_forward_exit);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
