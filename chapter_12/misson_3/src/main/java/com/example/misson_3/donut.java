package com.example.misson_3;

import android.content.Context;
import android.content.pm.PackageInstaller;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class donut extends View {
    int color, value = 0;
    int startAngle = -90,endAngle = 360;

    int width, height, textSize, stroke_size;

    public donut(Context context) {
        super(context);
    }

    public donut(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public donut(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        width = context.getResources().getDimensionPixelSize(R.dimen.donut_size);
        height = context.getResources().getDimensionPixelSize(R.dimen.donut_size);
        textSize = context.getResources().getDimensionPixelSize(R.dimen.donut_stroke_size);
        stroke_size = context.getResources().getDimensionPixelSize(R.dimen.donut_textSize);

    }

    public void setValue(int v) {
        this.value = v;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widhtMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = 0, h = 0;
        if (widhtMode == MeasureSpec.AT_MOST) {
            w = width + 30;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            h = height + 30;
        }

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke_size);

        RectF rect = new RectF(50,50,width,height);

        canvas.drawArc(rect,startAngle+value,360,false,paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rect,startAngle,value,false,paint);

        paint = new Paint();
        String txt = String.valueOf(value);
        paint.setTextSize(80);
        paint.setColor(Color.RED);
        int x_pos = width /2 - (int)(paint.measureText(txt)/2) + 20;
        int y_pos = (int)(height/2 - ((paint.descent() + paint.ascent()) / 2)) + 20;
        canvas.drawText(txt,x_pos,y_pos,paint);
    }
}
