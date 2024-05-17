package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splash extends AppCompatActivity {

    private static boolean splashActivityShown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if (splashActivityShown) {
            navigateToNextActivity();
            return;
        }

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            // Set the flag to indicate that the activity has been shown
            splashActivityShown = true;
            // Start your login activity
            navigateToNextActivity();
        },3000);
    }
    private void navigateToNextActivity() {
        Intent intent = new Intent(splash.this, login_Activity.class);
        startActivity(intent);
        finish();
    }
}