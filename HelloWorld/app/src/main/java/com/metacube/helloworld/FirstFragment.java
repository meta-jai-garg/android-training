package com.metacube.helloworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class FirstFragment extends Fragment implements View.OnClickListener {

    private Button clickBtn;
    private int counter;
    private Communicator communicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator) getActivity();
        clickBtn = getActivity().findViewById(R.id.clickBtn);
        clickBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        counter++;
        communicator.respond("Button clicked "+counter+" time");
    }
}
