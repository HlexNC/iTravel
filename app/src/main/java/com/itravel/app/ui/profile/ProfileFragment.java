package com.itravel.app.ui.profile;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.itravel.app.R;
import com.itravel.app.data.local.prefs.SessionManager;
import com.itravel.app.ui.auth.AuthActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private ProfileReviewAdapter reviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        TextView tvAvatar = view.findViewById(R.id.tvProfileAvatar);
        TextView tvName = view.findViewById(R.id.tvProfileName);
        TextView tvEmail = view.findViewById(R.id.tvProfileEmail);
        TextView tvStatReviews = view.findViewById(R.id.tvStatReviews);
        TextView tvStatVisited = view.findViewById(R.id.tvStatVisited);
        TextView tvStatAvgRating = view.findViewById(R.id.tvStatAvgRating);
        RecyclerView recyclerReviews = view.findViewById(R.id.recyclerViewProfileReviews);
        View layoutNoReviews = view.findViewById(R.id.layoutNoProfileReviews);
        MaterialButton btnLogout = view.findViewById(R.id.btnLogout);
        TextView tvVersion = view.findViewById(R.id.tvVersion);

        // Set app version
        try {
            PackageInfo pInfo = requireContext().getPackageManager()
                    .getPackageInfo(requireContext().getPackageName(), 0);
            tvVersion.setText("v" + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            tvVersion.setText("v1.0");
        }

        // Setup reviews list
        recyclerReviews.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewAdapter = new ProfileReviewAdapter(new ArrayList<>(), new HashMap<>());
        recyclerReviews.setAdapter(reviewAdapter);

        // Observe user info
        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            tvName.setText(name != null && !name.isEmpty() ? name : "Traveler");
            if (name != null && !name.isEmpty()) {
                String[] parts = name.split(" ");
                String initials = String.valueOf(parts[0].charAt(0));
                if (parts.length > 1) {
                    initials += parts[parts.length - 1].charAt(0);
                }
                tvAvatar.setText(initials.toUpperCase(Locale.ROOT));
            }
        });

        viewModel.getUserEmail().observe(getViewLifecycleOwner(), email ->
                tvEmail.setText(email != null ? email : ""));

        // Observe stats
        viewModel.getReviewCount().observe(getViewLifecycleOwner(), count ->
                tvStatReviews.setText(String.valueOf(count)));

        viewModel.getVisitedCount().observe(getViewLifecycleOwner(), count ->
                tvStatVisited.setText(String.valueOf(count)));

        viewModel.getAvgRating().observe(getViewLifecycleOwner(), avg -> {
            if (avg != null && avg > 0) {
                tvStatAvgRating.setText(String.format(Locale.US, "%.1f", avg));
            } else {
                tvStatAvgRating.setText("—");
            }
        });

        // Observe user reviews
        viewModel.getUserReviews().observe(getViewLifecycleOwner(), reviews -> {
            if (reviews == null || reviews.isEmpty()) {
                layoutNoReviews.setVisibility(View.VISIBLE);
                recyclerReviews.setVisibility(View.GONE);
            } else {
                layoutNoReviews.setVisibility(View.GONE);
                recyclerReviews.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getLocationNames().observe(getViewLifecycleOwner(), names -> {
            if (viewModel.getUserReviews().getValue() != null) {
                reviewAdapter.updateData(viewModel.getUserReviews().getValue(), names);
            }
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            new SessionManager(requireContext()).clearSession();
            Intent intent = new Intent(requireActivity(), AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        viewModel.loadProfile();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel != null) {
            viewModel.loadProfile();
        }
    }
}
