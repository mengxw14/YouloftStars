package com.example.youloftstars.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ItemViewGroup extends ConstraintLayout {

    public ItemViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int count = getChildCount();
        int curWidth = i;
        for(int j = 0; j < count; j ++){
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            child.layout(curWidth,i1,curWidth + width,i1 + height);
            curWidth += width;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        if (count == 0){
            setMeasuredDimension(0,0);
        }else {
            int width = getTotalWidth();
            int height = getMaxHeight();
            setMeasuredDimension(width,height);
        }
    }
    private int getTotalWidth(){
        int count = getChildCount();
        int width = 0;
        for(int i = 0;i < count; i ++){
            width += getChildAt(i).getMeasuredWidth();
        }
        return width;
    }
    public int getMaxHeight(){
        int count = getChildCount();
        int height = 0;
        for(int i = 0;i < count; i ++){
            View childView = getChildAt(i);
            if(childView.getMeasuredHeight() > height){
                height = childView.getMeasuredHeight();
            }
        }
        return height;
    }
}
