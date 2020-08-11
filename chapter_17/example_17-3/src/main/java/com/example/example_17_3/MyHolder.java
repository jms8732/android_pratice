package com.example.example_17_3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView title;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(android.R.id.text1);
    }


}
