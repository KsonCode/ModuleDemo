package com.example.kson.moduledemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.example.andfix.AndFixService;
import com.example.kson.moduledemo.R;

public class AndFixActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_fix);

        Button button = findViewById(R.id.createBug);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printLog();
            }
        });
    }


    /**
     * 模拟错误
     */
    private void printLog() {
        String error = "修复的bug";
//        String error = null;
        ToastUtils.showShort(error + "");
//        Log.e("kson",error);
    }

    /**
     * 修复bug
     *
     * @param view
     */
    public void fixBug(View view) {

//        String mPatchFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apatch/";
//        LogUtils.e("patchDir:",mPatchFileDir);
//        File patchDir = new File(mPatchFileDir);
//
//        try {
//            if (patchDir == null || !patchDir.exists()) {
//                patchDir.mkdirs();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String mPatchFile = mPatchFileDir.concat("n").
//                concat(".apatch");
//
//        LogUtils.e("path:"+mPatchFile);

//        AndFixPatchManager.getInstance().addPatch(mPatchFile);

    }

}
