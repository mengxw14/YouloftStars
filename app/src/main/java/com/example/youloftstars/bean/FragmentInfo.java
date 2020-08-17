package com.example.youloftstars.bean;

import androidx.fragment.app.Fragment;

public class FragmentInfo {
    Fragment mFragment;
    String title;
    public FragmentInfo(Fragment fragment,String title){
        this.mFragment = fragment;
        this.title = title;
    }

    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
