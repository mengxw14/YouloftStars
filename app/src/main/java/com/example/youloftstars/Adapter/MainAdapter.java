package com.example.youloftstars.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.OrderDetailData;
import com.example.youloftstars.bean.ProductInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
    private ArrayList<ProductInfo> mList;
    private Context mContext;
    private int id;
    public MainAdapter(Context mContext,ArrayList<ProductInfo> mList,int id){
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
            viewholder.img = v.findViewById(R.id.main_img);
            viewholder.title = v.findViewById(R.id.main_title);
            viewholder.detai = v.findViewById(R.id.main_detail);
            v.setTag(viewholder);
            view = v;
        }else {
            viewholder = (ViewHolder) view.getTag();
        }
        ProductInfo odd = mList.get(i);
        if(odd.getTitle().equals("AI手相")){
            viewholder.img.setImageResource(R.mipmap.shouxiang_icon);
        }else if(odd.getTitle().equals("占星")){
            viewholder.img.setImageResource(R.mipmap.taluo_icon);
        }
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
