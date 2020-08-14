package com.example.youloftstars.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SPutils {
    public static SharedPreferences getSp(Context c){
        SharedPreferences sp = c.getSharedPreferences(Constant.TABLE,Context.MODE_PRIVATE);
        return sp;
    }
    @SuppressLint("CommitPrefEdits")
    public static boolean getBoolean(Context c, String key){
        SharedPreferences sp = getSp(c);
        SharedPreferences.Editor editor = sp.edit();
        return sp.getBoolean(Constant.TUISONG,false);
    }
    public static void putBoolean(Context c,String key,Boolean b){
        SharedPreferences sp = getSp(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,b);
        editor.commit();
    }
}
