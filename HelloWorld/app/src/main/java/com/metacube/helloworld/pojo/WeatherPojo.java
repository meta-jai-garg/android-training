package com.metacube.helloworld.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherPojo {
    private String icon;
    private String description;
    private String main;
    private int id;
}
