package com.example.youloftstars.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.youloftstars.Fragment.TodayFragment;
import com.example.youloftstars.R;

public class FortuneDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune_detail);
        initView();
        initData();
        initEvent();
    }
    private void initView(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //viewgroup容器 和fragment
        //fragmentTransaction.add(R.id.fragment_containor,fragment);
//        TodayFragment fragment = new TodayFragment();
//        fragmentTransaction.add(R.id.xingzuo_viewpage,fragment);
//        fragmentTransaction.commit();
    }
    private void initData(){

    }
    private void initEvent(){

    }
}