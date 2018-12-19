package com.metacube.helloworld;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentCommunicationActivity extends AppCompatActivity implements
        FragmentCommunicator, ActivityCommunicator {

    private Button notifyFragmentBtn;
    private FragmentManager manager;
    private TextView resultText;
    private SecondFragment secondFragment;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_communication);
        init();
        methodListener();
    }

    private void init() {
        manager = getSupportFragmentManager();
        secondFragment = (SecondFragment) manager.findFragmentById(R.id.secondFragment);
        notifyFragmentBtn = findViewById(R.id.notifyFragmentBtn);
        frameLayout = findViewById(R.id.fragmentContainer);
        linearLayout = findViewById(R.id.secondFragmentLayout);
        resultText = findViewById(R.id.resultText);
    }

    private void methodListener() {
        notifyFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("data", "From Activity");
                SecondFragment secondFragment = new SecondFragment();
                secondFragment.setArguments(bundle);
                linearLayout.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                manager.beginTransaction().replace(R.id.fragmentContainer, secondFragment).commit();
            }
        });
    }

    @Override
    public void passDataToFragment(String data) {
        if (frameLayout.getVisibility() == View.VISIBLE) {
            frameLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
        secondFragment.changeText(data);
    }

    @Override
    public void passDataToActivity(String data) {
        resultText.setText("Addition is: " + data);
    }
}
