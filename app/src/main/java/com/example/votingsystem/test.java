package com.example.votingsystem;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get current user's email
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();

            // Query the database using the user's email
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

            // Check if the user is an admin
            DatabaseReference adminRef = mDatabase.child("admin").child("admin");
            adminRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && dataSnapshot.child("email").getValue(String.class).equals(userEmail)) {
                        // User is an admin
                        String name = dataSnapshot.child("email").getValue(String.class);
                        TextView t = findViewById(R.id.dispName);
                        t.setText(name);
                    } else {
                        // User is not an admin, check if user is a candidate
                        DatabaseReference candidateRef = mDatabase.child("candidate").child(getKeyFromEmail(userEmail));
                        candidateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // User is a candidate
                                    String name = dataSnapshot.child("party").getValue(String.class);
                                    TextView t = findViewById(R.id.dispName);
                                    t.setText(name);
                                } else {
                                    // User is not a candidate, assume user is a voter
                                    DatabaseReference voterRef = mDatabase.child("voter").child(getKeyFromEmail(userEmail));
                                    voterRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                // User is a voter
                                                String name = dataSnapshot.child("aadharNumber").getValue(String.class);
                                                // Process voter details
                                                TextView t = findViewById(R.id.dispName);
                                                t.setText(name);
                                            } else {
                                                // User details not found
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            // Handle database error
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Handle database error
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }
    }

    private String getKeyFromEmail(String email) {
        return email.split("@")[0];
    }
}
