package com.example.youloftstars.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.ProductInfo;

import java.util.ArrayList;

public class MainGridAdapter extends BaseAdapter {
    private ArrayList<ProductInfo> mList;
    private Context mContext;
    private int id;
    public MainGridAdapter(Context mContext,ArrayList<ProductInfo> mList,int id){
        this.mContext = mContext;
        this.mList = mList;
        this.id = id;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        ViewHolder viewholder;
        if(view == null){
            v = View.inflate(mContext, id,null);
            viewholder = new ViewHolder();
            viewholder.img = v.findViewById(R.id.more_test_picture);
            viewholder.title = v.findViewById(R.id.more_text_title);
            viewholder.detai = v.findViewById(R.id.grid_detail);
            v.setTag(viewholder);
            view = v;
        }else {
            viewholder = (ViewHolder) view.getTag();
        }
        ProductInfo odd = mList.get(i);
        if(odd.getTitle().equals("占星")){
            viewholder.img.setImageResource(R.mipmap.liumangxing);
        }else if(odd.getTitle().equals("塔罗")){
            viewholder.img.setImageResource(R.mipmap.taluo_icon);
        }
        Log.d("测试", odd.getTitle());
        viewholder.title.setText(odd.getTitle());
        viewholder.detai.setText(odd.getContent());
        return view;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder{
        ImageView img;
        TextView title;
        TextView detai;
    }
}
