package com.example.tp4.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class City implements Serializable {

    private String id;
    private String name;
    private String country;
    private Long sunrise;
    private Long sunset;

    public City(String id, String name, String country, Long sunrise, Long sunset) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getSunriseToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date newDate;
        newDate = new Date((this.sunrise * 1000L));
        return sdf.format(newDate);
    }

    public String getSunsetToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date newDate;
        newDate = new Date((this.sunset * 1000L));
        return sdf.format(newDate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

}