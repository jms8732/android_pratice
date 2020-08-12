package com.example.mission_3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeadHolder extends RecyclerView.ViewHolder {
    TextView header;

    public HeadHolder(@NonNull View itemView) {
        super(itemView);

        header = (TextView)itemView.findViewById(R.id.title);
    }
}
