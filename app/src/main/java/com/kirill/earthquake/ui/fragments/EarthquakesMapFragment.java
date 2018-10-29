package com.kirill.earthquake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kirill.earthquake.R;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.models.Earthquakes;
import com.kirill.earthquake.mvp.presenters.EarthquakesPresenter;
import com.kirill.earthquake.mvp.views.EarthquakesView;

import java.util.List;

public class EarthquakesMapFragment extends MvpAppCompatFragment implements EarthquakesView, OnMapReadyCallback {
    public static final String ARGS_EARTHQUAKES = "argsEarthquakes";

    @InjectPresenter
    EarthquakesPresenter mEarthquakesPresenter;

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private List<Earthquake> mEarthquakes = null;

    @ProvidePresenter
    EarthquakesPresenter provideEarthquakesPresenter() {
        Earthquakes earthquakes = (Earthquakes) getArguments().get(ARGS_EARTHQUAKES);
        return new EarthquakesPresenter(earthquakes.getEarthquakes());
    }

    public static EarthquakesMapFragment getInstance(List<Earthquake> earthquakes) {
        EarthquakesMapFragment fragment = new EarthquakesMapFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_EARTHQUAKES, new Earthquakes(earthquakes));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earthquakes_maps, container, false);
        mMapView = view.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView.getMapAsync(this);
    }

    @Override
    public void showEarthquakes(@NonNull List<Earthquake> earthquakes) {
        mEarthquakes = earthquakes;
        if (mGoogleMap != null) {
            showEarthQuakes();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mEarthquakes != null && mEarthquakes.size() > 0) {
            showEarthQuakes();
        }
    }

    private void showEarthQuakes() {
        for (Earthquake earthquake : mEarthquakes) {
            LatLng latLng = new LatLng(Double.valueOf(earthquake.getGeometry().getCoordinates().get(1)), Double.valueOf(earthquake.getGeometry().getCoordinates().get(0)));
            mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(earthquake.getProperties().getMagnitude()));
            if (mEarthquakes.indexOf(earthquake) == mEarthquakes.size() - 1) {
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
