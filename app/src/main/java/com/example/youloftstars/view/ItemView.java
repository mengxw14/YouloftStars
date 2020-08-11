package com.example.youloftstars.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.youloftstars.R;

public class ItemView extends RelativeLayout {
            private ImageView mImg;
            private ImageView mTitle;
            private TextView mText1;
            private TextView mText2;

            public ItemView(Context context, AttributeSet attrs) {
                super(context);
                initView();
                initData(attrs);
            }
            private void initView(){
                View view = View.inflate(getContext(), R.layout.lv_item,this);
                mImg = findViewById(R.id.lv_img);
                mTitle = findViewById(R.id.lv_title);
                mText1 = findViewById(R.id.lv_text1);
                mText2 = findViewById(R.id.lv_text2);
            }
            private void initData(AttributeSet attrs){
                String id = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto",
                        "set_iimg");
                String text1 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto",
                        "set_text1");
                String text2 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto",
                        "set_text2");
                int iid = Integer.valueOf(id);
                switch (iid){
                    case 1:
                        mImg.setImageResource(R.mipmap.mingyunline);
                        mTitle.setImageResource(R.mipmap.kancaiyun);
                        break;
                    case 2:
                        mImg.setImageResource(R.mipmap.hunyinline);
                        mTitle.setImageResource(R.mipmap.kanhunyin);
                        break;
                    case 3:
                        mImg.setImageResource(R.mipmap.shiyeline);
                        mTitle.setImageResource(R.mipmap.kancaiyun);
                        break;
                    case 4:
                        mImg.setImageResource(R.mipmap.zhihuiline);
                        mTitle.setImageResource(R.mipmap.kanzhihu);
                        break;
                    case 5:
                        mImg.setImageResource(R.mipmap.shengmingline);
                        mTitle.setImageResource(R.mipmap.kanjiankang);
                        break;
                }

                mText1.setText(text1);
                mText2.setText(text2);
            }

        }
