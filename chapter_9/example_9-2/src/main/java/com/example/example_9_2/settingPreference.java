package com.example.example_9_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;

public class settingPreference extends PreferenceFragment {
    ListPreference soundPref, listPref;
    SharedPreferences prefs;
    PreferenceScreen preferenceScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        soundPref = (ListPreference) findPreference("sound_list");
        listPref = (ListPreference) findPreference("keyword_sound_list");
        preferenceScreen = (PreferenceScreen) findPreference("keyword_screen");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!prefs.getString("sound_list", "").equals("")) {
            soundPref.setSummary(prefs.getString("sound_list", "카톡"));
        }

        if (!prefs.getString("keyword_sound_list", "").equals("")) {
            listPref.setSummary(prefs.getString("keyword_sound_list", "카톡"));
        }

        if (prefs.getBoolean("keyword", false)) {
            preferenceScreen.setSummary("사용");
        }

        prefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("sound_list")) {
                    soundPref.setSummary(prefs.getString("sound_list", "카톡"));
                }
                if (key.equals("keyword_sound_list")) {
                    preferenceScreen.setSummary(prefs.getString("keyword_sound_list", "카톡"));
                }
            }
        });

    }

}
