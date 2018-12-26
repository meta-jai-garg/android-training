package com.metacube.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metacube.helloworld.pojo.WeatherDetailPojo;
import com.metacube.helloworld.pojo.WeatherPojo;

public class WeatherDetailFragment extends Fragment {

    private TextView cityText, countryText, temperatureText, humidityText, pressureText,
            windSpeedText, cloudinessText, weatherConditionText, longitudeText, latitudeText;
    private WeatherDetailPojo weatherDetailPojo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        init(view);
        if (getArguments() != null) {
            weatherDetailPojo = (WeatherDetailPojo) getArguments().getSerializable("weatherData");
        }
        populateData(weatherDetailPojo);
        return view;
    }


    private void init(View view) {
        cityText = view.findViewById(R.id.cityText);
        countryText = view.findViewById(R.id.countryText);
        temperatureText = view.findViewById(R.id.temperatureText);
        humidityText = view.findViewById(R.id.humidityText);
        pressureText = view.findViewById(R.id.pressureText);
        windSpeedText = view.findViewById(R.id.windSpeedText);
        cloudinessText = view.findViewById(R.id.cloudinessText);
        weatherConditionText = view.findViewById(R.id.weatherConditionText);
        longitudeText = view.findViewById(R.id.longitudeText);
        latitudeText = view.findViewById(R.id.latitudeText);
    }

    private void populateData(WeatherDetailPojo weatherDetailPojo) {
        cityText.setText(weatherDetailPojo.getName());
        countryText.setText(weatherDetailPojo.getSys().getCountry());
        temperatureText.setText(Double.toString(weatherDetailPojo.getMain().getTemp()) + " \u2103");
        humidityText.setText(Integer.toString(weatherDetailPojo.getMain().getHumidity()) + "%");
        pressureText.setText(Integer.toString(weatherDetailPojo.getMain().getPressure()) + "hPa");
        windSpeedText.setText(Double.toString(weatherDetailPojo.getWind().getSpeed()) + "m/s");
        cloudinessText.setText(Integer.toString(weatherDetailPojo.getClouds().getAll()) + "%");
        WeatherPojo weatherPojo = weatherDetailPojo.getWeather().get(0);
        weatherConditionText.setText(weatherPojo.getDescription());
        longitudeText.setText(Double.toString(weatherDetailPojo.getCoord().getLon()));
        latitudeText.setText(Double.toString(weatherDetailPojo.getCoord().getLat()));
    }

}
