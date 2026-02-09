package com.itravel.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.itravel.app.R;
import com.itravel.app.utils.ValidationUtils;

public class AddReviewFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText etReview;
    private TextView tvGpsStatus;
    private Button btnSubmit;

    // Mock location check for MVP
    private boolean isLocationValid = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ratingBar = view.findViewById(R.id.ratingBar);
        etReview = view.findViewById(R.id.etReview);
        tvGpsStatus = view.findViewById(R.id.tvGpsStatus);
        btnSubmit = view.findViewById(R.id.btnSubmitReview);

        // Simulate GPS Check
        tvGpsStatus.setText("Location Verified (Mock)");
        tvGpsStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark, null));

        btnSubmit.setOnClickListener(v -> submitReview());
    }

    private void submitReview() {
        if (!isLocationValid) {
            Toast.makeText(getContext(), "You must be at the location to review!", Toast.LENGTH_SHORT).show();
            return;
        }

        String reviewText = etReview.getText().toString();
        if (!ValidationUtils.isValidReviewText(reviewText)) {
            Toast.makeText(getContext(), "Review must be at least 50 characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        float rating = ratingBar.getRating();
        if (rating == 0) {
            Toast.makeText(getContext(), "Please provide a rating.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Submit to Backend/DB
        Toast.makeText(getContext(), "Review Submitted!", Toast.LENGTH_LONG).show();

        // Reset or navigate back
        etReview.setText("");
        ratingBar.setRating(0);
    }
}
