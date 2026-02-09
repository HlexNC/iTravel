package com.itravel.app.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itravel.app.R;
import com.itravel.app.data.local.prefs.SessionManager;
import com.itravel.app.ui.auth.AuthActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SessionManager sessionManager = new SessionManager(requireContext());

        TextView tvLabel = view.findViewById(R.id.tvLabel);
        String userName = sessionManager.getUserName();
        tvLabel.setText(userName.isEmpty() ? "Profile" : userName);

        // Add logout button programmatically
        Button btnLogout = new Button(requireContext());
        btnLogout.setText(R.string.logout);
        btnLogout.setOnClickListener(v -> {
            sessionManager.clearSession();
            Intent intent = new Intent(requireActivity(), AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        // Add button below the label
        ViewGroup parent = (ViewGroup) tvLabel.getParent();
        if (parent instanceof android.widget.FrameLayout) {
            LinearLayout wrapper = new LinearLayout(requireContext());
            wrapper.setOrientation(LinearLayout.VERTICAL);
            wrapper.setGravity(android.view.Gravity.CENTER);
            wrapper.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parent.removeView(tvLabel);
            wrapper.addView(tvLabel);
            wrapper.addView(btnLogout);
            parent.addView(wrapper);
        }
    }
}
