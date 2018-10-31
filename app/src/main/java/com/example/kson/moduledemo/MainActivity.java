package com.example.kson.moduledemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.kson.lib_core.constants.Apath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.fromg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ARouter.getInstance().build("/home/home2").navigation(MainActivity.this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                    }
                });

            }
        });

//        Fragment fragmentHome = (Fragment) ARouter.getInstance().build(Apath.HOME_MAIN).navigation();
//
//
//
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fr_content, fragmentHome)
////                .add(R.id.fl_Content, marketFragment)
////                .add(R.id.fl_Content, messageFragment)
////                .add(R.id.fl_Content, mineFragment)
////                .add(R.id.fl_Content, walletFragment)
//                .show(fragmentHome)
////                .hide(marketFragment)
////                .hide(messageFragment)
////                .hide(mineFragment)
////                .hide(walletFragment)
//                .commit();


    }

    public void home(View view) {

        //集成开发模式，可以调用
//        startActivity(new Intent(this, PushActivity.class));

    }
}
