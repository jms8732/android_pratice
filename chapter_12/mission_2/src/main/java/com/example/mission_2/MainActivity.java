package com.example.mission_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {
    EditText editText;
    ListView listView;
    List<String> content ;
    List<SpannableStringBuilder> ssb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_1);
        editText.addTextChangedListener(this);
        listView = (ListView) findViewById(R.id.listView);

        ssb = new ArrayList<>();
        content = new ArrayList<>();
        content.add("우편번호 검색");
        content.add("지도검색");
        content.add("대법원 나의사건 검색");
        content.add("도로명주소 검색");
        content.add("나의사진검색");


        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ssb);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ssb = new ArrayList<>();

        for(String tar : content){
            if(tar.contains(s)){
                int s_pos = tar.indexOf(s.toString());
                int e_pos = s_pos + s.length();

                SpannableStringBuilder builder = new SpannableStringBuilder(tar);
                builder.setSpan(new ForegroundColorSpan(Color.RED),s_pos,e_pos,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new StyleSpan(Typeface.BOLD),s_pos,e_pos,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                ssb.add(builder);
            }
        }

        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ssb);
        listView.setAdapter(adapter);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(content.get(position));
        ssb = new ArrayList<>();
        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ssb);
        listView.setAdapter(adapter);
    }
}

