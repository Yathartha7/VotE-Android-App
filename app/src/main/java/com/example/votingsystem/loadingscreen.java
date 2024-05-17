package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class loadingscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate back to the previous activity
                finish();
            }
        }, 2000);
    }
}