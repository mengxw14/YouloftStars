package com.example.youloftstars;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.example.youloftstars.Fragment.TodayFragment;
import com.example.youloftstars.bean.AstroldData;
import com.example.youloftstars.bean.AstroldInfo;
import com.example.youloftstars.util.HttpUtils;
import com.example.youloftstars.util.RetrofitNetWork;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableTestToActivity extends AppCompatActivity {
    private TabLayout myTabLayout;
    private ViewPager myViewPager;
    private List<String>myTitle;
    private List<Fragment>myFragment;
    private ProgressBar mBar;
    private FloatingActionButton floatingActionButton;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    RetrofitNetWork fortuneInfo = HttpUtils.getFortuneInfo();
                    Call<ResponseBody> call = fortuneInfo.getAstroTb("all");
                    call.enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String json = response.body().string();
                                AstroldData data = new Gson().fromJson(json, AstroldInfo.class).getData();
                                myFragment = new ArrayList<>();
                                myFragment.add(new TodayFragment(data.getYunshi_today()));
                                myFragment.add(new TodayFragment(data.getYunshi_tomorrow()));
                                myFragment.add(new TodayFragment(data.getYunshi_week()));
                                myFragment.add(new TodayFragment(data.getYunshi_month()));
                                myFragment.add(new TodayFragment(data.getYunshi_year()));
                                //预加载
                                myViewPager.setOffscreenPageLimit(myFragment.size());
                                //适配器
                                myViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                                    //选中的item
                                    @NonNull
                                    @Override
                                    public Fragment getItem(int position) {
                                        return myFragment.get(position);
                                    }

                                    @Override
                                    public int getCount() {
                                        return myFragment.size();
                                    }

                                    @Nullable
                                    @Override
                                    public CharSequence getPageTitle(int position) {
                                        return myTitle.get(position);
                                    }
                                });
                                myTabLayout.setupWithViewPager(myViewPager);
                                mBar.setVisibility(View.GONE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_test2);
        //去掉阴影？
        //getSupportActionBar().setElevation(0);
        initData();
        initView();
    }
    private void initData(){
        myTitle = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.yunshi_titles);
        for(int i = 0;i < strings.length;i ++){
            myTitle.add(strings[i]);
        }
        Message message = Message.obtain();
        message.what = 0;
        handler.sendMessage(message);

    }
    private void initView(){
        myTabLayout = findViewById(R.id.myTab);
        myViewPager = findViewById(R.id.myView);
        mBar = findViewById(R.id.table_test_loading_bar);
    }
}
