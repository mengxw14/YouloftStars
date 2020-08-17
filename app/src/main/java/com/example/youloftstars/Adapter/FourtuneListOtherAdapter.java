package com.example.youloftstars.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.AstroldMessage;
import com.example.youloftstars.bean.FourtuneListBean;
import com.example.youloftstars.bean.FourtuneOtherList;

import java.util.ArrayList;

public class FourtuneListOtherAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FourtuneOtherList> mList;
    public FourtuneListOtherAdapter(Context mContext, AstroldMessage message){
        this.mContext = mContext;
        mList = new ArrayList<>();
        for(int i = 0; i < 4; i ++){
            FourtuneOtherList bean = new FourtuneOtherList();
            switch (i){
                case 0:
                    bean.setText(message.getBusi_index());
                    bean.setTitle("谈商指数");
                    break;
                case 1:
                    bean.setText(message.getColor());
                    bean.setTitle("幸运颜色");
                    break;
                case 2:
                    bean.setText(message.getNumber());
                    bean.setTitle("幸运数字");
                    break;
                case 3:
                    bean.setText(message.getCouple());
                    bean.setTitle("速配星座");
                    break;
            }
            mList.add(bean);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            View view = View.inflate(mContext, R.layout.fourtune_grid_other,null);
            viewHolder.text = view.findViewById(R.id.fourtune_grid_other_text);
            viewHolder.title = view.findViewById(R.id.fourtune_grid_other_title);
            convertView = view;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FourtuneOtherList bean = mList.get(position);
        viewHolder.title.setText(bean.getTitle());
        viewHolder.text.setText(bean.getText());
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
        TextView text;
        TextView title;
    }

}
