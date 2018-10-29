package com.kirill.earthquake.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.kirill.earthquake.mvp.models.Earthquake;

import java.util.List;

public interface EarthquakesLoadView extends MvpView {

    void earthquakesLoading();

    void loaddingError();

    void earthquakesLoaded(List<Earthquake> earthquakes);
}
