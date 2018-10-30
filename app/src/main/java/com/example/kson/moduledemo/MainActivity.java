package com.example.kson.moduledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kson.module_home.Main2Activity;
import com.example.kson.module_push.PushActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void home(View view) {

        //集成开发模式，可以调用
        startActivity(new Intent(this, PushActivity.class));

    }
}
