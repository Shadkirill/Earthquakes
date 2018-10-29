package com.kirill.earthquake.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.kirill.earthquake.R;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.models.Earthquakes;
import com.kirill.earthquake.mvp.presenters.EarthquakesPresenter;
import com.kirill.earthquake.mvp.views.EarthquakesView;
import com.kirill.earthquake.ui.adapters.EarthquakesRecyclerAdapter;

import java.util.List;

public class EarthquakesListFragment extends MvpAppCompatFragment implements EarthquakesView {
    public static final String ARGS_EARTHQUAKES = "argsEarthquakes";

    @InjectPresenter
    EarthquakesPresenter mEarthquakesPresenter;

    private RecyclerView mRecyclerView;
    private EarthquakesRecyclerAdapter mRecyclerAdapter;

    @ProvidePresenter
    EarthquakesPresenter provideEarthquakesPresenter() {
        Earthquakes earthquakes =(Earthquakes) getArguments().get(ARGS_EARTHQUAKES);
        return new EarthquakesPresenter(earthquakes.getEarthquakes());
    }

    public static EarthquakesListFragment getInstance(List<Earthquake> earthquakes) {
        EarthquakesListFragment fragment = new EarthquakesListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_EARTHQUAKES, new Earthquakes(earthquakes));
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_earthquakes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void showEarthquakes(List<Earthquake> earthquakes) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter = new EarthquakesRecyclerAdapter(earthquakes);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
