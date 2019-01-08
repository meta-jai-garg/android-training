package com.metacube.intermediatefirst.activities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.metacube.intermediatefirst.R;


public class MainActivity extends BaseActivity {

    private MaterialButton grantMultiplePermissionBtn, defaultNotificationBtn,
            customNotificationBtn, customDrawableBtn, customViewBtn;
    private final String CHANNEL_ID = "System";
    private NotificationManagerCompat notificationManagerCompat;
    private NotificationCompat.Builder notificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            requestRunTimePermission(this, new String[]{Manifest.permission.READ_CONTACTS},
                    CONTACT_PERMISSION_REQUEST_CODE);
        }

        createNotificationChannel();
        FirebaseMessaging.getInstance().subscribeToTopic("general");
        init();
        methodListener();
    }

    private void init() {
        grantMultiplePermissionBtn = findViewById(R.id.grantMultiplePermissionBtn);
        defaultNotificationBtn = findViewById(R.id.defaultNotificationBtn);
        customNotificationBtn = findViewById(R.id.customNotificationBtn);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        customDrawableBtn = findViewById(R.id.customDrawableBtn);
        customViewBtn = findViewById(R.id.customViewBtn);
    }

    private void methodListener() {
        grantMultiplePermissionBtn.setOnClickListener(v -> {
            if (areMultiplePermissionEnabled()) {
                Toast.makeText(this, "All permissions granted.", Toast.LENGTH_SHORT).show();
            } else {
                requestRunTimePermission(this, multiplePermission,
                        MULTIPLE_PERMISSIONS_REQUEST_CODE);
            }
        });

        defaultNotificationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            notificationBuilder.setSmallIcon(android.R.drawable.ic_btn_speak_now)
                    .setContentTitle(getString(R.string.notification_title))
                    .setContentText(getString(R.string.notification_content))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setStyle(
                            new NotificationCompat.BigTextStyle()
                                    .bigText(getString(R.string.notification_content))
                    ).setContentIntent(pendingIntent);
            notificationManagerCompat.notify(1, notificationBuilder.build());
        });

        customNotificationBtn.setOnClickListener(v -> {
            notificationBuilder.setSmallIcon(android.R.drawable.arrow_down_float)
                    .setContentTitle("Image Download")
                    .setContentText("Download in progress")
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setProgress(100, 0, false);
            notificationManagerCompat.notify(2, notificationBuilder.build());

            new Thread(() -> {
                for (int i = 1; i <= 100; i++) {
                    notificationBuilder.setProgress(100, i, false);
                    notificationManagerCompat.notify(2, notificationBuilder.build());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notificationBuilder.setContentText("Download Complete")
                        .setProgress(0, 0, false);
                notificationManagerCompat.notify(2, notificationBuilder.build());
            }).start();
        });

        customDrawableBtn.setOnClickListener(v -> startActivity(new Intent(this,
                CustomDrawableActivity.class)));

        customViewBtn.setOnClickListener(v -> startActivity(new Intent(this, CustomViewActivity
                .class)));
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case CONTACT_PERMISSION_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        permissions[0])) {
                    requestRunTimePermission(this, new String[]{Manifest.permission.READ_CONTACTS},
                            CONTACT_PERMISSION_REQUEST_CODE);
                } else {
                    showPermissionAlertDialog(getString(R.string.permission_dialog_message));
                }
            }
            break;
            case MULTIPLE_PERMISSIONS_REQUEST_CODE: {
                if (areMultiplePermissionEnabled()) {
                    Toast.makeText(this, "Please go ahead do your stuff", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    String[] deniedPermissions = requestDeniedPermissions();
                    String permissionString = deniedPermissions.length == 1 ? "Permission" :
                            "Permissions";

                    if (!shouldShowRequestPermissionRationale(deniedPermissions[0])) {
                        showPermissionAlertDialog(getString(R.string.permission_dialog_message));
                        return;
                    } else if (!shouldShowRequestPermissionRationale(deniedPermissions[0])
                            && deniedPermissions.length == 1) {
                        showPermissionAlertDialog(getString(R.string.permission_dialog_message));
                        return;
                    } else {
                        Snackbar.make(
                                findViewById(android.R.id.content),
                                permissionString + " denied. To enable now click here",
                                Snackbar.LENGTH_INDEFINITE
                        ).setAction(
                                "ENABLE",
                                v -> ActivityCompat.requestPermissions(
                                        MainActivity.this,
                                        requestDeniedPermissions(),
                                        MULTIPLE_PERMISSIONS_REQUEST_CODE
                                )
                        ).show();
                    }
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}