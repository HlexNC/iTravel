package com.itravel.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.itravel.app.R;
import com.itravel.app.activities.MainActivity;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnDoLogin = view.findViewById(R.id.btnDoLogin);
        TextView tvGoToRegister = view.findViewById(R.id.tvGoToRegister);

        btnDoLogin.setOnClickListener(v -> {
            // Mock Login Success
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        tvGoToRegister
                .setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_login_to_register));
    }
}
