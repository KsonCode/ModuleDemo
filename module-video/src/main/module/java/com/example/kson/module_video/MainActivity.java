package com.example.kson.module_video;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);



    }



    /**
     *
     * @param view
     */
    public void testHome(View view){
        ARouter.getInstance().build("/com/home2").navigation();
    }
}
