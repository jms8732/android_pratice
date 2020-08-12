package com.example.mission3;

import android.content.ClipData;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyDecoration extends RecyclerView.ItemDecoration {
    private List<ItemVO> list;


    public MyDecoration(List<ItemVO> list) {
        this.list = list;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int idx = parent.getChildAdapterPosition(view);

        if (list.get(idx).getType() == ItemVO.TYPE_BODY) {
            if (idx == 3 || idx == 6)
                outRect.set(20, 20, 20, 60);
            else
                outRect.set(20, 10, 20, 10);
            view.setBackgroundColor(0xFFFFFFFF);
            ViewCompat.setElevation(view, 20.0F);
        }
    }
}
