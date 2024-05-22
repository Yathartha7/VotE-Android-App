package com.example.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class admin_home extends Fragment {

    public admin_home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        // Find the button to view candidates
        ImageButton countVotesBtn = view.findViewById(R.id.admin_countVotes);
        ImageButton addAccountBtn = view.findViewById(R.id.admin_addAccount);
        ImageButton resetSystemBtn = view.findViewById(R.id.admin_reset);
        Button btn = view.findViewById(R.id.logout);

        // Set click listener on the button
        countVotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), admin_voteCount.class);
                startActivity(intent);
            }
        });

        addAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), signUp_Activity.class);
                startActivity(intent);
            }
        });
        resetSystemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to open the CandidatesActivity
                Intent intent = new Intent(getActivity(), admin_resetSystem.class);
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
