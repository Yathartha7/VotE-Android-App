package com.example.votingsystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class candidatevote_adapter extends RecyclerView.Adapter<candidatevote_adapter.ViewHolder> {

    private List<model_candidate_voteNow> candidates;
    private OnVoteClickListener voteClickListener;

    public candidatevote_adapter(List<model_candidate_voteNow> candidates) {
        this.candidates = candidates;
    }

    public void setOnVoteClickListener(OnVoteClickListener listener) {
        this.voteClickListener = listener;
    }

    public interface OnVoteClickListener {
        void onVoteClick(String candidateEmail, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item_votenow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model_candidate_voteNow candidate = candidates.get(holder.getAdapterPosition());
        holder.nameTextView.setText(candidate.getName());
        holder.ageTextView.setText(String.valueOf(candidate.getAge()));
        holder.genderTextView.setText(candidate.getGender());
        holder.partyTextView.setText(candidate.getParty());

        holder.voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voteClickListener != null) {
                    voteClickListener.onVoteClick(candidate.getEmail(), holder.getAdapterPosition());
                }
            }
        });
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
        Button voteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.vcandidate_nameTextView);
            ageTextView = itemView.findViewById(R.id.vcandidate_ageTextView);
            genderTextView = itemView.findViewById(R.id.vcandidate_genderTextView);
            partyTextView = itemView.findViewById(R.id.vcandidate_partyTextView);
            voteButton = itemView.findViewById(R.id.candidateVoteBtn);
        }
    }
}
