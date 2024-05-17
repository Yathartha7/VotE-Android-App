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

public class voter_account extends Fragment {

    public voter_account() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voter_account, container, false);

        // Find the button to view candidates
        ImageButton myDetailsButton = view.findViewById(R.id.voter_myDetails);
        ImageButton updateDetailsButton = view.findViewById(R.id.voter_updateDetails);
        // Set click listener on the button
        myDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), voter_myDetails.class);
                startActivity(intent);
            }
        });
        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), voterUpdateDetails.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
