package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class view_voters extends AppCompatActivity {
    private RecyclerView voterRecyclerView;
    private voterAdapter voterAdapter;
    private List<model_voter> voterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voters);

        // Initialize RecyclerView and CandidateAdapter
        voterList = new ArrayList<>();
        voterAdapter = new voterAdapter(voterList);

        // Find RecyclerView in the layout
        voterRecyclerView = findViewById(R.id.voterRecyclerView);

        // Set LayoutManager and Adapter to RecyclerView
        voterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        voterRecyclerView.setAdapter(voterAdapter);
        Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
        startActivity(intent);
        // Fetch candidate data from Firebase and populate candidateList

        DatabaseReference voterRef = FirebaseDatabase.getInstance().getReference().child("voter");
        voterRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot voterSnapshot : dataSnapshot.getChildren()) {
                    model_voter voter = voterSnapshot.getValue(model_voter.class);
                    voterList.add(voter);
                }
                voterAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(view_voters.this, "Database Error",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
