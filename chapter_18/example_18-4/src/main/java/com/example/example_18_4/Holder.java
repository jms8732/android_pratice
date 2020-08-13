package com.example.example_18_4;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public Holder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView)itemView.findViewById(R.id.image1);
        textView = (TextView)itemView.findViewById(R.id.text);
    }
}
