package com.itravel.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import com.itravel.app.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Mock loading for 2 seconds then go to Auth
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }, 2000);
    }
}
