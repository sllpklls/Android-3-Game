package com.example.btl.g2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class Square extends AppCompatTextView {
    public Square(Context context) {
        super(context);
    }

    public Square(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Square(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int length = getMeasuredWidth();
        setMeasuredDimension(length,length);
    }
    public void setSize(int value){
        if(value<100){
            setTextSize(40);
        }
        else if(value <1000){
            setTextSize(30);
        }
        if(value>=8){
            setTextColor(Color.WHITE);
        }
        else{
            setTextColor(Color.BLACK);
        }
        GradientDrawable drawable = (GradientDrawable) this.getBackground();
        drawable.setColor(DataGame.getDataGame().color(value));
        setBackground(drawable);
        if(value == 0){
            setText(" ");
        }
        else{
            setText(""+value);
        }
    }
}
