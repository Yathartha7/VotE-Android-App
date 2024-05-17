package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class candidate_myDetails extends AppCompatActivity {

    private TextView nameTextView, ageTextView, genderTextView, partyTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_my_details);

        // Initialize Views
        nameTextView = findViewById(R.id.candidate_nameDetail);
        ageTextView = findViewById(R.id.candidate_ageDetail);
        genderTextView = findViewById(R.id.candidate_genderDetail);
        partyTextView = findViewById(R.id.candidate_partyDetail);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Retrieve Current User's Email
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();

            // Construct Database Reference
            DatabaseReference candidateRef = FirebaseDatabase.getInstance().getReference().child("candidate").child(email.replace(".", "_"));
            // Fetch Data
            Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
            startActivity(intent);
            candidateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Get Candidate object from DataSnapshot
                        model_candidate candidate = dataSnapshot.getValue(model_candidate.class);

                        // Display Candidate details
                        if (candidate != null) {
                            nameTextView.setText(candidate.getName());
                            ageTextView.setText(String.valueOf(candidate.getAge()));
                            genderTextView.setText(candidate.getGender());
                            partyTextView.setText(candidate.getParty());
                        }
                    } else {
                        // Candidate details not found
                        Toast.makeText(candidate_myDetails.this, "Candidate details not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(candidate_myDetails.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User not logged in
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}
