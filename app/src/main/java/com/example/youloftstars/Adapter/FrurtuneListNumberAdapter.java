package com.example.youloftstars.Adapter;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.youloftstars.R;
import com.example.youloftstars.bean.AstroldMessage;
import com.example.youloftstars.bean.FourtuneListBean;

import java.util.ArrayList;

public class FrurtuneListNumberAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<FourtuneListBean> mList;
    public FrurtuneListNumberAdapter(Context mContext, AstroldMessage astroldMessage){
        this.mContext = mContext;
        mList = new ArrayList<>();
        for(int i = 0;i < 4; i ++){
            FourtuneListBean bean = new FourtuneListBean();
            bean.setId(i);
            if(i == 0){
                bean.setLevel(astroldMessage.getAll_level());
                bean.setText(astroldMessage.getAll());
            }else if(i == 1){
                bean.setLevel(astroldMessage.getLove_level());
                bean.setText(astroldMessage.getLove());
            }else if(i == 2){
                bean.setLevel(astroldMessage.getCareer_level());
                bean.setText(astroldMessage.getCareer());

            }else if( i == 3){
                bean.setLevel(astroldMessage.getFinance_level());
                bean.setText(astroldMessage.getFinance());
            }
            mList.add(bean);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            View v = View.inflate(mContext, R.layout.fourtune_list_number,null);
            viewHolder.title = v.findViewById(R.id.fourtune_list_number_title);
            viewHolder.bar = v.findViewById(R.id.fourtune_list_number_bar);
            viewHolder.detail = v.findViewById(R.id.fourtune_list_number_detail);
            convertView = v;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FourtuneListBean bean = mList.get(position);
        //viewHolder.title.setText(bean.getLevel());
        //String string = "总体运势：98分";
        String string = null;
        //ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(0xFFFDCF5D);
        ForegroundColorSpan foregroundColorSpan = null;
        ClipDrawable drawable = null;
        switch (bean.getId()){
            case 0:
                string = "总体运势：" +Integer.valueOf(bean.getLevel())*20 + "分";
                foregroundColorSpan = new ForegroundColorSpan(0xFF8EEAFF);
                drawable = new ClipDrawable(new ColorDrawable(0xFF8EEAFF), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                break;
            case 1:
                string = "爱情运势：" +Integer.valueOf(bean.getLevel())*20 + "分";
                foregroundColorSpan = new ForegroundColorSpan(0xFFFF63FF);
                drawable = new ClipDrawable(new ColorDrawable(0xFFFF63FF), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                break;
            case 2:
                string = "财富运势：" +Integer.valueOf(bean.getLevel())*20 + "分";
                foregroundColorSpan = new ForegroundColorSpan(0xFFFFF269);
                drawable = new ClipDrawable(new ColorDrawable(0xFFFFF269), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                break;
            case 3:
                string = "事业学业：" +Integer.valueOf(bean.getLevel())*20 + "分";
                foregroundColorSpan = new ForegroundColorSpan(0xFF8267FF);
                drawable = new ClipDrawable(new ColorDrawable(0xFF8267FF), Gravity.LEFT, ClipDrawable.HORIZONTAL);
                break;
            default:
                break;

        }
        SpannableString spannableString = new SpannableString(string);
        //ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(0xFFFDCF5D);
        spannableString.setSpan(foregroundColorSpan,5,string.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        viewHolder.title.setText(spannableString);
        //ClipDrawable drawable = new ClipDrawable(new ColorDrawable(color), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        //viewHolder.bar.setProgressDrawable(drawable);//必须先设置到progressbar上再设置level，告诉这个drawable的宽度有多宽，这个level才能生效
        drawable.setLevel(Integer.valueOf(bean.getLevel())*200);
        viewHolder.bar.setProgressDrawable(drawable);
        //viewHolder.bar.setProgress(progress);
        Log.d("测试", bean.getLevel());
        viewHolder.bar.setProgress(Integer.valueOf(bean.getLevel())*20);
        viewHolder.detail.setText(bean.getText());
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
        ProgressBar bar;
        TextView detail;
    }


}
