package com.dp.gk.Activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.dp.gk.R;
import com.dp.gk.Utils.AppPrefs;

public class SettingsActivity extends AppCompatActivity {

    private Switch themeSwitch;
    private boolean isThemeChangeInProgress = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Get the switch view
        themeSwitch = findViewById(R.id.themeSwitch);

        if (AppPrefs.getTheme(SettingsActivity.this)) {
            // Enable Theme Switch
            themeSwitch.setChecked(true);
        } else {
            // Disable Theme Switch
            themeSwitch.setChecked(false);
        }

        // Set a listener for switch changes
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    AppPrefs.saveTheme(SettingsActivity.this, isChecked);
                    if (isChecked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
//                    recreate();
                }
        });

    }
}
