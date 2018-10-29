package com.kirill.earthquake.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.kirill.earthquake.mvp.models.Earthquake;

import java.util.List;

public interface EarthquakesView extends MvpView {
    void showEarthquakes(List<Earthquake> earthquakes);
}
