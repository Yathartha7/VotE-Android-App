package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class voterUpdateDetails extends AppCompatActivity {

    private EditText nameEditText, ageEditText, addressEditText, aadharEditText;
    private Spinner genderEditText;
    private Button uploadButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_update_details);
        checkIfVoterHasVoted();
        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
        startActivity(intent);
        nameEditText = findViewById(R.id.voter_editTextName);
        ageEditText = findViewById(R.id.voter_editTextAge);
        genderEditText = findViewById(R.id.voter_spinnerGender);
        addressEditText = findViewById(R.id.voter_editTextAddress);
        aadharEditText = findViewById(R.id.voter_editTextAadhar);
        uploadButton = findViewById(R.id.voter_buttonSubmit);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                String gender = genderEditText.getSelectedItem().toString();
                String address = addressEditText.getText().toString();
                String aadhar = aadharEditText.getText().toString();

                // Get current user's email
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {
                    // User is not authenticated, handle this case
                    Toast.makeText(voterUpdateDetails.this, "User is not authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = currentUser.getEmail();

                // Create Candidate object
                voter_profile voter = new voter_profile(name, age, gender, email, address, aadhar,false);

                // Upload candidate details to Firebase
                DatabaseReference votersRef = FirebaseDatabase.getInstance().getReference().child("voter");
                votersRef.child(email.replace(".", "_")).setValue(voter);

                Toast.makeText(voterUpdateDetails.this, "Details uploaded successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });
    }
    private void checkIfVoterHasVoted() {
        // Get current user's email
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();

            // Construct the path to the specific voter node
            DatabaseReference voterRef = FirebaseDatabase.getInstance().getReference().child("voter").child(email.replace(".", "_"));

            // Check if the voter has voted
            voterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Get the value of hasVoted field
                        boolean hasVoted = (boolean) dataSnapshot.child("hasVoted").getValue();
                        if (hasVoted) {
                            // Voter has already voted, show message and finish activity
                            Toast.makeText(voterUpdateDetails.this, "You have already voted.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(voterUpdateDetails.this, "Database Error", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
