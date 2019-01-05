package com.metacube.intermediatefirst;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements ActivityCompat
        .OnRequestPermissionsResultCallback {

    protected final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 10;
    protected final int CONTACT_PERMISSION_REQUEST_CODE = 20;
    protected final int SMS_PERMISSION_REQUEST_CODE = 30;

    protected final String[] multiplePermission = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private String[] deniedPermissionsAmongMultiplePermissions;


    protected void requestRunTimePermission(final Activity activity, final String[] permissions,
                                            final int permissionRequestCode) {
        if (permissions.length == 1) {
            requestPermission(activity, permissions, permissionRequestCode);
        } else if (permissions.length > 1 && permissionRequestCode ==
                MULTIPLE_PERMISSIONS_REQUEST_CODE) {
            if (requestDeniedPermissions().length == 1) {
                requestPermission(activity, deniedPermissionsAmongMultiplePermissions,
                        permissionRequestCode);
            } else if (requestDeniedPermissions().length > 1) {
                if (isFirstTimeAskForMultiplePermission()) {
                    ActivityCompat.requestPermissions(activity,
                            deniedPermissionsAmongMultiplePermissions, permissionRequestCode);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "This functionality needs " +
                            "multiple app permissions", Snackbar.LENGTH_INDEFINITE).setAction
                            ("ENABLE",
                                    v -> ActivityCompat.requestPermissions(activity,
                                            deniedPermissionsAmongMultiplePermissions,
                                            permissionRequestCode)
                            ).show();
                }
            }
        }
    }

    protected boolean isFirstTimeAskForMultiplePermission() {
        SharedPreferences sharedPreferences = getSharedPreferences("permissionasks", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("MULTIPLE_PERMISSION", true);
        if (isFirstTime) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("MULTIPLE_PERMISSION", false);
            editor.apply();
        }
        return isFirstTime;
    }

    private void requestPermission(final Activity activity, final String[] permissions, final int
            permissionRequestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {
            Snackbar.make(
                    findViewById(android.R.id.content),
                    "App requires permission to work",
                    Snackbar.LENGTH_INDEFINITE
            ).setAction(
                    "ENABLE",
                    v -> ActivityCompat.requestPermissions(activity, permissions,
                            permissionRequestCode)
            ).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permissions[0]},
                    permissionRequestCode);
        }
    }

    protected boolean areMultiplePermissionEnabled() {
        for (String permission : multiplePermission) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    protected String[] requestDeniedPermissions() {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : multiplePermission) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        this.deniedPermissionsAmongMultiplePermissions = deniedPermissions.toArray(new
                String[deniedPermissions.size()]);
        return deniedPermissionsAmongMultiplePermissions;
    }


    protected void showPermissionAlertDialog(final String message) {
        new AlertDialog.Builder(this, R.style.CustomDialogTheme)
                .setTitle("Permission Grant")
                .setMessage(message)
                .setPositiveButton("Go to Settings", (dialog, which) -> startAppPermissions()
                ).show();
    }

    private void startAppPermissions() {
        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setData(Uri.fromParts("package", getPackageName(), null)));
    }

}
