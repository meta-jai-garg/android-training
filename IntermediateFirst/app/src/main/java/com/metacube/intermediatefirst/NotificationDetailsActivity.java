package com.metacube.intermediatefirst;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class NotificationDetailsActivity extends BaseActivity implements View.OnClickListener {

    private Handler mHandler;
    private ProgressBar progressBar;
    private BroadcastReceiver broadcastReceiver;
    private MaterialButton startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Log.d("123456", "onCreate: " + Thread.currentThread().getId());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            requestRunTimePermission(this, new String[]{Manifest.permission.READ_SMS},
                    SMS_PERMISSION_REQUEST_CODE);
        }
        init();
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                requestRunTimePermission(this, new String[]{Manifest.permission.READ_CONTACTS},
                        CONTACT_PERMISSION_REQUEST_CODE);
            } else {
                showPermissionAlertDialog(getString(R.string.permission_dialog_message));
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void init() {
        mHandler = new Handler();
        broadcastReceiver = new MyBroadcastReceiver();
        startBtn = findViewById(R.id.startBtn);
        progressBar = findViewById(R.id.progressBar);
    }


    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
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
