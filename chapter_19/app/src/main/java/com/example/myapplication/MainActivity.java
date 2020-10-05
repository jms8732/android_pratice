package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;


    BroadcastReceiver brOn = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            addListItem("screen on...");
        }
    };

    BroadcastReceiver brOff = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            addListItem("screen off...");
        }
    };

    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                addListItem("Power connected...");
            }else if(action.equals(Intent.ACTION_BATTERY_LOW)){
                addListItem("Battery low...");
            }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                addListItem("Power disconntected...");
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null,intentFilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;

        if(isCharging){
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if(usbCharge){
                addListItem("Battery is USB Charging");
            }else if(acCharge)
                addListItem("Battery is AC Charging");
        }

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

        float batteryPct = (level/ (float)scale)* 100;

        addListItem("Current Battery: " + batteryPct + "%");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);

        registerReceiver(brOn,new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(brOff,new IntentFilter(Intent.ACTION_SCREEN_OFF));
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }

    private void addListItem(String item){
        list.add(item);
        adapter.notifyDataSetChanged();
    }
}