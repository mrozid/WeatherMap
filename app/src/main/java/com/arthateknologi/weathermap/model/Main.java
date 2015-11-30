package com.arthateknologi.weathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main {


    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;


    public Double getTemp() {
        return temp;
    }


    public void setTemp(Double temp) {
        this.temp = temp;
    }


    public String getPressure() {
        return pressure;
    }


    public void setPressure(String pressure) {
        this.pressure = pressure;
    }


    public String getHumidity() {
        return humidity;
    }


    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


    public Double getTempMin() {
        return tempMin;
    }


    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }


    public Double getTempMax() {
        return tempMax;
    }


    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }
}
