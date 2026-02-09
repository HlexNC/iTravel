package com.itravel.app.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.itravel.app.R;
import com.itravel.app.ui.main.MainActivity;
import com.itravel.app.util.ValidationUtils;

public class RegisterFragment extends Fragment {

    private AuthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        TextInputLayout tilName = view.findViewById(R.id.tilName);
        TextInputLayout tilEmail = view.findViewById(R.id.tilEmail);
        TextInputLayout tilPassword = view.findViewById(R.id.tilPassword);
        TextInputEditText etName = view.findViewById(R.id.etName);
        TextInputEditText etEmail = view.findViewById(R.id.etEmail);
        TextInputEditText etPassword = view.findViewById(R.id.etPassword);
        MaterialButton btnDoRegister = view.findViewById(R.id.btnDoRegister);
        CircularProgressIndicator progressRegister = view.findViewById(R.id.progressRegister);

        btnDoRegister.setOnClickListener(v -> {
            tilName.setError(null);
            tilEmail.setError(null);
            tilPassword.setError(null);

            String name = etName.getText() != null ? etName.getText().toString().trim() : "";
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String password = etPassword.getText() != null ? etPassword.getText().toString() : "";

            if (name.isEmpty()) {
                tilName.setError(getString(R.string.error_name_required));
                return;
            }
            if (!ValidationUtils.isValidEmail(email)) {
                tilEmail.setError(getString(R.string.error_invalid_email));
                return;
            }
            if (!ValidationUtils.isValidPassword(password)) {
                tilPassword.setError(getString(R.string.error_invalid_password));
                return;
            }

            btnDoRegister.setEnabled(false);
            progressRegister.setVisibility(View.VISIBLE);

            viewModel.register(name, email, password);
        });

        viewModel.getAuthResult().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            btnDoRegister.setEnabled(true);
            progressRegister.setVisibility(View.GONE);

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
