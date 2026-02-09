package com.itravel.app.ui.auth;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.itravel.app.R;
import com.itravel.app.ui.main.MainActivity;
import com.itravel.app.util.ValidationUtils;

public class LoginFragment extends Fragment {

    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        TextInputEditText etEmail = view.findViewById(R.id.etEmail);
        TextInputEditText etPassword = view.findViewById(R.id.etPassword);
        Button btnDoLogin = view.findViewById(R.id.btnDoLogin);
        TextView tvGoToRegister = view.findViewById(R.id.tvGoToRegister);

        btnDoLogin.setOnClickListener(v -> {
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String password = etPassword.getText() != null ? etPassword.getText().toString() : "";

            if (!ValidationUtils.isValidEmail(email)) {
                Snackbar.make(view, R.string.error_invalid_email, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (!ValidationUtils.isValidPassword(password)) {
                Snackbar.make(view, R.string.error_invalid_password, Snackbar.LENGTH_SHORT).show();
                return;
            }

            viewModel.login(email, password);
        });

        tvGoToRegister.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_login_to_register));

        viewModel.getAuthResult().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;
            if (result.success) {
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            } else {
                Snackbar.make(view, result.errorMessage, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
