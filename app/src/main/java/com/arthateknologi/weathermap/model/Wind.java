package com.arthateknologi.weathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Wind {


    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private String deg;


    public Double getSpeed() {
        return speed;
    }


    public void setSpeed(Double speed) {
        this.speed = speed;
    }


    public String getDeg() {
        return deg;
    }


    public void setDeg(String deg) {
        this.deg = deg;
    }
}
