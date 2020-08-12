package com.example.mission_3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BodyHolder extends RecyclerView.ViewHolder {
    ImageView profile;
    TextView name, date;

    public BodyHolder(@NonNull View itemView) {
        super(itemView);

        profile = (ImageView)itemView.findViewById(R.id.person);
        name = (TextView)itemView.findViewById(R.id.name);
        date = (TextView)itemView.findViewById(R.id.date);

    }
}
