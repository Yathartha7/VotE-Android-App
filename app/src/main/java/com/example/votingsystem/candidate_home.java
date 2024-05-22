package com.example.votingsystem;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class candidate_home extends Fragment {

    public candidate_home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_candidate_home, container, false);

        // Find the button to view candidates
        ImageButton viewCandidatesButton = view.findViewById(R.id.candidate_viewcandidates);
        ImageButton withdrawButton = view.findViewById(R.id.candidate_withdraw);
        Button btn = view.findViewById(R.id.logout);
        // Set click listener on the button
        viewCandidatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), view_candidates.class);
                startActivity(intent);
            }
        });
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), withdraw.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),login_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        return view;
    }
}
