package com.itravel.app.ui.profile;

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
import java.util.Map;

public class ProfileReviewAdapter extends RecyclerView.Adapter<ProfileReviewAdapter.ViewHolder> {

    private List<ReviewEntity> reviews;
    private Map<Long, String> locationNames;

    public ProfileReviewAdapter(List<ReviewEntity> reviews, Map<Long, String> locationNames) {
        this.reviews = reviews;
        this.locationNames = locationNames;
    }

    public void updateData(List<ReviewEntity> reviews, Map<Long, String> locationNames) {
        this.reviews = reviews;
        this.locationNames = locationNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewEntity review = reviews.get(position);

        String locationName = locationNames.getOrDefault(review.locationId, "Unknown Location");
        holder.tvLocationName.setText(locationName);
        holder.tvRating.setText(String.format(Locale.US, "%.1f", review.rating));
        holder.tvText.setText(review.text);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        holder.tvDate.setText(sdf.format(new Date(review.timestamp)));
    }

    @Override
    public int getItemCount() {
        return reviews != null ? reviews.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLocationName, tvRating, tvText, tvDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocationName = itemView.findViewById(R.id.tvLocationName);
            tvRating = itemView.findViewById(R.id.tvReviewRating);
            tvText = itemView.findViewById(R.id.tvReviewText);
            tvDate = itemView.findViewById(R.id.tvReviewDate);
        }
    }
}
