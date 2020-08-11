package com.example.example_17_3;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyDecoration extends RecyclerView.ItemDecoration {
    Context context;

    public MyDecoration(Context context){
        this.context =context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int idx = parent.getChildAdapterPosition(view)+1;

        if(idx % 3 == 0) {
            outRect.set(20, 20, 20, 60);
        }else
            outRect.set(20,20,20,20);

        view.setBackgroundColor(0xFFECE9E9);
        ViewCompat.setElevation(view,20.0F);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int width = parent.getWidth();
        int height = parent.getHeight();

        Drawable dr = ResourcesCompat.getDrawable(context.getResources(),R.drawable.android,null);

        int drWidth = dr.getIntrinsicWidth();
        int drHeight = dr.getIntrinsicHeight();

        int left = width/2 - drWidth /2;
        int top = height /2 - drHeight /2;

        c.drawBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.android),left,top,null);
    }
}
