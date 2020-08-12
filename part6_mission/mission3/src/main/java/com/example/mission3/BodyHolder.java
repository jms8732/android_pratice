package com.example.mission3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BodyHolder extends RecyclerView.ViewHolder {
    TextView date , name;
    ImageView profile;
    public BodyHolder(@NonNull View itemView) {
        super(itemView);

        profile = (ImageView)itemView.findViewById(R.id.profile);
        date = (TextView)itemView.findViewById(R.id.date);
        name = (TextView)itemView.findViewById(R.id.name);
    }
}
