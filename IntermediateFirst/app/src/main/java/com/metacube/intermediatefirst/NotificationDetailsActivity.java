package com.metacube.intermediatefirst;

import android.os.Handler;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class NotificationDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Log.d("123456", "onCreate: " + Thread.currentThread().getId());
        mHandler = new Handler();
        MaterialButton startBtn = findViewById(R.id.startBtn);
        progressBar = findViewById(R.id.progressBar);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn: {
                startProgress();
            }
        }
    }

    private void startProgress() {
        new Thread(() -> {
            Log.d("123456", "startProgress: " + Thread.currentThread().getId());
            for (int i = 1; i <= 100; i++) {
                final int currentProgressCount = i;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(() -> progressBar.setProgress(currentProgressCount));
            }
        }).start();
    }
}
