package com.metacube.firstapp;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, RewardsFragment.newInstance())
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_store:
                Toast.makeText(MainActivity.this, "Store", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_menu:
                Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_rewards:
                navigateTo(RewardsFragment.newInstance(), false);
                return true;
            case R.id.navigation_inbox:
                Toast.makeText(MainActivity.this, "Inbox", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_more:
                navigateTo(MoreDetailFragment.newInstance(), false);
                return true;
        }
        return false;
    };

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof StoreDetailFragment) {
            bottomNavigation.setVisibility(View.GONE);
        } else {
            bottomNavigation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
