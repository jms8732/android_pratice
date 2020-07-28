package com.example.mission_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.List;

public class MyAdapter extends ArrayAdapter<PhoneVO>  {
    private Context context;
    private int resId;
    private List<PhoneVO> list;

    public MyAdapter(@NonNull Context context, int resource, List<PhoneVO> list) {
        super(context, resource,list);
        this.context = context;
        this.resId = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhoneHolder holder = null;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resId,null);
            holder = new PhoneHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (PhoneHolder)convertView.getTag();
        final PhoneVO p = list.get(position);

        holder.person.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),p.person,null));
        holder.name.setText(p.name);
        holder.content.setText(p.content);
        holder.phone.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_dialer,null));

        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+p.person));
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class PhoneHolder{
        ImageView person, phone;
        TextView name,content;

        public PhoneHolder(View root){
            person = (ImageView)root.findViewById(R.id.person);
            phone = (ImageView)root.findViewById(R.id.phone);
            name = (TextView)root.findViewById(R.id.name);
            content = (TextView)root.findViewById(R.id.content);
        }
    }
}
