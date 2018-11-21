package com.example.kson.lib_core.widget.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;

import com.blankj.utilcode.util.ScreenUtils;
import com.example.kson.lib_core.utils.AppManager;
import com.example.kson.lib_core.widget.SweetAlert.SweetAlertDialog;


public class DialogUtils {



    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    public static final int CUSTOM_IMAGE_TYPE = 4;
    public static final int PROGRESS_TYPE = 5;
    public static SweetAlertDialog buildOnUiThreadForSweet(final int type, final String Title, final String content) {
        Activity context= AppManager.getInstance().getTopActivity();
        final SweetAlertDialog[] sweetAlertDialog = new SweetAlertDialog[1];
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sweetAlertDialog[0] =buildSweet(type,Title,content);

            }
        });
        return sweetAlertDialog[0];
    }
    public static SweetAlertDialog buildSweet(final int type, final String Title, String Content) {
        final Activity context= AppManager.getInstance().getTopActivity();
        ScreenUtils.cancelAdaptScreen(context);
        final SweetAlertDialog dialog=new SweetAlertDialog(context,type);
        if (ScreenUtils.isPortrait()) {
            ScreenUtils.adaptScreen4VerticalSlide(context, 360);
        } else {
            ScreenUtils.adaptScreen4HorizontalSlide(context, 360);
        }
        if (PROGRESS_TYPE == type) {
//            dialog.getProgressHelper()
            dialog.setTitleText(Title);
            dialog.setContentText(Content);
        } else {
            dialog.setTitleText(Title);
            dialog.setContentText(Content);

        }

        return dialog;
    }
}
