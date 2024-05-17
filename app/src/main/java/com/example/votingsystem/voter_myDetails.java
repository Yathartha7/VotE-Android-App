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

public class voter_myDetails extends AppCompatActivity {

    private TextView nameTextView, ageTextView, genderTextView, addressTextView, aadharTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_my_details);

        // Initialize Views
        nameTextView = findViewById(R.id.voter_nameDetail);
        ageTextView = findViewById(R.id.voter_ageDetail);
        genderTextView = findViewById(R.id.voter_genderDetail);
        addressTextView = findViewById(R.id.voter_addressDetail);
        aadharTextView = findViewById(R.id.voter_aadharDetail);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Retrieve Current User's Email
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();

            // Construct Database Reference
            DatabaseReference voterRef = FirebaseDatabase.getInstance().getReference().child("voter").child(email.replace(".", "_"));
            // Fetch Data
            Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
            startActivity(intent);
            voterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Get Candidate object from DataSnapshot
                        model_voter voter = dataSnapshot.getValue(model_voter.class);

                        // Display Candidate details
                        if (voter != null) {
                            nameTextView.setText(voter.getName());
                            ageTextView.setText(String.valueOf(voter.getAge()));
                            genderTextView.setText(voter.getGender());
                            addressTextView.setText(voter.getAddress());
                            aadharTextView.setText(voter.getAadhar());
                        }
                    } else {
                        // Candidate details not found
                        Toast.makeText(voter_myDetails.this, "Voter details not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(voter_myDetails.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // User not logged in
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}
