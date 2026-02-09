package com.itravel.app.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itravel.app.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_main);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_view);

            // Custom bottom nav with crossfade animation
            bottomNav.setOnItemSelectedListener(item -> {
                NavOptions navOptions = new NavOptions.Builder()
                        .setEnterAnim(R.anim.fade_in)
                        .setExitAnim(R.anim.fade_out)
                        .setPopEnterAnim(R.anim.fade_in)
                        .setPopExitAnim(R.anim.fade_out)
                        .setLaunchSingleTop(true)
                        .setPopUpTo(navController.getGraph().getStartDestinationId(), false)
                        .build();
                try {
                    navController.navigate(item.getItemId(), null, navOptions);
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            });

            // Keep selected state in sync when navigating
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                int destId = destination.getId();
                for (int i = 0; i < bottomNav.getMenu().size(); i++) {
                    if (bottomNav.getMenu().getItem(i).getItemId() == destId) {
                        bottomNav.getMenu().getItem(i).setChecked(true);
                        break;
                    }
                }
            });
        }
    }
}
