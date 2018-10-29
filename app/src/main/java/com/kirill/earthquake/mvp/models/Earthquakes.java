package com.kirill.earthquake.mvp.models;

import java.io.Serializable;
import java.util.List;

public class Earthquakes implements Serializable {
    private static final long serialVersionUID = 2L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private List<Earthquake> earthquakes;

    public Earthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }
}
