package com.metacube.firstapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MigrationFragment extends Fragment {

    public MigrationFragment() {
    }

    public static MigrationFragment newInstance() {
        return new MigrationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_migration, container, false);
    }

}
