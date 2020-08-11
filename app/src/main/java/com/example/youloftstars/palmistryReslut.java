package com.example.youloftstars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.youloftstars.Adapter.ShouxiangAdapter;
import com.example.youloftstars.bean.Shouxiang;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class palmistryReslut extends AppCompatActivity {
    private ImageView mShouxiang;
    private ArrayList<Shouxiang> mList;
    private TextView mLastTime;
    private Timer timer;
    private TextView mChaKan;
    private int hour;
    private int mini;
    private int second;
    //private ListView mListView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            second --;
            if(second < 0){
                mini --;
                second = 60;
            }
            if(mini < 0){
                hour --;
                mini = 59;
            }
            if(hour < 0){
                timer.cancel();
            }
            mLastTime.setText("限时优惠:" + hour + ":" + mini + ":" + second);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmistry_reslut);
        initView();
        initData();
        initEvent();
    }
    private void initView(){
        mShouxiang = findViewById(R.id.shouxiang);
        mLastTime = findViewById(R.id.last_time);
        //mListView = findViewById(R.id.lv);
        mChaKan = findViewById(R.id.lijichakan);
    }
    private void initData(){
        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("Shouxiang");
        mShouxiang.setImageBitmap(bitmap);
        mList = new ArrayList<>();
        hour = 24;
        mini = 0;
        second = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                handler.sendMessage(message);
            }
        },1000,1000);

    }
    private void initEvent(){
        mChaKan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}