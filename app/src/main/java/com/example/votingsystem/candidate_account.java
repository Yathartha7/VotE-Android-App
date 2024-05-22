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

public class candidate_account extends Fragment {

    public candidate_account() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_candidate_account, container, false);

        // Find the button to view candidates
        ImageButton myDetailsButton = view.findViewById(R.id.candidate_myDetails);
        ImageButton updateDetailsButton = view.findViewById(R.id.candidate_updateDetails);
        ImageButton changePasswordButton = view.findViewById(R.id.candidate_changePassword);
        // Set click listener on the button
        myDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), candidate_myDetails.class);
                startActivity(intent);
            }
        });
        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), candidateUpdateDetails.class);
                startActivity(intent);
            }
        });
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), changePassword.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
