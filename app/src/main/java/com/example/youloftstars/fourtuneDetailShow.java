package com.example.youloftstars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class fourtuneDetailShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourtune_detail_show);
        FragmentManager FragmentManager = getSupportFragmentManager();
        fourtune_frame fragment = (fourtune_frame) new Fragment();
        FragmentTransaction transaction = FragmentManager.beginTransaction();
        transaction.add(R.id.fourtun_day_select,fragment);
        transaction.commit();
    }
}