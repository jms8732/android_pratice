package com.example.example_18_3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView text;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        text = (TextView)itemView.findViewById(android.R.id.text1);
    }
}
