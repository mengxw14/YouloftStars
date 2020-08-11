package com.example.youloftstars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLayoutPalmistry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    private void initView(){
        mLayoutPalmistry = findViewById(R.id.layout_shouxiang);
    }
    private void initEvent(){
        mLayoutPalmistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PalmistryActivity.class);
                startActivity(intent);
            }
        });
    }

}