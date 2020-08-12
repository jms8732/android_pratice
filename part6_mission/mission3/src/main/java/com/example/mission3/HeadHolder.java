package com.example.mission3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeadHolder extends RecyclerView.ViewHolder {
    TextView head;

    public HeadHolder(@NonNull View itemView) {
        super(itemView);

        head = (TextView)itemView.findViewById(R.id.header);
    }
}
