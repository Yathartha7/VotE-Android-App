package com.example.votingsystem;

public class model_feedback {
    private String feedback;

    // Default constructor required for calls to DataSnapshot.getValue(Feedback.class)
    public model_feedback() {
    }

    public model_feedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
