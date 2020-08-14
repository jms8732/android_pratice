package com.example.mission1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    TextView textView;

    public Holder(@NonNull View itemView) {
        super(itemView);
        textView = (TextView)itemView.findViewById(android.R.id.text1);
    }
}
