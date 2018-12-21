package com.metacube.helloworld;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WeatherInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_information);
        isConnectedToNetwork();
    }

    private void isConnectedToNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            Log.d("71196", "isConnectedToNetwork: " + networkInfo.toString());
        } else {
            Snackbar.make(findViewById(android.R.id.content),
                    "Please Connect to Internet.....", Snackbar.LENGTH_SHORT).show();
        }
    }
}
