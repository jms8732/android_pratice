package com.example.example_18_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    CoordinatorLayout coordinatorLayout;
    BottomSheetBehavior<View> persistentBottom;
    List<DataVO> list;
    RecyclerView recyclerView;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn1);
        list = new ArrayList<>();
        btn.setOnClickListener(this);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator);

        View bottomSheet = coordinatorLayout.findViewById(R.id.bottomsheet);
        persistentBottom = BottomSheetBehavior.from(bottomSheet);

        init();

    }

    private void init(){
        DataVO dataVO = new DataVO();
        dataVO.text = "Keep";
        dataVO.image = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_1,null);

        list.add(dataVO);

        dataVO = new DataVO();
        dataVO.image = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_2,null);
        dataVO.text ="Inbox";

        list.add(dataVO);

        dataVO = new DataVO();
        dataVO.image = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_3,null);
        dataVO.text = "Messanger";

        list.add(dataVO);


        dataVO = new DataVO();
        dataVO.image = ResourcesCompat.getDrawable(getResources(),R.drawable.ic_lab4_4,null);
        dataVO.text ="Google+";

        list.add(dataVO);
    }

    @Override
    public void onClick(View v) {
        View view= getLayoutInflater().inflate(R.layout.model,null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(list));

        dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }
}
