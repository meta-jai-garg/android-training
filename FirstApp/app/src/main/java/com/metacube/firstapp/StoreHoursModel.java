package com.metacube.firstapp;

public class StoreHoursModel {
    private String day;
    private String timing;

    public StoreHoursModel(String day, String timing) {
        this.day = day;
        this.timing = timing;
    }

    public String getDay() {
        return day;
    }

    public String getTiming() {
        return timing;
    }

}
