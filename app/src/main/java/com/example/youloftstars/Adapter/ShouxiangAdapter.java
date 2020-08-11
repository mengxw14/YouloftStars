package com.example.youloftstars.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.Shouxiang;

import java.util.ArrayList;

public class ShouxiangAdapter extends BaseAdapter {
    private ArrayList<Shouxiang> mList;
    private Context mContext;
    public ShouxiangAdapter(ArrayList<Shouxiang> mList,Context mContext){
        this.mList = mList;
        this.mContext = mContext;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        ViewHolder viewholder;
        if(view == null){
            viewholder = new ViewHolder();
            v = View.inflate(mContext, R.layout.lv_item,null);
            viewholder.img = v.findViewById(R.id.lv_img);
            viewholder.title = v.findViewById(R.id.lv_title);
            viewholder.text1 = v.findViewById(R.id.lv_text1);
            viewholder.text2 = v.findViewById(R.id.lv_text2);
            v.setTag(viewholder);
            view = v;
        }else {
            viewholder = (ViewHolder) view.getTag();
        }
        Shouxiang sx = mList.get(i);
        viewholder.img.setImageResource(sx.getImgId());
        viewholder.title.setImageResource(sx.getTitle());
        viewholder.text1.setText(sx.getText1());
        viewholder.text2.setText(sx.getText2());
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
        ImageView title;
        TextView text1;
        TextView text2;
    }
}
