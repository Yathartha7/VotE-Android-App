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

public class admin_viewFeedback extends AppCompatActivity {

    private RecyclerView feedbackRecyclerView;
    private FeedbackAdapter feedbackAdapter;
    private List<model_feedback> feedbackList;
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_feedback);

        // Initialize RecyclerView and FeedbackAdapter
        feedbackList = new ArrayList<>();
        feedbackAdapter = new FeedbackAdapter(feedbackList);

        // Find RecyclerView in the layout
        feedbackRecyclerView = findViewById(R.id.feedbackRecyclerView);

        // Set LayoutManager and Adapter to RecyclerView
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackRecyclerView.setAdapter(feedbackAdapter);
        Intent intent = new Intent(getApplicationContext(),loadingscreen.class);
        startActivity(intent);
        // Initialize Firebase Database reference
        feedbackRef = FirebaseDatabase.getInstance().getReference().child("feedback");

        // Fetch feedback data from Firebase and populate the RecyclerView
        feedbackRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot feedbackSnapshot : dataSnapshot.getChildren()) {
                    model_feedback feedback = feedbackSnapshot.getValue(model_feedback.class);
                    feedbackList.add(feedback);
                }
                feedbackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(admin_viewFeedback.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
