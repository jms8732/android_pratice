package com.example.example_18_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {
    private List<DataVO> list;

    public  Adapter(List<DataVO> list){
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_view,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DataVO dataVO = list.get(position);
        holder.imageView.setImageDrawable(dataVO.image);
        holder.textView.setText(dataVO.text);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
