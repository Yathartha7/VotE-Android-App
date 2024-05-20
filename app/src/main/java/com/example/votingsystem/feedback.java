package com.example.votingsystem;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    private EditText feedbackEditText;
    private Button submitFeedbackButton;
    private DatabaseReference feedbackRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize Firebase Database reference
        feedbackRef = FirebaseDatabase.getInstance().getReference().child("feedback");

        // Initialize UI elements
        feedbackEditText = findViewById(R.id.voter_feedback);
        submitFeedbackButton = findViewById(R.id.voter_submitFeedbackBtn);

        // Set onClickListener for the submit button
        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        String feedback = feedbackEditText.getText().toString().trim();

        if (!feedback.isEmpty()) {
            // Push the feedback to the Firebase Database
            DatabaseReference newFeedbackRef = feedbackRef.push();
            newFeedbackRef.child("feedback").setValue(feedback).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(feedback.this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                    feedbackEditText.setText(""); // Clear the input field
                } else {
                    Toast.makeText(feedback.this, "Failed to submit feedback. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
        }
    }
}
