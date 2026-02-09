package com.itravel.app.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.itravel.app.R;
import com.itravel.app.data.local.db.LocationEntity;
import com.itravel.app.ui.location.LocationDetailActivity;

import java.util.List;

public class DiscoverFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DiscoverViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Setup ChipGroup filter
        ChipGroup chipGroup = view.findViewById(R.id.chipGroupCategories);
        if (chipGroup != null) {
            chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
                if (checkedIds.isEmpty()) {
                    viewModel.setCategory("All");
                    return;
                }
                Chip chip = group.findViewById(checkedIds.get(0));
                if (chip != null) {
                    viewModel.setCategory(chip.getText().toString());
                }
            });
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(marker -> {
            Long locationId = (Long) marker.getTag();
            Intent intent = new Intent(getContext(), LocationDetailActivity.class);
            intent.putExtra("location_name", marker.getTitle());
            if (locationId != null) {
                intent.putExtra("location_id", locationId);
            }
            startActivity(intent);
            return true;
        });

        viewModel.getFilteredLocations().observe(getViewLifecycleOwner(), this::updateMapMarkers);
    }

    private void updateMapMarkers(List<LocationEntity> locations) {
        if (mMap == null || locations == null || locations.isEmpty()) return;

        mMap.clear();

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        for (LocationEntity location : locations) {
            LatLng position = new LatLng(location.latitude, location.longitude);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(location.name)
                    .snippet(location.category));
            if (marker != null) {
                marker.setTag(location.id);
            }
            boundsBuilder.include(position);
        }

        try {
            LatLngBounds bounds = boundsBuilder.build();
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            // Fallback if bounds can't be built
            LatLng first = new LatLng(locations.get(0).latitude, locations.get(0).longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(first, 5));
        }
    }
}
