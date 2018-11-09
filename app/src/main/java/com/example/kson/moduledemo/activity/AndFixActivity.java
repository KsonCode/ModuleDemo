package com.example.kson.moduledemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kson.lib_core.hotfix.AndFixPatchManager;
import com.example.kson.moduledemo.R;

import java.io.File;

public class AndFixActivity extends AppCompatActivity {

    private  final String FIELD_END = ".apatch";
    private String mPatchDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_fix);
        mPatchDir = getExternalCacheDir().getAbsolutePath()+"/apatch/";
        File file = new File(mPatchDir);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 制造bug
     * @param view
     */
    public void createBug(View view) {


        printLog();
    }

    /**
     * 模拟错误
     */
    private void printLog() {
        String error = null;
        Log.e("kson",error);
    }

    /**
     * 修复bug
     * @param view
     */
    public void fixBug(View view) {

        AndFixPatchManager.getmInstance().addPatch(getPatchPath());
    }

    /**
     * 获取patch文件路径
     * @return
     */
    private String getPatchPath() {

        return mPatchDir.concat("im").concat(FIELD_END);
    }
}
