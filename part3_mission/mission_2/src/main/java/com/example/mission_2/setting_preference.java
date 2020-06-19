package com.example.mission_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;

public class setting_preference extends PreferenceFragment {
    ListPreference network_list;
    SharedPreferences prefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        network_list = (ListPreference)findPreference("network_list");

        if(prefs.getString("network","").equals("")){
            network_list.setSummary(prefs.getString("network_list","LTE(권장)"));
        }

        prefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("network_list")){
                    network_list.setSummary(prefs.getString("network_list","LTE(권장)"));
                }
            }
        });
    }
}
