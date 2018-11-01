package com.example.kson.lib_core.base;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.kson.lib_core.R;
import com.example.kson.lib_core.backlayout.BGASwipeBackHelper;
import com.example.kson.lib_core.utils.AppManager;
import com.example.kson.lib_core.utils.KeyboardUtil;
import com.example.kson.lib_core.utils.NetBroadcastReceiver;
import com.example.kson.lib_core.widget.SweetAlert.SweetAlertDialog;
import com.example.kson.lib_core.widget.dialog.DialogUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements NavigationCallback,  NetBroadcastReceiver.NetChangeListener{
    private static Disposable disposable;
    public Toolbar toolbar;
    public boolean isBack;
    protected BGASwipeBackHelper mSwipeBackHelper;
    private boolean isFullScreen = false;//是否全屏
    private boolean isStatus = false;//是否沉浸式状态栏
    private boolean isToolBar = false;//是否显示标题栏
    private Unbinder unbinder = null;

    NetBroadcastReceiver netBroadcastReceiver;
    public static NetBroadcastReceiver.NetChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getInstance().addActivity(this);
//
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(this, 750);
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(this, 750);
        }
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();

        listener=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            netBroadcastReceiver = new NetBroadcastReceiver();
            //注册广播接收
            registerReceiver(netBroadcastReceiver, filter);
        }
        ToastUtils.setMsgTextSize(30);
        ToastUtils.setGravity(Gravity.CENTER,0,0);
    }

    protected abstract void initData();

    public void setFullScreen(boolean isFullScreen){

        //全屏代码
        if (isFullScreen){
//            getWindow().setFlags();
        }
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定根布局
     * @return
     */
    protected abstract int bindLayoutId();

    /**
     * 网络变化
     *
     * @param status -1 无网络 0 移动 1 无线
     */
    @Override
    public void onChangeListener(int status) {
        if (status == -1) {
            ToastUtils.showShort("无网络");
        } else if (0 == status) {
            ToastUtils.showShort("移动网络");
        } else if (1 == status) {
            ToastUtils.showShort("无线网络");
        }
    }


    public BaseActivity initToolBarAsHome(String title, boolean flag, boolean isBack) {
        this.isBack = isBack;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && flag) {
                toolbar.setElevation(9.0f);
            }
            setSupportActionBar(toolbar);
            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbaTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return this;
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, new BGASwipeBackHelper.Delegate() {
            /**
             * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
             *
             * @return
             */
            @Override
            public boolean isSupportSwipeBack() {
                return true;
            }

            /**
             * 正在滑动返回
             *
             * @param slideOffset 从 0 到 1
             */
            @Override
            public void onSwipeBackLayoutSlide(float slideOffset) {
            }

            /**
             * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
             */
            @Override
            public void onSwipeBackLayoutCancel() {
            }

            /**
             * 滑动返回执行完毕，销毁当前 Activity
             */
            @Override
            public void onSwipeBackLayoutExecuted() {
                mSwipeBackHelper.swipeBackward();
            }
        });

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(false);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(false);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        super.onBackPressed();
        if (isBack) {
            backward();
        }
    }

    public void clickBack() {
        backward();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
//        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
//        ButterKnife.bind(this);
    }

    public void initSavedInstanceState(Bundle savedInstanceState) {
    }


    public BaseActivity showLeftBack() {
        TextView tv = ((TextView) toolbar.findViewById(R.id.tv_LeftBack));
        tv.setVisibility(View.VISIBLE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBack();
            }
        });
        return this;
    }

    public BaseActivity showRightText(String text) {
        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
        tv.setVisibility(View.VISIBLE);
        tv.setText(text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightText(tv);
            }
        });
        return this;
    }

    public BaseActivity hideRightText(String text) {
        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
        tv.setVisibility(View.VISIBLE);
        tv.setText(text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightText(tv);
            }
        });
        return this;
    }

    public BaseActivity showRightText(String text, int color) {
        final TextView tv = ((TextView) toolbar.findViewById(R.id.tv_RightText));
        tv.setVisibility(View.VISIBLE);
        tv.setTextColor(getResources().getColor(color));
        tv.setText(text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightText(tv);
            }
        });
        return this;
    }

    public BaseActivity showLeftImg1(int img) {
        ImageView tv = ((ImageView) toolbar.findViewById(R.id.tv_LeftImag1));
        tv.setVisibility(View.VISIBLE);
        tv.setImageResource(img);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeftImg();
            }
        });
        return this;
    }

    public BaseActivity showRightImg1(int img) {
        ImageView tv = ((ImageView) toolbar.findViewById(R.id.tv_RightImg1));
        tv.setVisibility(View.VISIBLE);
        tv.setImageResource(img);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightImg();
            }
        });
        return this;
    }

    public BaseActivity showRightImg2(int img) {
        ImageView tv = ((ImageView) toolbar.findViewById(R.id.tv_RightImg2));
        tv.setVisibility(View.VISIBLE);
        tv.setImageResource(img);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightImg2();
            }
        });
        return this;
    }

    public void clickRightImg() {
    }

    public void clickRightImg2() {
    }

    public void clickRightText(TextView tv) {
    }

    public void clickLeftImg() {
    }

    /**
     * 跳转到下一个Activity，不销毁当前Activity
     *
     * @param cls 下一个Activity的 Path
     */
    public Postcard forward(String cls) {
        KeyboardUtil.closeKeyboard(this);
        return ARouter.getInstance().build(cls).withTransition(R.anim.bga_sbl_activity_forward_enter, R.anim.bga_sbl_activity_forward_exit);
    }

    /**
     * 回到上一个Activity，并销毁当前Activity
     */
    public void backward() {
        KeyboardUtil.closeKeyboard(this);
        finish();
        executeBackwardAnim();
    }

    /**
     * 执行回到到上一个Activity的动画
     */
    public void executeBackwardAnim() {
        overridePendingTransition(R.anim.bga_sbl_activity_backward_enter, R.anim.bga_sbl_activity_backward_exit);
    }

    /**
     * 解决fragment onActivityResult不调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FragmentManager fm = getSupportFragmentManager();
        //if (index != 0) {
        if (fm.getFragments() == null) {
            LogUtils.w("BaseActivity", "Activity result fragment fragmentIndex out of range: 0x"
                    + Integer.toHexString(requestCode));
            return;
        }
        for (int i = 0; i < fm.getFragments().size(); i++) {
            Fragment frag = fm.getFragments().get(i);
            if (frag == null) {
                Log.w("BaseActivity", "Activity result no fragment exists for fragmentIndex: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(frag, requestCode, resultCode, data);
            }
        }
        return;
        //}

    }

    /**
     * 递归调用，对所有子Fragement生效
     *
     * @param frag
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onFound(Postcard postcard) {

    }

    @Override
    public void onLost(Postcard postcard) {

    }

    @Override
    public void onArrival(Postcard postcard) {

    }

    @Override
    public void onInterrupt(Postcard postcard) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogUtils.buildSweet(DialogUtils.WARNING_TYPE, "登录提示", "登录后可以为您提供更好的服务")
                        .setCancelText("再看看")
                        .setConfirmText("去登陆")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();//释放资源，回收内存，优化性能
            unbinder = null;
        }
        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
        }
        AppManager.getInstance().killActivity(this);
    }

    /**
     * 无参数跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz){
        startActivity(new Intent(this,clz));
    }
    /**
     * 有参数跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz,Bundle bundle){
        Intent intent = new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);


    }


    /**
     * 显示toast、
     * @param msg
     */
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 显示带时间的toast
     * @param msg
     */
    public void showToast(String msg,int time){
        Toast.makeText(this, msg, time).show();
    }




}
