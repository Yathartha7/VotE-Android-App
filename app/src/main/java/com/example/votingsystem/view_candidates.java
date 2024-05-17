package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

public class view_candidates extends AppCompatActivity {
    private RecyclerView candidateRecyclerView;
    private candidateAdapter candidateAdapter;
    private List<model_candidate> candidateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidates);

        // Initialize RecyclerView and CandidateAdapter
        candidateList = new ArrayList<>();
        candidateAdapter = new candidateAdapter(candidateList);

        // Find RecyclerView in the layout
        candidateRecyclerView = findViewById(R.id.candidateRecyclerView);

        // Set LayoutManager and Adapter to RecyclerView
        candidateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        candidateRecyclerView.setAdapter(candidateAdapter);
        Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
        startActivity(intent);
        // Fetch candidate data from Firebase and populate candidateList
        DatabaseReference candidatesRef = FirebaseDatabase.getInstance().getReference().child("candidate");
        candidatesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot candidateSnapshot : dataSnapshot.getChildren()) {
                    model_candidate candidate = candidateSnapshot.getValue(model_candidate.class);
                    candidateList.add(candidate);
                }
                candidateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view_candidates.this, "Database Error",
                        Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}
