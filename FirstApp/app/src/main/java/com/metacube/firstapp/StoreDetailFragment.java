package com.metacube.firstapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StoreDetailFragment extends Fragment {

    public StoreDetailFragment() {
    }

    public static StoreDetailFragment newInstance() {
        return new StoreDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView backTv = view.findViewById(R.id.back_tv);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        backTv.setOnClickListener(v -> Toast.makeText(getActivity(), "Clicked", Toast
                .LENGTH_SHORT).show());
        return view;
    }
}
