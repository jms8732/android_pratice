package com.example.mission3;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ItemVO> list;

    public MyAdapter(Context context, List<ItemVO> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemVO item = list.get(position);

        if (item.getType() == ItemVO.TYPE_BODY) {
            BodyItem body = (BodyItem) item;
            BodyHolder bodyHolder = (BodyHolder) holder;

            bodyHolder.date.setText(body.date);
            bodyHolder.name.setText(body.name);

            int count = position % 5;
            if (count == 0) {
                ((GradientDrawable)bodyHolder.profile.getBackground()).setColor(0xff009688);
            } else if (count == 1) {
                ((GradientDrawable)bodyHolder.profile.getBackground()).setColor(0xff4285f4);
            } else if (count == 2) {
                ((GradientDrawable)bodyHolder.profile.getBackground()).setColor(0xff039be5);
            } else if (count == 3) {
                ((GradientDrawable)bodyHolder.profile.getBackground()).setColor(0xff9c27b0);
            } else if (count == 4) {
                ((GradientDrawable)bodyHolder.profile.getBackground()).setColor(0xff0097a7);
            }

        } else {
            HeadItem head = (HeadItem) item;
            HeadHolder headHolder = (HeadHolder) holder;

            headHolder.head.setText(head.header);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemVO.TYPE_BODY) {
            View view = LayoutInflater.from(context).inflate(R.layout.body, parent, false);
            return new BodyHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.header, parent, false);
            return new HeadHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }
}
