package com.example.votingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class voterAdapter extends RecyclerView.Adapter<voterAdapter.ViewHolder> {
    private List<model_voter> voters;

    public voterAdapter(List<model_voter> voters) {
        this.voters = voters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model_voter voter = voters.get(position);
        holder.nameTextView.setText(voter.getName());
        holder.ageTextView.setText(String.valueOf(voter.getAge()));
        holder.genderTextView.setText(voter.getGender());
        holder.emailTextView.setText(voter.getEmail());
        holder.aadharTextView.setText(String.valueOf(voter.getAadhar()));
        holder.addressTextView.setText(voter.getAddress());
        holder.hasVotedTextView.setText(String.valueOf(voter.isHasVoted()));
    }

    @Override
    public int getItemCount() {
        return voters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        TextView genderTextView;
        TextView emailTextView;
        TextView aadharTextView;
        TextView addressTextView;
        TextView hasVotedTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.voter_nameTextView);
            ageTextView = itemView.findViewById(R.id.voter_ageTextView);
            genderTextView = itemView.findViewById(R.id.voter_genderTextView);
            emailTextView = itemView.findViewById(R.id.voter_emailTextView);
            aadharTextView = itemView.findViewById(R.id.voter_aadharTextView);
            addressTextView = itemView.findViewById(R.id.voter_addressTextView);
            hasVotedTextView = itemView.findViewById(R.id.voter_votedTextView);
        }
    }
}
