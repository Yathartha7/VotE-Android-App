package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.votingsystem.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class voter_dashboard extends AppCompatActivity {
    BottomNavigationView bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_dashboard);
        getSupportFragmentManager().beginTransaction().replace(R.id.voterFragmentContainer, new voter_home()).commit();
        bn = findViewById(R.id.voterNavBar);
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedFragment = null;

                if(id == R.id.voterNavHome){
                    selectedFragment = new voter_home();
                } else if (id == R.id.voterNavAccount) {
                    selectedFragment = new voter_account();
                }else{
                    selectedFragment = new voter_help();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.voterFragmentContainer, selectedFragment).commit();
                return true;

            }
        });
    }
}