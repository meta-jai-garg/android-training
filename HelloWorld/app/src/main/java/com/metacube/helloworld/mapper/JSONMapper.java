package com.metacube.helloworld.mapper;

import android.util.Log;

import com.metacube.helloworld.pojo.CloudsPojo;
import com.metacube.helloworld.pojo.CoordPojo;
import com.metacube.helloworld.pojo.MainPojo;
import com.metacube.helloworld.pojo.SysPojo;
import com.metacube.helloworld.pojo.WeatherDetailPojo;
import com.metacube.helloworld.pojo.WeatherPojo;
import com.metacube.helloworld.pojo.WindPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class JSONMapper {

    public static WeatherDetailPojo responseToObject(String jsonString) {

        WeatherDetailPojo weatherDetailPojo = null;

        try {
            weatherDetailPojo = new WeatherDetailPojo();
            JSONObject jsonObject = new JSONObject(jsonString);

            JSONObject coord = jsonObject.getJSONObject("coord");
            weatherDetailPojo.setCoord(
                    new CoordPojo(
                            coord.getDouble("lat"),
                            coord.getDouble("lon")
                    )
            );

            JSONObject clouds = jsonObject.getJSONObject("clouds");
            weatherDetailPojo.setClouds(
                    new CloudsPojo(
                            clouds.getInt("all")
                    )
            );

            JSONObject main = jsonObject.getJSONObject("main");
            weatherDetailPojo.setMain(
                    new MainPojo(
                            main.getDouble("temp_max"),
                            main.getDouble("temp_min"),
                            main.getInt("humidity"),
                            main.getInt("pressure"),
                            main.getDouble("temp")
                    )
            );

            JSONObject sys = jsonObject.getJSONObject("sys");
            weatherDetailPojo.setSys(
                    new SysPojo(
                            sys.getInt("sunset"),
                            sys.getInt("sunrise"),
                            sys.getString("country")
                    )
            );

            JSONArray weather = jsonObject.getJSONArray("weather");
            for (int i = 0; i < weather.length(); i++) {
                JSONObject object = weather.getJSONObject(i);
                weatherDetailPojo.setWeather(
                        Arrays.asList(
                                new WeatherPojo(
                                        object.getString("icon"),
                                        object.getString("description"),
                                        object.getString("main"),
                                        object.getInt("id")
                                )
                        )
                );
            }

            JSONObject wind = jsonObject.getJSONObject("wind");
            weatherDetailPojo.setWind(
                    new WindPojo(
                            wind.getInt("deg"),
                            wind.getDouble("speed")
                    )
            );

            weatherDetailPojo.setBase(jsonObject.getString("base"));
            weatherDetailPojo.setDt(jsonObject.getInt("dt"));
            weatherDetailPojo.setId(jsonObject.getInt("id"));
            weatherDetailPojo.setName(jsonObject.getString("name"));
            weatherDetailPojo.setCod(jsonObject.getInt("cod"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherDetailPojo;
    }

}
