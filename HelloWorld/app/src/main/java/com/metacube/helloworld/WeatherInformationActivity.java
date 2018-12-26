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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.metacube.helloworld.mapper.JSONMapper;
import com.metacube.helloworld.pojo.WeatherDetailPojo;

import org.json.JSONObject;

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

    private String[] cities = {"Ajmer", "Alwar", "Jodhpur"};
    private AutoCompleteTextView cityTextView;
    private ArrayAdapter<String> cityAdapter;
    private WeatherDetailPojo weatherDetailPojo;
    private FrameLayout weatherDetailFragmentContainer;
    private Bundle bundle;
    private WeatherDetailFragment weatherDetailFragment;
    private FragmentManager fragmentManager;
    private String requestType;

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
        fragmentManager = getSupportFragmentManager();
        weatherDetailFragment = new WeatherDetailFragment();
        requestType = getIntent().getStringExtra("requestType");
    }

    private void methodListener() {
        cityTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                InputMethodManager in = (InputMethodManager) getSystemService(Context
                        .INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(parent.getApplicationWindowToken(), 0);
                cityTextView.clearFocus();

                try {

                    String selectedCity = String.valueOf(parent.getItemAtPosition(position));

                    if (Constants.REQUEST_TYPE.equals(requestType)) {
                        loadWeatherData(selectedCity, new VolleyCallback() {
                            @Override
                            public void onSuccess(WeatherDetailPojo weatherDetailPojo) {
                                startFragment(weatherDetailPojo);
                            }
                        });
                    } else {
                        weatherDetailPojo = new GetWeatherData().execute(selectedCity).get();
                        startFragment(weatherDetailPojo);
                    }
//                    if (weatherDetailPojo != null) {
//                        bundle.putSerializable("weatherData", weatherDetailPojo);
//                        weatherDetailFragment.setArguments(bundle);
//                        fragmentManager
//                                .beginTransaction()
//                                .replace(R.id.weatherDetailFragmentContainer,
// weatherDetailFragment)
//                                .commit();
//                    } else {
//                        Toast.makeText(WeatherInformationActivity.this, "Please try after " +
//                                "sometime..", Toast.LENGTH_SHORT)
//                                .show();
//                    }

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
                    getSupportFragmentManager().beginTransaction().remove
                            (getSupportFragmentManager().findFragmentById(R.id
                                    .weatherDetailFragmentContainer)).commit();
                }
            }
        });
    }

    private void loadWeatherData(String selectedCity, final VolleyCallback callback) {
        final ProgressDialog progressDialog = ProgressDialog.show(WeatherInformationActivity
                        .this, "",
                "Getting Result........");

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.BASE_URL + selectedCity + ",IN" + Constants.APP_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        callback.onSuccess(JSONMapper.responseToObject(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(WeatherInformationActivity.this,
                                "Please try after sometime.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(WeatherInformationActivity.this, "", "Getting " +
                    "Result......");
        }

        @Override
        protected WeatherDetailPojo doInBackground(String... strings) {
            try {
                URL url = new URL(Constants.BASE_URL + strings[0] + ",IN" + Constants.APP_ID);

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

    private void startFragment(WeatherDetailPojo weatherDetailPojo) {
        Log.d("12345", "startFragment: " + weatherDetailPojo.toString());
        bundle.putSerializable("weatherData", weatherDetailPojo);
        weatherDetailFragment.setArguments(bundle);
        fragmentManager
                .beginTransaction()
                .replace(R.id.weatherDetailFragmentContainer, weatherDetailFragment).commit();
    }
}
