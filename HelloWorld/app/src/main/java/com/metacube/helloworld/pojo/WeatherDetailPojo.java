package com.metacube.helloworld.pojo;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDetailPojo implements Serializable {
    private int cod;
    private String name;
    private int id;
    private SysPojo sys;
    private int dt;
    private CloudsPojo clouds;
    private WindPojo wind;
    private MainPojo main;
    private String base;
    private List<WeatherPojo> weather;
    private CoordPojo coord;
}
