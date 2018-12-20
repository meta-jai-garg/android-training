package com.metacube.helloworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FirstFragment extends Fragment implements View.OnClickListener {

    private int counter;
    private EditText firstNumberEditText, secondNumberEditText;
    private FragmentCommunicator fragmentCommunicator;
    private ActivityCommunicator activityCommunicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button addBtn = view.findViewById(R.id.addBtn);
        Button clickBtn = view.findViewById(R.id.clickBtn);
        firstNumberEditText = view.findViewById(R.id.firstNumberEditText);
        secondNumberEditText = view.findViewById(R.id.secondNumberEditText);
        clickBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentCommunicator = (FragmentCommunicator) getActivity();
        activityCommunicator = (ActivityCommunicator) getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtn: {
                counter++;
                fragmentCommunicator.passDataToFragment("Button clicked " + counter + " time");
                break;
            }
            case R.id.addBtn: {
                int firstNumber = Integer.parseInt(String.valueOf(firstNumberEditText.getText()));
                int secondNumber = Integer.parseInt(String.valueOf(secondNumberEditText.getText()));
                activityCommunicator.passDataToActivity(Integer.toString(firstNumber +
                        secondNumber));
                break;
            }
            default:
                break;

        }
    }
}
