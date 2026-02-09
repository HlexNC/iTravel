package com.itravel.app.ui.location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itravel.app.R;
import com.itravel.app.data.local.db.ReviewEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<ReviewEntity> reviews;
    private final java.util.Map<Long, String> userNames;

    public ReviewAdapter(List<ReviewEntity> reviews, java.util.Map<Long, String> userNames) {
        this.reviews = reviews;
        this.userNames = userNames;
    }

    public void updateReviews(List<ReviewEntity> newReviews) {
        this.reviews = newReviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewEntity review = reviews.get(position);

        String userName = userNames.getOrDefault(review.userId, "Traveler");
        holder.tvReviewerName.setText(userName);

        // Set initials avatar
        String initials = "";
        if (userName != null && !userName.isEmpty()) {
            String[] parts = userName.split(" ");
            initials = String.valueOf(parts[0].charAt(0));
            if (parts.length > 1) {
                initials += parts[parts.length - 1].charAt(0);
            }
        }
        holder.tvAvatar.setText(initials.toUpperCase(Locale.ROOT));

        holder.tvRating.setText(String.format(Locale.US, "%.1f", review.rating));
        holder.tvReviewText.setText(review.text);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        holder.tvDate.setText(sdf.format(new Date(review.timestamp)));
    }

    @Override
    public int getItemCount() {
        return reviews != null ? reviews.size() : 0;
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvAvatar;
        TextView tvReviewerName;
        TextView tvDate;
        TextView tvRating;
        TextView tvReviewText;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.tvReviewAvatar);
            tvReviewerName = itemView.findViewById(R.id.tvReviewerName);
            tvDate = itemView.findViewById(R.id.tvReviewDate);
            tvRating = itemView.findViewById(R.id.tvReviewRating);
            tvReviewText = itemView.findViewById(R.id.tvReviewText);
        }
    }
}
