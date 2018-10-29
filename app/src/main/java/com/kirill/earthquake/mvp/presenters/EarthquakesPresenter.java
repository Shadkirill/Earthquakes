package com.kirill.earthquake.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.views.EarthquakesView;

import java.util.List;

@InjectViewState
public class EarthquakesPresenter extends MvpPresenter<EarthquakesView> {
    private List<Earthquake> mEarthquakes;

    public EarthquakesPresenter(List<Earthquake> mEarthquakes) {
        super();
        this.mEarthquakes = mEarthquakes;
        getViewState().showEarthquakes(mEarthquakes);
    }

}
