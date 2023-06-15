package com.dp.gk.Activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dp.gk.R;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

public class HomeActivity extends AppCompatActivity {
    private Button btnOneLiners;
    private Button btnPracticeSets;
    private Button btnImportantQuiz;
    private Button btnImportantMonthlyQuiz;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerForContextMenu(toolbar);

        // Initialize buttons
        btnOneLiners = findViewById(R.id.btn_oneLiners);
        btnPracticeSets = findViewById(R.id.btn_practice_sets);
        btnImportantQuiz = findViewById(R.id.btn_important_quiz);
        btnImportantMonthlyQuiz = findViewById(R.id.btn_important_monthly_quiz);

        // Set click listeners for buttons
        btnOneLiners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "One Liners clicked", Toast.LENGTH_SHORT).show();
                // Add your logic for One Liners button click here
            }
        });

        btnPracticeSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Latest Practice Sets clicked", Toast.LENGTH_SHORT).show();
                // Add your logic for Latest Practice Sets button click here
            }
        });

        btnImportantQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Latest Important Quizs clicked", Toast.LENGTH_SHORT).show();
                // Add your logic for Latest Important Quizs button click here
            }
        });

        btnImportantMonthlyQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Monthly Important Quizs clicked", Toast.LENGTH_SHORT).show();
                // Add your logic for Monthly Important Quizs button click here
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_settings) {
//            Intent intent = new Intent(this, SettingsActivity.class);
//            startActivity(intent);
//            return true;
//        } else
        if (item.getItemId() == R.id.action_rateUs) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.dp.gk&hl=en-IN")));
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android&hl=en-IN")));
            return true;
        } else if (item.getItemId() == R.id.action_ShareApp) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "General Science Quiz");
                String shareMessage= "\nLet me recommend you this application\n\n";
//                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.instagram.android&hl=en-IN" +"\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.dp.gk&hl=en-IN" +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (item.getItemId() == R.id.action_privacy_policy) {
            // TODO: 6/15/2023 open privacy policy in WebView 
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.EXTRA_URL, WebViewActivity.PRIVACY_POLICY_URL);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_terms) {
            // TODO: 6/15/2023 Open Terms and conditions in WebView
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.EXTRA_URL, WebViewActivity.TERMS_CONDITIONS_URL);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Do nothing to disable the back button
    }

    @Override
    protected void onStart() {
        super.onStart();
        UpdateApp();
    }

    public void UpdateApp(){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(result -> {

            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
//                requestUpdate(result);
                android.view.ContextThemeWrapper ctw = new android.view.ContextThemeWrapper(this,R.style.Theme_AlertDialog);
                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ctw);
                alertDialogBuilder.setTitle("Update NTT Netmagic");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setIcon(R.mipmap.app_logo);
                alertDialogBuilder.setMessage("Fitness Trainer recommends that you update to the latest version for a seamless & enhanced performance of the app.");
                alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try{
                            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+getPackageName())));
                        }
                        catch (ActivityNotFoundException e){
                            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("No Thanks",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                alertDialogBuilder.show();

            } else {

            }
        });
    }

}
