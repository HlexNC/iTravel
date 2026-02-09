package com.itravel.app.ui.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.itravel.app.R;
import com.itravel.app.data.local.db.AppDatabase;
import com.itravel.app.data.local.db.ReviewEntity;
import com.itravel.app.data.local.prefs.SessionManager;
import com.itravel.app.util.ValidationUtils;

import java.util.concurrent.Executors;

public class AddReviewFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText etReview;
    private TextInputLayout tilReview;
    private TextView tvGpsStatus;
    private MaterialButton btnSubmit;

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

        MaterialToolbar toolbar = view.findViewById(R.id.toolbarReview);
        ratingBar = view.findViewById(R.id.ratingBar);
        etReview = view.findViewById(R.id.etReview);
        tilReview = view.findViewById(R.id.tilReview);
        tvGpsStatus = view.findViewById(R.id.tvGpsStatus);
        btnSubmit = view.findViewById(R.id.btnSubmitReview);

        toolbar.setNavigationOnClickListener(v -> {
            if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                getParentFragmentManager().popBackStack();
            } else {
                requireActivity().onBackPressed();
            }
        });

        tvGpsStatus.setText(R.string.location_verified);
        tvGpsStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.success_green));

        btnSubmit.setOnClickListener(v -> submitReview(view));
    }

    private void submitReview(View rootView) {
        if (!isLocationValid) {
            Snackbar.make(rootView, "You must be at the location to review!", Snackbar.LENGTH_SHORT).show();
            return;
        }

        String reviewText = etReview.getText().toString();
        if (!ValidationUtils.isValidReviewText(reviewText)) {
            tilReview.setError("Review must be at least 50 characters");
            return;
        } else {
            tilReview.setError(null);
        }

        float rating = ratingBar.getRating();
        if (rating == 0) {
            Snackbar.make(rootView, "Please provide a rating.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Save review to database
        long locationId = getArguments() != null ? getArguments().getLong("location_id", -1) : -1;
        SessionManager session = new SessionManager(requireContext());
        long userId = session.getUserId();

        if (locationId != -1 && userId != -1) {
            ReviewEntity review = new ReviewEntity();
            review.locationId = locationId;
            review.userId = userId;
            review.rating = rating;
            review.text = reviewText;
            review.timestamp = System.currentTimeMillis();

            btnSubmit.setEnabled(false);

            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase.getInstance(requireContext()).reviewDao().insert(review);
                requireActivity().runOnUiThread(() -> {
                    Snackbar.make(rootView, "Review submitted!", Snackbar.LENGTH_SHORT).show();
                    if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                        getParentFragmentManager().popBackStack();
                    }
                });
            });
        } else {
            Snackbar.make(rootView, "Review submitted!", Snackbar.LENGTH_SHORT).show();
            etReview.setText("");
            ratingBar.setRating(0);
        }
    }
}
