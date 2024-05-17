package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class voter_voteNow extends AppCompatActivity implements candidatevote_adapter.OnVoteClickListener {

    private RecyclerView voteNowRecyclerView;
    private candidatevote_adapter voteAdapter;
    private List<model_candidate_voteNow> candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_vote_now);

        checkIfVoterHasVoted();

        // Initialize RecyclerView and Adapter
        candidates = new ArrayList<>();
        voteAdapter = new candidatevote_adapter(candidates);
        voteAdapter.setOnVoteClickListener(this::onVoteClick);

        // Find RecyclerView in the layout
        voteNowRecyclerView = findViewById(R.id.voteNowRecyclerView);

        // Set LayoutManager and Adapter to RecyclerView
        voteNowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        voteNowRecyclerView.setAdapter(voteAdapter);

        // Fetch candidate data from Firebase and populate the list
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference().child("candidate");
        Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
        startActivity(intent);
        candidatesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot candidateSnapshot : dataSnapshot.getChildren()) {
                    model_candidate_voteNow candidate = candidateSnapshot.getValue(model_candidate_voteNow.class);
                    candidates.add(candidate);
                }
                voteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(voter_voteNow.this, "Database Error", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onVoteClick(String candidateEmail, int position) {
        // Get a reference to the Firebase database
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference().child("candidate");

        // Replace "." with "_" in the candidate's email
        String candidateNode = candidateEmail.replace(".", "_");

        // Construct the path to the specific candidate node
        DatabaseReference candidateNodeRef = candidatesRef.child(candidateNode);

        // Read the current vote count from the database and update it
        candidateNodeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the current vote count
                    long currentVotes = (long) dataSnapshot.child("votes").getValue();

                    // Increment the vote count by 1
                    long newVotes = currentVotes + 1;

                    // Update the vote count in the database
                    dataSnapshot.child("votes").getRef().setValue(newVotes);
                    changeVoterStatus();
                    // Notify the user that their vote has been successfully cast

                } else {
                    // Handle the case where the candidate node does not exist
                    Toast.makeText(getApplicationContext(), "Candidate not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during the database operation
                Toast.makeText(getApplicationContext(), "Failed to vote. Please try again.", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(voter_voteNow.this, "You have already voted.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(voter_voteNow.this, "Database Error", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
    private void changeVoterStatus() {
        // Get current user's email
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();

            // Construct the path to the specific voter node
            DatabaseReference voterRef = FirebaseDatabase.getInstance().getReference().child("voter").child(email.replace(".", "_"));

            // Update hasVoted field to true
            voterRef.child("hasVoted").setValue(true);
            Toast.makeText(getApplicationContext(), "Your vote has been successfully cast.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


}
