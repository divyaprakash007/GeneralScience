package com.dp.gk.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dp.gk.R;
import com.dp.gk.Utils.AppPrefs;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // Splash screen duration in milliseconds (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AppPrefs.getTheme(SplashActivity.this)) {
            // Enable dark theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Enable default theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Use a Handler to post a delayed Runnable that starts the HomeActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the HomeActivity
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);

                // Finish the SplashActivity to prevent going back to it
                finish();
            }
        }, SPLASH_DURATION);
    }

    @Override
    public void onBackPressed() {
        // Do nothing to disable the back button
    }
}