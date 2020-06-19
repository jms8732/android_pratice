package com.example.example_5_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn1){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle("알림");
            alert.setMessage("정말 종료하시겠습니까");
            DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast tmp = null;
                    if(which == -2){
                        tmp = Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_SHORT);
                    }else
                        tmp = Toast.makeText(getApplicationContext(),"확인",Toast.LENGTH_SHORT);

                    tmp.show();
                }
            };

            alert.setPositiveButton("OK",dialogListener);
            alert.setNegativeButton("NO",dialogListener);

            alert.create().show();
        }else if (v== btn2){
            final String [] array = {"기본 알람 소리", "Argon" , " Awaken", "Bounce", " Carborn"};
            AlertDialog.Builder list_dialog= new AlertDialog.Builder(this);
            list_dialog.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),array[which] + "를 선택하였습니다.",Toast.LENGTH_SHORT).show();
                }
            });

            list_dialog.create().show();
        }else if(v == btn3){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("Wait..");
            builder.setMessage("서버 연결중입니다. 잠시만 기다려주세요.");
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.progress_dialog,null);
            builder.setView(view);
            builder.show();

        }else if(v== btn4){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String y = String.valueOf(year);
                    String m = String.valueOf(month);
                    String d = String.valueOf(dayOfMonth);
                    Toast.makeText(getApplicationContext(), y + "-" + m + "-" + d, Toast.LENGTH_SHORT).show();
                }
            }, 2020, 06, 12);

            datePickerDialog.show();
        }else if(v == btn5){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String h = String.valueOf(hourOfDay);
                    String m = String.valueOf(minute);

                    Toast.makeText(getApplicationContext(),h + ":" + m , Toast.LENGTH_SHORT).show();
                }
            },8,30,true);

            timePickerDialog.show();
        }else{
            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view =layoutInflater.inflate(R.layout.custom_dialog,null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            builder.setPositiveButton("확인",null);
            builder.setNegativeButton("취소",null);
            builder.show();
        }
    }

}
