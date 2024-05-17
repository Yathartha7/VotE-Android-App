package com.example.votingsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class candidateAdapter extends RecyclerView.Adapter<candidateAdapter.ViewHolder> {
    private List<model_candidate> candidates;

    public candidateAdapter(List<model_candidate> candidates) {
        this.candidates = candidates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model_candidate candidate = candidates.get(position);
        holder.nameTextView.setText(candidate.getName());
        holder.ageTextView.setText(String.valueOf(candidate.getAge()));
        holder.genderTextView.setText(candidate.getGender());
        holder.partyTextView.setText(candidate.getParty());
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        TextView genderTextView;
        TextView partyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.candidate_nameTextView);
            ageTextView = itemView.findViewById(R.id.candidate_ageTextView);
            genderTextView = itemView.findViewById(R.id.candidate_genderTextView);
            partyTextView = itemView.findViewById(R.id.candidate_partyTextView);
        }
    }
}

