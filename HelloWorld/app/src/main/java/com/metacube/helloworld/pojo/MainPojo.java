package com.metacube.helloworld.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MainPojo {
    private double tempMax;
    private double tempMin;
    private int humidity;
    private int pressure;
    private double temp;
}
