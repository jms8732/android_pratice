package com.example.mission_3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    List<ItemVO> list;
    Context context;

    public MyAdapter(DBHelper helper, Context context){
        this.context =context;
        SQLiteDatabase db = helper.getReadableDatabase();
        list = new ArrayList<>();

        list.add(new HeaderItem("오늘"));
        Cursor current = db.rawQuery("select * from db_table where date='2017-07-01'",null);

        while(current.moveToNext()){
            PersonItem personItem = new PersonItem(current.getString(1),current.getString(2));
            list.add(personItem);
        }

        list.add(new HeaderItem("어제"));
        Cursor yesterday = db.rawQuery("select * from db_table where date='2017-06-30'",null);
        while(yesterday.moveToNext()){
            PersonItem personItem = new PersonItem(yesterday.getString(1),yesterday.getString(2));
            list.add(personItem);
        }

        list.add(new HeaderItem("이전"));

        Cursor previous = db.rawQuery("select * from db_table where date != '2017-06-30' and date != '2017-07-01'",null);

        while(previous.moveToNext()){
            PersonItem personItem = new PersonItem(previous.getString(1),previous.getString(2));
            list.add(personItem);
        }

        helper.close();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemVO itemVO = list.get(position);

        if(itemVO.getType() == ItemVO.TYPE_BODY){
            BodyHolder body = (BodyHolder)holder;
            PersonItem item = (PersonItem)itemVO;

            body.date.setText(item.date);
            body.name.setText(item.name);

        }else{
            HeadHolder head = (HeadHolder)holder;
            HeaderItem item = (HeaderItem)itemVO;

            head.header.setText(item.date);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ItemVO.TYPE_BODY){
            View view = LayoutInflater.from(context).inflate(R.layout.person,parent,false);
            return new BodyHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.header,parent,false);
            return new HeadHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }
}
