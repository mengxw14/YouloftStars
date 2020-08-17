package com.example.youloftstars.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.AstroldMessage;
import com.example.youloftstars.bean.FourtuneListBean;
import com.example.youloftstars.view.FourtuneDetail;

import java.util.ArrayList;

public class FourtuneListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FourtuneListBean> mList;
    public FourtuneListAdapter(Context mContext, AstroldMessage astroldMessage){
        this.mContext = mContext;
        mList = new ArrayList<>();
        for(int i = 0;i < 4; i ++){
            FourtuneListBean bean = new FourtuneListBean();
            bean.setId(i);
            if(i == 0){
                bean.setLevel(astroldMessage.getAll_level());
            }else if(i == 1){
                bean.setLevel(astroldMessage.getLove_level());
            }else if(i == 2){
                bean.setLevel(astroldMessage.getFinance_level());
            }else if( i == 3){
                bean.setLevel(astroldMessage.getCareer_level());
            }

            mList.add(bean);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            View v = View.inflate(mContext, R.layout.fourtune_list_item,null);
            viewHolder.title = v.findViewById(R.id.fourtune_grid_title);
            viewHolder.number = v.findViewById(R.id.fourtune_grid_number);
            viewHolder.bar = v.findViewById(R.id.fourtune_grid_bar);
            convertView = v;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FourtuneListBean bean = mList.get(position);
        int level = Integer.valueOf(bean.getLevel());
        switch (bean.getId()){
            case 1:
                viewHolder.title.setText("总体运势");
                viewHolder.number.setTextColor(0xFFFFFFFF);
                break;
            case 2:
                viewHolder.title.setText("爱情运势");
                viewHolder.number.setTextColor(0xFFFF63FF);
                break;
            case 3:
                viewHolder.title.setText("金钱运势");
                viewHolder.number.setTextColor(0xFFFFC469);
                break;
            case 4:
                viewHolder.title.setText("事业运势");
                viewHolder.number.setTextColor(0xFF8267FF);
                break;
        }
        viewHolder.bar.setProgress(level * 20);
        viewHolder.number.setText(String.valueOf(level * 20));
        return convertView;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder{
        TextView title;
        TextView number;
        ProgressBar bar;
    }
}
