package com.example.youloftstars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView1 extends View {
    private int ImgId;
    private String mText;
    private String mCount;
    private Paint paint;

    {
        paint  = new Paint();
        paint.setColor(0xff775ab4);
        paint.setTextSize(56);
    }
    public MyView1(Context context) {
        super(context);
    }


    public MyView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView1);
        ImgId = a.getInteger(R.styleable.MyView1_set_img,0);
        mText = a.getString(R.styleable.MyView1_set_text);
        mCount = a.getString(R.styleable.MyView1_set_count);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(mText,10,60,paint);
        Log.d("测试用例", mText + "   " + mCount);
        float v = paint.measureText(mText);
        //
        Bitmap bitmap = null;
        switch (ImgId){
            case 1:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aiqingyun1);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aiqingyun2);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.aiqingyun3);
                break;
        }
        canvas.drawBitmap(bitmap,v + 20,20,paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(mCount,v + 400,60,paint);
    }
}
