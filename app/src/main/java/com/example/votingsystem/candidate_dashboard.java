package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class candidate_dashboard extends AppCompatActivity {
    BottomNavigationView bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_dashboard);
        getSupportFragmentManager().beginTransaction().replace(R.id.candidateFragmentContainer, new candidate_home()).commit();
        bn = findViewById(R.id.candidateNavBar);
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedFragment = null;

                if(id == R.id.candidateNavHome) {
                    selectedFragment = new candidate_home();
                }
                    else{
                    selectedFragment = new candidate_account();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.candidateFragmentContainer, selectedFragment).commit();
                return true;

            }
        });
    }
}