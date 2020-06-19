package com.example.example_10_2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Holder {
    ImageView iv, iv1;
    TextView tv1,tv2;

    public Holder(View root){
        iv = (ImageView)root.findViewById(R.id.iv1);
        iv1 = (ImageView)root.findViewById(R.id.iv2);

        tv1 = (TextView)root.findViewById(R.id.tv1);
        tv2 = (TextView)root.findViewById(R.id.tv2);

    }
}
