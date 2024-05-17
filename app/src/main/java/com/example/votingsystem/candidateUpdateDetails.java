package com.example.votingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class candidateUpdateDetails extends AppCompatActivity {

    private EditText nameEditText, ageEditText, partyEditText;
    private Spinner genderEditText;
    private Button uploadButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_update_details);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        nameEditText = findViewById(R.id.candidate_editTextName);
        ageEditText = findViewById(R.id.candidate_editTextAge);
        genderEditText = findViewById(R.id.candidate_spinnerGender);
        partyEditText = findViewById(R.id.candidate_editTextParty);
        uploadButton = findViewById(R.id.candidate_buttonSubmit);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                String gender = genderEditText.getSelectedItem().toString();
                String party = partyEditText.getText().toString();

                // Get current user's email
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {
                    // User is not authenticated, handle this case
                    Toast.makeText(candidateUpdateDetails.this, "User is not authenticated", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = currentUser.getEmail();

                // Create Candidate object
                candidate_profile candidate = new candidate_profile(name, age, gender, email, 0, party);

                // Upload candidate details to Firebase
                DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference().child("candidate");
                candidatesRef.child(email.replace(".", "_")).setValue(candidate);

                Toast.makeText(candidateUpdateDetails.this, "Details uploaded successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });
    }
}
