package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class withdraw extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference candidateRef;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // User is not authenticated, handle this case
            Toast.makeText(withdraw.this, "User is not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = currentUser.getEmail();

            // Get the Firebase Realtime Database instance
        database = FirebaseDatabase.getInstance();

            // Get the reference to the "candidates" node
        candidateRef = database.getReference("candidate");
        DatabaseReference candidateNode = candidateRef.child(email.replace(".", "_"));

        // Remove the candidate node from the database
        candidateNode.removeValue();
        Intent intent = new Intent(withdraw.this,login_Activity.class);
        Toast.makeText(withdraw.this, "Withdrawn Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
        }
}