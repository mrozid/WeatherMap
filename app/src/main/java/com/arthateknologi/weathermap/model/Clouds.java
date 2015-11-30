package com.arthateknologi.weathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds {

    @SerializedName("all")
    @Expose
    private String all;


    public String getAll() {
        return all;
    }


    public void setAll(String all) {
        this.all = all;
    }
}
