package com.itravel.app.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itravel.app.R;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private FeedViewModel viewModel;
    private FeedAdapter adapter;

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFeed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FeedAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel.getFeedItems().observe(getViewLifecycleOwner(), items -> {
            adapter.updateItems(items);
        });

        viewModel.loadFeed();
    }
}
