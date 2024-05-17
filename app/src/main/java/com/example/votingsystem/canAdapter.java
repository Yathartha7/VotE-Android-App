package com.example.votingsystem;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class canAdapter extends RecyclerView.Adapter<canAdapter.ViewHolder>{
    private List<candidateAll> candidates;

    public canAdapter(List<candidateAll> candidates) {
        this.candidates = candidates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_vc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        candidateAll candidate = candidates.get(position);
        holder.nameTextView.setText(candidate.getName());
        holder.partyTextView.setText(candidate.getParty());
        holder.emailTextView.setText(candidate.getEmail());
        holder.votesTextView.setText(String.valueOf(candidate.getVotes()));
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView partyTextView;
        TextView emailTextView;
        TextView votesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.candidate_nameTextViewvc);
            partyTextView = itemView.findViewById(R.id.candidate_partyTextViewvc);
            emailTextView = itemView.findViewById(R.id.candidate_emailTextViewvc);
            votesTextView = itemView.findViewById(R.id.candidate_votesTextViewvc);
        }
    }
}

