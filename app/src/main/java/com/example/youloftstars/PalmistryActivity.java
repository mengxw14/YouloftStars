package com.example.youloftstars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youloftstars.Adapter.MainAdapter;
import com.example.youloftstars.Adapter.MainGridAdapter;
import com.example.youloftstars.bean.GetProduct;
import com.example.youloftstars.bean.ProductInfo;
import com.example.youloftstars.util.Constant;
import com.example.youloftstars.util.HttpUtils;
import com.example.youloftstars.util.RetrofitNetWork;
import com.example.youloftstars.util.SPutils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PalmistryActivity extends Activity {
    private ImageView mBtnJiexi;
    private static final String TAG = "测试用例";
    private RetrofitNetWork mNetWork = null;
    private Gson mGson = new Gson();
    private AlertDialog dialog;
    private ListView mListView;
    private GridView mGridView1;
    private ArrayList<ProductInfo> mOneLineProduct;//占卜中一行的产品
    private ArrayList<ProductInfo> mTwoLineProduct;//占卜中两列的产品
    private static final int SEND_ALERT = 1;
    private static final int GET_MAINUI = 2;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SEND_ALERT:
                    startAlert();
                    break;
                case GET_MAINUI:
                    getMainUi();
                    break;
                default:
                    break;
            }
        }
    };
    private void getMainUi(){
        Call<ResponseBody> call = mNetWork.getProductInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    Log.d("测试",json);
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
        for(int i = 0;i < n;i ++){
            if(list.get(i).isIsMain()){
                if(list.get(i).getType() == 1){
                    mOneLineProduct.add(list.get(i));
                }else {
                    mTwoLineProduct.add(list.get(i));
                }
            }else {

            }
        }
        MainAdapter adapter = new MainAdapter(getApplicationContext(),mOneLineProduct,
                R.layout.listview_shouxiang);
        mListView.setAdapter(adapter);
        MainGridAdapter adapter1 = new MainGridAdapter(getApplicationContext(),mTwoLineProduct,
                R.layout.grid_shouxiang_item);
        mGridView1.setAdapter(adapter1);

    }
    private void startAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PalmistryActivity.this);
        View v = View.inflate(PalmistryActivity.this,R.layout.tuisong,null);
        builder.setView(v);
        dialog = builder.show();
        //v.findViewById(R.id.)
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.alpha=0.3f;
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getWindow().setBackgroundDrawableResource(new Co);
        //android:drawable/empty
        ImageView btn1 = v.findViewById(R.id.ts_cancel);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView encourage = v.findViewById(R.id.guli_btn);
        encourage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"谢谢，我们会加油做更好的！",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                SPutils.putBoolean(getApplication(),Constant.TUISONG,true);
            }
        });
        TextView tucao = v.findViewById(R.id.tucao_btn);
        tucao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"我们会通知研发人员进行改进",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                SPutils.putBoolean(getApplication(),Constant.TUISONG,true);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmistry);
        initView();
        initDate();
        initEvent();
    }
    private void initView(){
        mBtnJiexi = findViewById(R.id.btn_jiemi);
        mListView = findViewById(R.id.listview_zhanbu);
        mGridView1 = findViewById(R.id.grid_zhanbu);
    }
    private void initDate(){
        mOneLineProduct = new ArrayList<>();
        mTwoLineProduct = new ArrayList<>();
        Boolean b = SPutils.getBoolean(getApplication(), Constant.TUISONG);
        if(!b){
            //如果没有推送过 5s 后则推送求好评
            Log.d(TAG, "定时器准备就绪");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "定时器开始工作");
                    Message message = Message.obtain();
                    message.what = SEND_ALERT;
                    handler.sendMessage(message);
                }
            },1000);
        }
        mNetWork = HttpUtils.getFortuneInfo();
        Message message = Message.obtain();
        message.what = GET_MAINUI;
        handler.sendMessage(message);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                PalmistryActivity.this, Manifest.permission.CAMERA)){
            ActivityCompat.requestPermissions(PalmistryActivity.this,
                    new String[]{Manifest.permission.CAMERA},0);
        }
    }
    private void initEvent(){
        mBtnJiexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 拍照测试手相
                 */
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    if(!isPermission()){
//                        //没有获取全部权限 请求权限
//                        requestPermission();
//                    }else {
//                        takePhoto();
//                    }
                }

                Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                if(takePictureIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(takePictureIntent,0);
                }
            }
        });
    }
    private boolean isPermission(){
        boolean haveCameraPermission = checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;;
        boolean haveWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        return haveCameraPermission && haveWritePermission;
    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 0 && resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        Log.d(TAG, "onActivityResult: 成功得到imageBitmap图片");
        Intent intent = new Intent(this,palmistryReslut.class);
        intent.putExtra("Shouxiang",imageBitmap);
        startActivity(intent);
    }
}

}