package com.example.mission1;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Decoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int idx = parent.getChildAdapterPosition(view)+1;

        outRect.set(20,20,20,20);
        view.setBackgroundColor(0xFFECE9E9);
        ViewCompat.setElevation(view,20.0F);

    }
}
