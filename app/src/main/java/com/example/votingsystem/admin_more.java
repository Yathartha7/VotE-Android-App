package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class admin_more extends Fragment {

    public admin_more() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_more, container, false);

        // Find the button to view candidates
        ImageButton viewCandidatesButton = view.findViewById(R.id.admin_viewcandidates);
        ImageButton viewVotersButton = view.findViewById(R.id.admin_viewvoters);
        ImageButton viewFeedbackButton = view.findViewById(R.id.admin_viewfeedback);

        // Set click listener on the button
        viewCandidatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), view_candidates.class);
                startActivity(intent);
            }
        });
        viewVotersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), view_voters.class);
                startActivity(intent);
            }
        });
        viewFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), admin_viewFeedback.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
