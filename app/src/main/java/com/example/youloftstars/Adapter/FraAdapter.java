package com.example.youloftstars.Adapter;

import android.content.Context;

import com.example.youloftstars.bean.FragmentInfo;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FraAdapter extends FragmentStatePagerAdapter {
    ArrayList<FragmentInfo> info;
    Context context;
    public FraAdapter(Context mContext, FragmentManager fm, ArrayList<FragmentInfo> info) {
        super(fm);
        this.info = info;
        this.context = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        FragmentInfo fragmentInfo = info.get(position);
        return fragmentInfo.getmFragment();
        //return Fragment.instantiate(context,fragmentInfo.getClass_name());
    }

    @Override
    public int getCount() {
        return info.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return info.get(position).getTitle();
    }
}
