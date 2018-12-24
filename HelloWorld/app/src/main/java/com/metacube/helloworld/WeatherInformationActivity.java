package com.metacube.helloworld;

import android.support.v4.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;

import com.metacube.helloworld.mapper.JSONMapper;
import com.metacube.helloworld.pojo.WeatherDetailPojo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class WeatherInformationActivity extends AppCompatActivity {

    private String[] cities = {"Jaipur", "Jodhpur"};
    private AutoCompleteTextView cityTextView;
    private ArrayAdapter<String> cityAdapter;
    private WeatherDetailPojo weatherDetailPojo;
    private FrameLayout weatherDetailFragmentContainer;
    private Bundle bundle;
    private WeatherDetailFragment weatherDetailFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isConnectedToNetwork()) {
            setContentView(R.layout.activity_weather_information);
            cityAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, cities);
            init();
            methodListener();
        }
    }


    private void init() {
        cityTextView = findViewById(R.id.cityTextView);
        cityTextView.setThreshold(1);
        cityTextView.setAdapter(cityAdapter);
        weatherDetailFragmentContainer = findViewById(R.id.weatherDetailFragmentContainer);
        bundle = new Bundle();
        weatherDetailFragment = new WeatherDetailFragment();
        fragmentManager = getSupportFragmentManager();
    }

    private void methodListener() {
        cityTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityTextView.clearFocus();
                try {

                    weatherDetailPojo = new GetWeatherData().execute(String.valueOf(parent
                            .getItemAtPosition(position))).get();
                    bundle.putSerializable("weatherData", weatherDetailPojo);
                    weatherDetailFragment.setArguments(bundle);
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.weatherDetailFragmentContainer, weatherDetailFragment)
                            .commit();

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cityTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && getSupportFragmentManager().findFragmentById(R.id
                        .weatherDetailFragmentContainer) != null) {
                    getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull
                            (getSupportFragmentManager().findFragmentById(R.id
                                    .weatherDetailFragmentContainer))).commit();
                }
            }
        });
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(networkInfo != null && networkInfo.isConnected())) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Please Connect to Internet.....", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private class GetWeatherData extends AsyncTask<String, Integer, WeatherDetailPojo> {

        private ProgressDialog progressDialog;
        private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
        private static final String APP_ID = "&units=metric&APPID=37a48452e709699e3c1e56370bd13724";

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(WeatherInformationActivity.this, "", "Getting " +
                    "Result......");
        }

        @Override
        protected WeatherDetailPojo doInBackground(String... strings) {
            WeatherDetailPojo weatherDetailPojo = new WeatherDetailPojo();
            try {
                URL url = new URL(BASE_URL + strings[0] + ",IN" + APP_ID);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                weatherDetailPojo = JSONMapper.responseToObject(builder.toString());
                urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return weatherDetailPojo;
        }

        @Override
        protected void onPostExecute(WeatherDetailPojo weatherDetail) {
            progressDialog.dismiss();
        }
    }
}
