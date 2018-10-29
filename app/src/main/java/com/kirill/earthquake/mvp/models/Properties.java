package com.kirill.earthquake.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

public class Properties implements Serializable {
    private static final long serialVersionUID = 29L;

    @SerializedName("mag")
    @Expose
    private String magnitude;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("time")
    @Expose
    private long time;

    public String getMagnitude() {
        return this.magnitude;
    }
    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
    public String getPlace() {
        return this.place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }



}
