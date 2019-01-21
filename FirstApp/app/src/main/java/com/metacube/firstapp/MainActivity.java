package com.metacube.firstapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                    .replace(R.id.fragmentContainer, StoreDetailFragment.newInstance())
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
                Toast.makeText(MainActivity.this, "Rewards", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_inbox:
                Toast.makeText(MainActivity.this, "Inbox", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_more:
                Toast.makeText(MainActivity.this, "More", Toast.LENGTH_SHORT).show();
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
}
