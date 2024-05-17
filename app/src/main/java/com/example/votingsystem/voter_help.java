package com.example.votingsystem;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class voter_help extends Fragment {

    public voter_help() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voter_help, container, false);

        // Find the button to view candidates
        ImageButton faqButton = view.findViewById(R.id.help_faq);
        ImageButton feedbackButton = view.findViewById(R.id.help_feedback);
        // Set click listener on the button
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), FAQ.class);
                startActivity(intent);
            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), feedback.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
