package com.example.example_13_1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Rectangle;

import java.util.ArrayList;

public class PlusMinusButton extends View {
    ArrayList<onMyListener> listeners;
    Bitmap plus, minus;
    Rect plusRectF, minusRectF;
    int textColor, value;

    public PlusMinusButton(Context context) {
        super(context);
    }

    public PlusMinusButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlusMinusButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        plus = BitmapFactory.decodeResource(context.getResources(), R.drawable.plus);
        minus = BitmapFactory.decodeResource(context.getResources(), R.drawable.minus);

        plusRectF = new Rect(10, 10, 210, 210);
        minusRectF = new Rect(400, 10, 600, 210);

        listeners = new ArrayList<>();

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlusMinusButton);
            textColor = a.getColor(R.styleable.PlusMinusButton_customColor, Color.RED);
        }
    }

    public void setListeners(onMyListener listener) {
        listeners.add(listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (plusRectF.contains(x, y) && event.getAction() == MotionEvent.ACTION_DOWN) {
            value++;

            for(onMyListener listener: listeners){
                listener.onChange(value);
            }
            invalidate();

        }

        if(minusRectF.contains(x,y) && event.getAction() == MotionEvent.ACTION_DOWN){
            value--;

            for(onMyListener listener : listeners){
                listener.onChange(value);
            }
            invalidate();
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getMode(widthMeasureSpec);
        int h = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0, height =0 ;

        if(w == MeasureSpec.AT_MOST){
            width = 800;
        }

        if(h == MeasureSpec.AT_MOST)
            height = 400;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN));

        Rect plusRect = new Rect(0, 0, plus.getWidth(), plus.getHeight());
        Rect minusRect = new Rect(0, 0, minus.getWidth(), minus.getHeight());

        Paint paint = new Paint();

        canvas.drawBitmap(plus, plusRect, plusRectF, null);

        paint.setTextSize(80);
        paint.setColor(textColor);
        canvas.drawText(String.valueOf(value), 260, 150, paint);

        canvas.drawBitmap(minus, minusRect, minusRectF, null);
    }
}
