package com.metacube.intermediatefirst;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    private MaterialButton grantMultiplePermissionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            requestRunTimePermission(this, new String[]{Manifest.permission.READ_CONTACTS},
                    CONTACT_PERMISSION_REQUEST_CODE);
        }

        init();
        methodListener();
    }

    private void init() {
        grantMultiplePermissionBtn = findViewById(R.id.grantMultiplePermissionBtn);
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
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case CONTACT_PERMISSION_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else if (shouldShowRequestPermissionRationale(permissions[0])) {
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