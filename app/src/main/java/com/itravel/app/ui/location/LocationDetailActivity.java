package com.itravel.app.ui.location;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.itravel.app.R;
import com.itravel.app.data.local.db.LocationEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class LocationDetailActivity extends AppCompatActivity {

    private LocationDetailViewModel viewModel;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        viewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);

        // Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);
        ImageView ivLocationImage = findViewById(R.id.ivLocationImage);
        Chip chipCategory = findViewById(R.id.chipCategory);
        TextView tvRating = findViewById(R.id.tvRating);
        TextView tvReviewCount = findViewById(R.id.tvReviewCount);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvDescription = findViewById(R.id.tvDescription);
        MaterialButton btnSave = findViewById(R.id.btnSave);
        MaterialButton btnShare = findViewById(R.id.btnShare);
        MaterialButton btnMap = findViewById(R.id.btnMap);
        RecyclerView recyclerReviews = findViewById(R.id.recyclerViewReviews);
        View layoutNoReviews = findViewById(R.id.layoutNoReviews);
        ExtendedFloatingActionButton fabWriteReview = findViewById(R.id.fabWriteReview);
        ImageView ivShare = findViewById(R.id.ivShare);

        // Setup reviews RecyclerView
        recyclerReviews.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new ReviewAdapter(new ArrayList<>(), new HashMap<>());
        recyclerReviews.setAdapter(reviewAdapter);

        // Get location ID from intent
        long locationId = getIntent().getLongExtra("location_id", -1);
        String locationName = getIntent().getStringExtra("location_name");

        if (locationName != null) {
            collapsingToolbar.setTitle(locationName);
        }

        if (locationId != -1) {
            viewModel.loadLocation(locationId);
        }

        // Observe location data
        viewModel.getLocation().observe(this, location -> {
            if (location == null) return;

            collapsingToolbar.setTitle(location.name);
            tvAddress.setText(location.address);
            tvDescription.setText(location.description);

            // Set category chip with icon
            chipCategory.setText(location.category);
            setCategoryIcon(chipCategory, location.category);

            // Load hero image
            if (location.imageUrl != null && !location.imageUrl.isEmpty()) {
                Glide.with(this)
                        .load(location.imageUrl)
                        .centerCrop()
                        .into(ivLocationImage);
            }

            // Share button
            btnShare.setOnClickListener(v -> shareLocation(location));
            ivShare.setOnClickListener(v -> shareLocation(location));

            // Map button
            btnMap.setOnClickListener(v -> {
                Uri gmmUri = Uri.parse("geo:" + location.latitude + "," + location.longitude +
                        "?q=" + Uri.encode(location.name));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmUri);
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            });
        });

        // Observe rating
        viewModel.getAvgRating().observe(this, avg -> {
            if (avg != null && avg > 0) {
                tvRating.setText(String.format(Locale.US, "%.1f", avg));
            } else {
                tvRating.setText("—");
            }
        });

        // Observe review count
        viewModel.getReviewCount().observe(this, count -> {
            if (count != null && count > 0) {
                tvReviewCount.setText(getString(R.string.review_count, count));
            } else {
                tvReviewCount.setText("");
            }
        });

        // Observe like state
        viewModel.getIsLiked().observe(this, liked -> {
            if (liked) {
                btnSave.setIconResource(R.drawable.ic_favorite);
                btnSave.setIconTintResource(R.color.coral_action);
            } else {
                btnSave.setIconResource(R.drawable.ic_favorite_border);
                btnSave.setIconTintResource(R.color.teal_primary);
            }
        });

        btnSave.setOnClickListener(v -> {
            animateLikeButton(btnSave);
            viewModel.toggleLike();
        });

        // Observe reviews
        viewModel.getReviews().observe(this, reviews -> {
            if (reviews == null || reviews.isEmpty()) {
                layoutNoReviews.setVisibility(View.VISIBLE);
                recyclerReviews.setVisibility(View.GONE);
            } else {
                layoutNoReviews.setVisibility(View.GONE);
                recyclerReviews.setVisibility(View.VISIBLE);
                reviewAdapter.updateReviews(reviews);
                viewModel.loadUserNames(reviews);
            }
        });

        // Observe user names for reviews
        viewModel.getUserNames().observe(this, names -> {
            if (names != null && !names.isEmpty()) {
                reviewAdapter = new ReviewAdapter(
                        viewModel.getReviews().getValue() != null ?
                                viewModel.getReviews().getValue() : new ArrayList<>(),
                        names);
                recyclerReviews.setAdapter(reviewAdapter);
            }
        });

        // FAB - Write Review
        fabWriteReview.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putLong("location_id", viewModel.getLocationId());
            AddReviewFragment fragment = new AddReviewFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.coordinatorLayout, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void shareLocation(LocationEntity location) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, location.name);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Check out " + location.name + " on iTravel!\n" + location.address);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    }

    private void animateLikeButton(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.3f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.3f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(300);
        set.setInterpolator(new OvershootInterpolator());
        set.start();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void setCategoryIcon(Chip chip, String category) {
        switch (category) {
            case "Restaurant":
                chip.setChipIconResource(R.drawable.ic_category_restaurant);
                break;
            case "Hotel":
                chip.setChipIconResource(R.drawable.ic_category_hotel);
                break;
            case "Attraction":
                chip.setChipIconResource(R.drawable.ic_category_attraction);
                break;
            case "Nature":
                chip.setChipIconResource(R.drawable.ic_category_nature);
                break;
        }
        chip.setChipIconVisible(true);
    }
}
