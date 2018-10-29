package com.kirill.earthquake.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Earthquake implements Serializable {
    private static final long serialVersionUID = 6L;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("properties")
    @Expose
    private Properties properties;

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }


}