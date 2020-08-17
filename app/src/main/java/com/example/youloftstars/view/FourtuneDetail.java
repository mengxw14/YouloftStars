package com.example.youloftstars.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youloftstars.Fragment.TodayFragment;
import com.example.youloftstars.R;

import java.util.ArrayList;
import java.util.List;

public class FourtuneDetail extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private String[] titleString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourtune_detail);
        initView();
//        initData();
        initEvent();
    }
    private void initView(){

    }
//    private void initData(){
//        titleString = getResources().getStringArray(R.array.yunshi_titles);
//        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new TodayFragment());
//        fragmentList.add(new TodayFragment());
//        fragmentList.add(new TodayFragment());
//        fragmentList.add(new TodayFragment());
//        fragmentList.add(new TodayFragment());
//        fragmentList.add(new TodayFragment());
//        tabHost = findViewById(android.R.id.tabhost);
//        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
//        for(int i = 0;i < fragmentList.size();i ++){
//            TabHost.TabSpec tabSpec = tabHost.newTabSpec(String.valueOf(i))
//                    .setIndicator(getEveryView(i));
//            tabHost.addTab(tabSpec,fragmentList.get(i).getClass(),null);
//        }
//        tabHost.setCurrentTab(0);
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            //String s -- 点击的tab标签名
//            public void onTabChanged(String s) {
//                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
//                TabChange(s);
//                //tabhost.setCurrentTab();
//            }
//        });
//
//       // FragmentManager fragmentManager = getSupportFragmentManager();
//        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        //fragmentTransaction.add(viewgroup fragment)
//        //TodayFragment fragment = new TodayFragment();
//        //fragmentTransaction.add(R.id.fourtune_frame,fragment);
//        //fragmentTransaction.commit();
//    }
    private void TabChange(String s){
        //改变tab的颜色
        int tabIndex = tabHost.getCurrentTab();
        for(int i = 0; i < 5; i ++){
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView text = view.findViewById(R.id.fourtune_tab_item_title);
            if(i == tabIndex){
                text.setTextSize(20);
                text.setTextColor(0xFFE5D8FF);
            }else {
                text.setTextSize(14);
                text.setTextColor(0xFF886FB7);
            }
        }
    }
    private void initEvent(){

    }
    private View getEveryView(int i){
        View view = View.inflate(getApplicationContext(),R.layout.fourtune_tab_item,null);
        TextView text = view.findViewById(R.id.fourtune_tab_item_title);
        if(i == 0){
            text.setTextSize(20);
            text.setTextColor(0xFFE5D8FF);
        }else {
            text.setTextSize(14);
            text.setTextColor(0xFF886FB7);
        }
        text.setText(titleString[i]);
        return view;
    }
}