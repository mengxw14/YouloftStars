package com.example.youloftstars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.youloftstars.util.Constant;
import com.example.youloftstars.util.SPutils;

import java.util.Timer;
import java.util.TimerTask;

public class PalmistryActivity extends Activity {
    private ImageView mBtnJiexi;
    private static final String TAG = "测试用例";
    private AlertDialog dialog;
    private static final int SEND_ALERT = 1;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SEND_ALERT:
                    startAlert();
                    break;
                default:
                    break;
            }
        }
    };
    private void startAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PalmistryActivity.this);
        View v = View.inflate(PalmistryActivity.this,R.layout.tuisong,null);
        builder.setView(v);
        dialog = builder.show();
        //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ImageView btn1 = v.findViewById(R.id.ts_cancel);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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
    }
    private void initDate(){
        Boolean b = SPutils.getBoolean(getApplication(), Constant.TUISONG);
//        if(!b){
//            //如果没有推送过 5s 后则推送求好评
//            Log.d(TAG, "定时器准备就绪");
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Log.d(TAG, "定时器开始工作");
//                    Message message = Message.obtain();
//                    message.what = SEND_ALERT;
//                    handler.sendMessage(message);
//                }
//            },5000);
//        }
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
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        PalmistryActivity.this, Manifest.permission.CAMERA)){
                    ActivityCompat.requestPermissions(PalmistryActivity.this,
                            new String[]{Manifest.permission.CAMERA},0);
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