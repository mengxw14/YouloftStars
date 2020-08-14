package com.example.youloftstars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youloftstars.Adapter.MainAdapter;
import com.example.youloftstars.Adapter.MainGridAdapter;
import com.example.youloftstars.bean.GetOrder;
import com.example.youloftstars.bean.GetProduct;
import com.example.youloftstars.bean.OrderDetailData;
import com.example.youloftstars.bean.ProductInfo;
import com.example.youloftstars.util.HttpUtils;
import com.example.youloftstars.util.RetrofitNetWork;
import com.example.youloftstars.util.ScreenAdjust;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RetrofitNetWork mNetWork = null;
    private ListView mListView;
    private GridView mGridView1;
    private GridView mGridView2;
    private ImageView mCombinedShape;
    private TextView mTodayTotal;
    private ImageView mStar1;
    private ImageView mStar2;
    private ImageView mStar3;
    private ImageView mStar4;
    private ImageView mStar5;
    private Gson mGson;
    private ArrayList<ProductInfo> mOneLineProduct;//占卜中一行的产品
    private ArrayList<ProductInfo> mTwoLineProduct;//占卜中两列的产品
    private ArrayList<ProductInfo> mOtherProduct;//占卜中其他产品
    private static final String Astrold = "scorpio";
    private static final int GET_MAINUI = 1;
    private static final int GET_ARTICLE = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_MAINUI:
                    getMainUi();
                    break;
                case GET_ARTICLE:
                    getArticele();
                    break;
            }
        }
    };

    private void getArticele() {
        Call<ResponseBody> call = mNetWork.getAstroTb("scorpio");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    Intent intent = new Intent(MainActivity.this,fourtuneDetailShow.class);
                    intent.putExtra("json",json);
                    startActivity(intent);
                    //请求到的json数据传入Intent进入另一个activity---请求不了
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("测试", t.getMessage());
                Toast.makeText(getApplicationContext(),"网络请求失败,请稍后再试",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getMainUi(){
        Call<ResponseBody> call = mNetWork.getProductInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    //Log.d("测试",json);
                    ArrayList<ProductInfo> list = mGson.fromJson(json, GetProduct.class).getData();
                    //需要把list分裂分为两类--一种是一行一列 一种是两列一行
                    setList(list);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void setList(ArrayList<ProductInfo> list){
        int n = list.size();
        mOneLineProduct = new ArrayList<>();
        mTwoLineProduct = new ArrayList<>();
        mOtherProduct = new ArrayList<>();
        for(int i = 0;i < n;i ++){
            if(list.get(i).isIsMain()){
                if(list.get(i).getType() == 1){
                    String a = list.get(i).getContent();
                    list.get(i).setContent(a + "看一看能不能换行");
                    mOneLineProduct.add(list.get(i));
                    Log.d("测试用例", list.get(i).toString());
                }else {
                    mTwoLineProduct.add(list.get(i));
                }
            }else {
                mOtherProduct.add(list.get(i));
            }
        }
        MainAdapter adapter = new MainAdapter(getApplicationContext(),mOneLineProduct,
                R.layout.main_lv_item);
        mListView.setAdapter(adapter);
        MainGridAdapter adapter1 = new MainGridAdapter(getApplicationContext(),mTwoLineProduct,
                R.layout.grid_item);
        mGridView1.setAdapter(adapter1);
        MainGridAdapter adapter2 = new MainGridAdapter(getApplicationContext(),mOtherProduct,
                R.layout.grid_item);
        mGridView2.setAdapter(adapter2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ScreenAdjust.setup(getApplication());
        //ScreenAdjust.match(getApplicationContext(),360f);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }
    private void initView(){
        mListView = findViewById(R.id.listview_zhanbu);
        mGridView1 = findViewById(R.id.grid_zhanbu);
        mGridView2 = findViewById(R.id.grid_more_test);
        mCombinedShape = findViewById(R.id.combined_shape);
        mTodayTotal = findViewById(R.id.today_text);
        mStar1 = findViewById(R.id.star_1);
        mStar2 = findViewById(R.id.star_2);
        mStar3 = findViewById(R.id.star_3);
        mStar4 = findViewById(R.id.star_4);
        mStar5 = findViewById(R.id.star_5);
    }
    private void initData(){
        mGson = new Gson();
        mNetWork = HttpUtils.getFortuneInfo();
        Message message = Message.obtain();
        message.what = GET_MAINUI;
        handler.sendMessage(message);
        setTodayTotal();
    }
    private void setTodayTotal(){
        String string = "今运日势总运：98分";
        SpannableString spannableString = new SpannableString(string);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(0xFFFDCF5D);
        spannableString.setSpan(foregroundColorSpan,7,string.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        mTodayTotal.setText(spannableString);
        //设置星星 假设是4
        int count = 4;
        switch (count){
            case 5:
                mStar5.setImageResource(R.mipmap.yellowstar);
            case 4:
                mStar4.setImageResource(R.mipmap.yellowstar);
            case 3:
                mStar3.setImageResource(R.mipmap.yellowstar);
            case 2:
                mStar2.setImageResource(R.mipmap.yellowstar);
            case 1:
                mStar1.setImageResource(R.mipmap.yellowstar);
                break;
            default:
                break;
        }
    }
    private void initEvent(){
        mCombinedShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = Message.obtain();
                message.what = GET_ARTICLE;
                handler.sendMessage(message);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //通过后台的参数获取点击的按钮 因为只有一个 就假装已经处理过了
                Intent intent = new Intent(MainActivity.this,PalmistryActivity.class);
                startActivity(intent);
            }
        });
    }
}