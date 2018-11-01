package com.example.kson.lib_core.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by pwj on 2017/11/20.
 */

public class DisplayUtil {

    public static int screenWidthPx; //屏幕宽 px
    public static int screenhightPx; //屏幕高 px
    public static float density;//屏幕密度
    public static int densityDPI;//屏幕密度
    public static float screenWidthDip;//  dp单位
    public static float screenHightDip;//  dp单位


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断收否该隐藏软件盘
     *
     * @param v     被点击的view
     * @param event 点击事件
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        Log.i("DisplayUtil", "----isShouldHideInput-v: " + v + " --- " +
                "" + event.getX() + " " + event.getY());
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取软键盘的高度
     * @param v 软键盘弹出页面的View
     * @return
     */
    public static int getKeyboardHeight(View v) {
        //获取窗口可见区域大小
        Rect r = new Rect();
        v.getWindowVisibleDisplayFrame(r);
        //获取整个屏幕的高度
        int screenHeight = v.getRootView().getHeight();
        Log.e("DisplayUtil", "------------Keyboard Size, bottom: " + r.bottom + " -top: " + r.top);
        Log.e("DisplayUtil", "------------Keyboard Size, screenHeight: " + screenHeight + " -v.getHeight(): " + v.getHeight());
        int heightDifference = screenHeight - r.bottom - (screenHeight - v.getHeight());
        Log.e("DisplayUtil", "------------Keyboard Size, Size: " + heightDifference);
        return heightDifference;
    }
}


