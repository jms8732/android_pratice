package com.example.example_8_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1;
    Realm realm;
    EditText edt1,edt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        edt1 = (EditText)findViewById(R.id.title);
        edt2 = (EditText)findViewById(R.id.content);
    }

    @Override
    public void onClick(View v) {
        if(btn1 == v){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    MemoVO vo = realm.createObject(MemoVO.class);
                    vo.title = edt1.getText().toString();
                    vo.content = edt2.getText().toString();
                }
            });

            Intent intent = new Intent(this,readActivity.class);
            startActivity(intent);
        }
    }
}
