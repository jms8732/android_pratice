package com.example.example_12_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv1);

        registerForContextMenu(iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder)menu;
            m.setOptionalIconsVisible(true);
        }

        MenuItem item = menu.findItem(R.id.menu_search);

        search_view = (SearchView)item.getActionView();
        search_view.setQueryHint(getResources().getString(R.string.query));
        search_view.setOnQueryTextListener(new queryTextListener());
        return true;
    }

    private class queryTextListener implements SearchView.OnQueryTextListener{
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "서버전송");
        menu.add(0, 1, 0, "보관함에 보관");
        menu.add(0, 2, 0, "삭제");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 0){
            Toast.makeText(this, "서버 전송!", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId()== 1)
            Toast.makeText(this, "보관 완료!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "삭제 완료!", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
