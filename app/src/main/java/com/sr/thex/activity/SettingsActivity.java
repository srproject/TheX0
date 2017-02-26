package com.sr.thex.activity;

import android.preference.PreferenceActivity;

import android.os.Bundle;

import com.sr.thex.R;


public class SettingsActivity extends PreferenceActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }

}