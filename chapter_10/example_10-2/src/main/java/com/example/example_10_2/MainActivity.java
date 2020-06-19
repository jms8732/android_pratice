package com.example.example_10_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        init();
    }

    private void init() {
        int pic[] = {R.drawable.ic_type_doc, R.drawable.ic_type_file, R.drawable.ic_type_doc, R.drawable.ic_type_image, R.drawable.ic_type_file, R.drawable.ic_type_doc};
        String[] name = {"안드로이드", "db.zip", "하이브리드", "이미지1", "Part4", "Angular"};
        String[] content = {"최종 수정 날짜 : 2월 6일", "최종 수정 날짜 : 1월 16일", "최종 수정 날짜 : 1월 8일"
                , "최종 수정 날짜 : 1월 1일", "최종 수정 날짜 : 12월 24일", "최종 수정 날짜 : 12월 6일"};

        List<DataVO> list = new ArrayList<>();

        for(int i =0 ; i < pic.length ; i++){
            list.add(new DataVO(pic[i],name[i],content[i]));
        }

        myAdapter myAdapter = new myAdapter(this,R.layout.item_layout,list);
        listView.setAdapter(myAdapter);
    }

    private class myAdapter extends ArrayAdapter<DataVO> {
        Context context;
        List<DataVO> list;
        int resId;

        public myAdapter(@NonNull Context context, int resource,List<DataVO> array) {
            super(context, resource,array);
            this.context = context;
            this.resId = resource;
            this.list = array;
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resId,null);

                Holder holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            Holder holder = (Holder)convertView.getTag();

            final DataVO tmp = list.get(position);

            holder.tv1.setText(tmp.name);
            holder.tv2.setText(tmp.content);
            holder.iv.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),tmp.id,null));
            holder.iv1.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_menu,null));

            holder.iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, tmp.id + " menu click", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }

}
