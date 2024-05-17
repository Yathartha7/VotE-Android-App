package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_dashboard extends AppCompatActivity {
    BottomNavigationView bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportFragmentManager().beginTransaction().replace(R.id.adminFragmentContainer, new admin_home()).commit();
        bn = findViewById(R.id.adminNavBar);
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedFragment = null;

                if(id == R.id.adminNavHome) {
                    selectedFragment = new admin_home();
                }
                else{
                    selectedFragment = new admin_more();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.adminFragmentContainer, selectedFragment).commit();
                return true;

            }
        });
    }
}