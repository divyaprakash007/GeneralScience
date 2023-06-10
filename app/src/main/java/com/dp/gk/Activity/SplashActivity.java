package com.dp.gk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dp.gk.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // Splash screen duration in milliseconds (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
}