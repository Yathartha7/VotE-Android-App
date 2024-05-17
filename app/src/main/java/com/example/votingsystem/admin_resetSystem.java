package com.example.votingsystem;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_resetSystem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reset_system);

        // Reset voters
        resetVoters();

        // Reset candidates
        resetCandidates();

        // Display a message indicating that the system has been reset
        Toast.makeText(this, "System reset successful", Toast.LENGTH_SHORT).show();

        // Optionally, you can finish the activity after the reset is complete
        finish();
    }

    private void resetVoters() {
        DatabaseReference votersRef = FirebaseDatabase.getInstance().getReference().child("voter");
        votersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot voterSnapshot : dataSnapshot.getChildren()) {
                    // Update the hasVoted field to false for each voter
                    voterSnapshot.getRef().child("hasVoted").setValue(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(admin_resetSystem.this, "Failed to reset voters", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void resetCandidates() {
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference().child("candidate");
        candidatesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot candidateSnapshot : dataSnapshot.getChildren()) {
                    // Update the vote count to 0 for each candidate
                    candidateSnapshot.getRef().child("votes").setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(admin_resetSystem.this, "Failed to reset candidates", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
