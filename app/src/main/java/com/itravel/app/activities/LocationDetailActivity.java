package com.itravel.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.itravel.app.R;
import com.itravel.app.fragments.AddReviewFragment;

public class LocationDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        String locationName = getIntent().getStringExtra("location_name");
        TextView tvTitle = findViewById(R.id.tvLocationTitle);
        if (locationName != null) {
            tvTitle.setText(locationName);
        }

        Button btnWriteReview = findViewById(R.id.btnWriteReview);
        btnWriteReview.setOnClickListener(v -> {
            // Hide the detail view elements and show the fragment
            tvTitle.setVisibility(View.GONE);
            btnWriteReview.setVisibility(View.GONE);

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new AddReviewFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
