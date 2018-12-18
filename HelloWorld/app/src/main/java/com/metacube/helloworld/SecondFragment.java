package com.metacube.helloworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment {

    private TextView resultantText, activityText;
    private String receivedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_second, container, false);
        activityText = view.findViewById(R.id.activityText);
        resultantText = view.findViewById(R.id.resultantText);
        if (getArguments() != null) {
            activityText.setText(getArguments().getString("data"));
        }
        return view;
    }

    public void changeText(String data) {
        resultantText.setText(data);
    }

}
