package com.itravel.app.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.itravel.app.R;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private FeedViewModel viewModel;
    private FeedAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private View layoutLoading;
    private View layoutEmpty;
    private View layoutError;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        swipeRefresh = view.findViewById(R.id.swipeRefreshFeed);
        recyclerView = view.findViewById(R.id.recyclerViewFeed);
        layoutLoading = view.findViewById(R.id.layoutFeedLoading);
        layoutEmpty = view.findViewById(R.id.layoutFeedEmpty);
        layoutError = view.findViewById(R.id.layoutFeedError);

        // Style pull-to-refresh
        swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.teal_primary),
                ContextCompat.getColor(requireContext(), R.color.orange_accent),
                ContextCompat.getColor(requireContext(), R.color.teal_secondary)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FeedAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(() -> viewModel.loadFeed());

        view.findViewById(R.id.btnRetry).setOnClickListener(v -> viewModel.loadFeed());

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading && adapter.getItemCount() == 0) {
                layoutLoading.setVisibility(View.VISIBLE);
                layoutEmpty.setVisibility(View.GONE);
                layoutError.setVisibility(View.GONE);
            } else {
                layoutLoading.setVisibility(View.GONE);
            }
            if (!isLoading) {
                swipeRefresh.setRefreshing(false);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null && adapter.getItemCount() == 0) {
                layoutError.setVisibility(View.VISIBLE);
                layoutEmpty.setVisibility(View.GONE);
                layoutLoading.setVisibility(View.GONE);
            }
        });

        viewModel.getFeedItems().observe(getViewLifecycleOwner(), items -> {
            layoutError.setVisibility(View.GONE);
            layoutLoading.setVisibility(View.GONE);
            if (items == null || items.isEmpty()) {
                layoutEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                layoutEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.updateItems(items);
            }
        });

        viewModel.loadFeed();
    }
}
