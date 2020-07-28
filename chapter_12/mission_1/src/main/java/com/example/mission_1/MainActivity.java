package com.example.mission_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv1);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            insert_data();
            start();
        }else{
            ActivityCompat.requestPermissions(this,new String [] {Manifest.permission.CALL_PHONE},200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 200 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                insert_data();
                start();
            }
        }
    }


    private void start(){
        DBhelper helper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from db_table",null);
        List<PhoneVO> list = new ArrayList<>();

        while(cursor.moveToNext()){
            int person = Integer.parseInt(cursor.getString(1));
            String name = cursor.getString(2);
            String content = cursor.getString(3);

            list.add(new PhoneVO(person,name,content));
        }

        MyAdapter adapter = new MyAdapter(this,R.layout.phone_layout,list);
        listView.setAdapter(adapter);
    }


    private void insert_data(){
        int [] person = { R.drawable.hong,R.drawable.ic_person,R.drawable.ic_person,R.drawable.hong,
                R.drawable.ic_person,R.drawable.ic_person,R.drawable.ic_person};
        String[] name = {"홍길동","류현진","강정호","김현수","오승환","이대호","박병호"};
        String [] contents = {"휴대전화, 1일전","휴대전화, 1일전","휴대전화, 2일전","휴대전화, 2일전","휴대전화, 2일전","휴대전화, 2일전"
        ,"휴대전화, 2일전"};

        DBhelper helper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        for(int i =0 ; i < name.length ; i++){
            String sql = "insert into db_table (person, name, content) values (?,?,?)";
            db.execSQL(sql,new Object[]{person[i],name[i],contents[i]});
        }

        Toast.makeText(this, "insert complete", Toast.LENGTH_SHORT).show();
    }

}
